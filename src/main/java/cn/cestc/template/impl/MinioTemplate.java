package cn.cestc.template.impl;

import cn.cestc.handler.FileHandler;
import cn.cestc.handler.impl.ExcelFileHandler;
import cn.cestc.handler.impl.TextFileHandler;
import cn.cestc.handler.impl.WordFileHandler;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ObjectUtil;
import cn.cestc.domain.MinioProperties;
import cn.cestc.domain.vo.OssFile;
import cn.cestc.template.OssTemplate;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * MinIOTemplate
 *
 * @author gyb
 */
@Slf4j
@Service
public class MinioTemplate implements OssTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //策略模式处理不同的文件类型
    private final Map<String, FileHandler> fileHandlers;
    public MinioTemplate() {
        fileHandlers = new HashMap<>();
        fileHandlers.put("txt", new TextFileHandler());
        fileHandlers.put("docx",new WordFileHandler());
        fileHandlers.put("doc",new WordFileHandler());
        fileHandlers.put("rtf",new WordFileHandler());
//        fileHandlers.put("xlsx",new ExcelFileHandler());
        // 添加其他文件类型的处理策略
    }

    /**
     * MinIO客户端
     */
    @Resource
    private MinioClient client;

    /**
     * 配置类
     */
    @Resource
    private MinioProperties ossProperties;

    /**
     * 格式化时间
     */
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * 字符集
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return boolean
     */
    @Override
    public boolean bucketExists(String bucketName) {
        try {
            return client.bucketExists(BucketExistsArgs.builder().bucket(getBucketName(bucketName)).build());
        } catch (Exception e) {
            logger.error("minio bucketExists Exception:{}", e);
        }
        return false;
    }

    /**
     * @Description:  : 创建 存储桶
     * @param  bucketName: 存储桶名称
     */
    public void makeBucket(String bucketName) {
        try {
            if (!client.bucketExists(BucketExistsArgs.builder().bucket(getBucketName(bucketName)).build())) {
                client.makeBucket(MakeBucketArgs.builder().bucket(getBucketName(bucketName)).build());
                logger.info("minio makeBucket success bucketName:{}", bucketName);
            }
        } catch (Exception e) {
            logger.error("minio makeBucket Exception:{}", e);
        }
    }

    /**
     * 获取文件信息
     *
     * @param fileName 存储桶文件名称
     * @return InputStream
     */
    @Override
    public OssFile getOssInfo(String fileName) {
        try {
            StatObjectResponse stat = client.statObject(
                    StatObjectArgs.builder().bucket(getBucketName(ossProperties.getBucketName())).object(fileName)
                            .build());
            OssFile ossFile = new OssFile();
            ossFile.setName(ObjectUtil.isEmpty(stat.object()) ? fileName : stat.object());
            ossFile.setFilePath(ossFile.getName());
            ossFile.setDomain(getOssHost(ossProperties.getBucketName()));
            ossFile.setHash(String.valueOf(stat.hashCode()));
            ossFile.setSize(stat.size());
            ossFile.setPutTime(DateUtil.date(stat.lastModified().toLocalDateTime()));
            ossFile.setContentType(stat.contentType());
            return ossFile;
        } catch (Exception e) {
            logger.error("minio getOssInfo Exception:{}", e);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param folderName 上传的文件夹名称
     * @param fileName   上传文件名
     * @param file       上传文件类
     * @return BladeFile
     */
    @Override
    @SneakyThrows
    public OssFile upLoadFile(String folderName, String fileName, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        // 文件大小
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("文件大小超过5M");
        }
        String suffix = getFileExtension(file.getOriginalFilename());
        // 文件后缀判断
        if (!CollUtil.contains(ossProperties.getFileExt(), suffix)) {
            String error = String.format("文件类型错误,目前支持[%s]等文件类型",
                    String.join(",", ossProperties.getFileExt()));
            throw new RuntimeException(error);
        }
        try {
            return upLoadFile(folderName, fileName, suffix, file.getInputStream());
        } catch (Exception e) {
            logger.error("minio upLoadFile Exception:{}", e);
            throw new RuntimeException("文件上传失败,请重新上传或联系管理员");
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param fullName 文件全名
     * @return {String}
     */
    public static String getFileExtension(String fullName) {
        Assert.notNull(fullName, "minio file fullName is null.");
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * 上传文件
     *
     * @param folderName 上传的文件夹名称
     * @param fileName   存储桶对象名称
     * @param suffix     文件后缀名
     * @param stream     文件流
     * @return BladeFile
     */
    @Override
    public OssFile upLoadFile(String folderName, String fileName, String suffix, InputStream stream) {
        try {
            return upLoadFile(ossProperties.getBucketName(), folderName, fileName, suffix, stream,
                    "application/octet" + "-stream");
        } catch (Exception e) {
            logger.error("minio upLoadFile Exception:{}", e);
        }
        return null;
    }

    /**
     * 上传文件
     * @param  bucketName: 存储桶名称
     * @param folderName: 上传的文件夹名称
     * @param fileName: 上传文件名
     * @param suffix: 文件后缀名
     * @param stream: 文件流
     * @param contentType: 文件类型
     */
    @SneakyThrows
    public OssFile upLoadFile(String bucketName, String folderName, String fileName, String suffix, InputStream stream,
                              String contentType) {
        if (!bucketExists(bucketName)) {
            logger.info("minio bucketName is not creat");
            makeBucket(bucketName);
        }
        OssFile file = new OssFile();
        String filePath = getFilePath(folderName, fileName, suffix);
        System.out.println("filePath:"+filePath);
        client.putObject(PutObjectArgs.builder().bucket(getBucketName(bucketName)).object(filePath)
                .stream(stream, stream.available(), -1).contentType(contentType).build());
        System.out.println("putObject:"+filePath);
        file.setOriginalName(fileName);
        file.setName(filePath);
        file.setDomain(getOssHost(bucketName));
        file.setFilePath(filePath);
        stream.close();
        logger.info("minio upLoadFile success, filePath:{}", filePath);
        return file;
    }

    /**
     * 删除文件
     *
     * @param fileName 存储桶对象名称
     */
    @Override
    public boolean removeFile(String fileName) {
        try {
            client.removeObject(
                    RemoveObjectArgs.builder().bucket(getBucketName(ossProperties.getBucketName())).object(fileName)
                            .build());
            logger.info("minio removeFile success, fileName:{}", fileName);
            return true;
        } catch (Exception e) {
            logger.error("minio removeFile fail, fileName:{}, Exception:{}", fileName, e);
        }
        return false;
    }

    /**
     * 批量删除文件
     *
     * @param fileNames 存储桶对象名称集合
     */
    @Override
    public boolean removeFiles(List<String> fileNames) {
        try {
            Stream<DeleteObject> stream = fileNames.stream().map(DeleteObject::new);
            client.removeObjects(RemoveObjectsArgs.builder().bucket(getBucketName(ossProperties.getBucketName()))
                    .objects(stream::iterator).build());
            logger.info("minio removeFiles success, fileNames:{}", fileNames);
            return true;
        } catch (Exception e) {
            logger.error("minio removeFiles fail, fileNames:{}, Exception:{}", fileNames, e);
        }
        return false;
    }

    /**
     * @Description: 下载文件
     * @Param response: 响应
     * @Param fileName: 文件名
     * @Param filePath: 文件路径
     * @return: void
     */
    @Override
    public void downloadFile(HttpServletResponse response, String fileName, String filePath) {
        GetObjectResponse is = null;
        try {
            GetObjectArgs getObjectArgs =
                    GetObjectArgs.builder().bucket(ossProperties.getBucketName()).object(filePath)
                            .build();
            is = client.getObject(getObjectArgs);
            // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding(ENCODING);
            // 设置文件头：最后一个参数是设置下载的文件名并编码为UTF-8
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, ENCODING));
            IoUtil.copy(is, response.getOutputStream());
            logger.info("minio downloadFile success, filePath:{}", filePath);
        } catch (Exception e) {
            logger.error("minio downloadFile Exception:{}", e);
        } finally {
            IoUtil.close(is);
        }
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param fileName   文件名称
     * @param expires    过期时间
     * @return url
     */
    public String getResignedObjectUrl(String bucketName, String fileName, Integer expires) {
        String link = "";
        try {
            link = client.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(getBucketName(bucketName))
                            .object(fileName).expiry(expires).build());
        } catch (Exception e) {
            logger.error("minio getResignedObjectUrl is fail, fileName:{}", fileName);
        }
        return link;
    }

    /**
     * 根据规则生成存储桶名称规则
     *
     * @param bucketName 存储桶名称
     * @return String
     */
    private String getBucketName(String bucketName) {
        return bucketName;
    }

    /**
     * 根据规则生成文件路径
     *
     * @param folderName       上传的文件夹名称
     * @param originalFilename 原始文件名
     * @param suffix           文件后缀名
     * @return string 上传的文件夹名称/yyyyMMdd/原始文件名_时间戳.文件后缀名
     */
    private String getFilePath(String folderName, String originalFilename, String suffix) {
        return folderName +
                "/" +
                originalFilename;
    }

    /**
     * 获取域名
     *
     * @param bucketName 存储桶名称
     * @return String
     */
    public String getOssHost(String bucketName) {
        return ossProperties.getEndpoint() + StrPool.SLASH + getBucketName(bucketName);
    }
    /**
     * 获取指定存储桶下的所有文件信息
     *
     * @param bucketName 存储桶名称
     * @return List<OssFile> 存储桶下的所有文件信息列表
     */
    public List<OssFile> getAllOssFiles(String bucketName) {
        List<OssFile> ossFiles = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = client.listObjects(
                    ListObjectsArgs.builder().bucket(getBucketName(bucketName)).build());

            for (Result<Item> result : results) {
                getItem(bucketName, result, ossFiles);
            }
        } catch (Exception e) {
            logger.error("minio getAllOssFiles Exception:{}", e);
        }
        return ossFiles;
    }
    /**
     * 获取指定存储桶下指定目录的所有文件信息
     *
     * @param bucketName 存储桶名称
     * @param folderName 目录名称
     * @return List<OssFile> 目录下的所有文件信息列表
     */
    public List<OssFile> getOssFilesInFolderHelp(String bucketName, String folderName) {
        List<OssFile> ossFiles = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = client.listObjects(
                    ListObjectsArgs.builder().bucket(getBucketName(bucketName)).prefix(folderName + "/").build());

            for (Result<Item> result : results) {
                getItem(bucketName, result, ossFiles);
            }
        } catch (Exception e) {
            logger.error("minio getOssFilesInFolder Exception:{}", e);
        }
        return ossFiles;
    }

    /**
     * 获取文件Item
     */
    private void getItem(String bucketName, Result<Item> result, List<OssFile> ossFiles) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        Item item = result.get();
        String filePath = item.objectName();
        OssFile ossFile = new OssFile();
        ossFile.setName(getFileNameFromPath(filePath));
        ossFile.setFilePath(filePath);
        ossFile.setDomain(getOssHost(bucketName));
        ossFile.setHash(item.etag());
        ossFile.setSize(item.size());
        ossFile.setPutTime(DateUtil.date(item.lastModified().toLocalDateTime()));
        ossFiles.add(ossFile);
    }

    /**
     * 获取指定存储桶下指定目录的所有文件信息
     *
     * @param folderName 目录名称
     * @return List<OssFile> 目录下的所有文件信息列表
     */
    @Override
    public List<OssFile> getOssFilesInFolder(String folderName){
        String bucket = ossProperties.getBucketName();
        return getOssFilesInFolderHelp(bucket, folderName);
    }
    /**
     * 从文件路径中提取文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    private String getFileNameFromPath(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < filePath.length() - 1) {
            return filePath.substring(lastSlashIndex + 1);
        }
        return filePath;
    }


    /**
     * 获取文件内部数据
     *
     * @param folderName 目录名称
     * @param fileNames  文件名列表，如果为null或空，则获取所有文件的内部数据
     * @return List<byte[]> 文件内部数据列表
     */
    @Override
    public List<String> getFileData(String folderName, List<String> fileNames) {
        String bucketName = ossProperties.getBucketName();
        List<String> fileDataList = new ArrayList<>();

        try {
            Iterable<Result<Item>> results = client.listObjects(ListObjectsArgs.builder()
                    .bucket(getBucketName(bucketName))
                    .prefix(folderName + "/")
                    .build());

            for (Result<Item> result : results) {
                Item item = result.get();
                if(item.isDir()) continue;
                //只读取输入文件 或 不输入文件名则全读取
                if (fileNames == null || fileNames.isEmpty() || fileNames.contains(getFileNameFromPath(item.objectName()))) {
                    try (InputStream inputStream = client.getObject(GetObjectArgs.builder()
                            .bucket(getBucketName(bucketName))
                            .object(item.objectName())
                            .build())) {
                        String fileName = item.objectName();
                        //获取对应的策略处理器
                        String fileExtension = getFileExtension(fileName);
                        FileHandler handler = fileHandlers.get(fileExtension.toLowerCase());
                        if (handler != null) {//有对应策略则读取数据
                            //读取文件内部数据
                            String fileData = handler.handleFile(inputStream);
                            fileDataList.add(fileData);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("minio getFileData Exception:{}", e);
        }

        return fileDataList;
    }





}
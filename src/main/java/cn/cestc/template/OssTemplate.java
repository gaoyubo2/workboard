package cn.cestc.template;

import cn.cestc.domain.vo.OssFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * OssTemplate抽象API
 *
 * @author gyb
 */
public interface OssTemplate {

    /**
     * 存储桶是否存在
     *
     * @param bucketName 存储桶名称
     * @return boolean
     */
    boolean bucketExists(String bucketName);


    /**
     * 获取文件信息
     *
     * @param fileName 存储桶文件名称
     * @return InputStream
     */
    OssFile getOssInfo(String fileName);

    /**
     * 上传文件
     *
     * @param folderName 上传的文件夹名称
     * @param fileName   上传文件名
     * @param file       上传文件类
     * @return BladeFile
     */
    OssFile upLoadFile(String folderName, String fileName, MultipartFile file);

    /**
     * 上传文件
     *
     * @param folderName 上传的文件夹名称
     * @param fileName   存储桶对象名称
     * @param suffix     文件后缀名
     * @param stream     文件流
     * @return BladeFile
     */
    OssFile upLoadFile(String folderName, String fileName, String suffix, InputStream stream);

    /**
     * 删除文件
     *
     * @param fileName 存储桶对象名称
     */
    boolean removeFile(String fileName);

    /**
     * 批量删除文件
     *
     * @param fileNames 存储桶对象名称集合
     */
    boolean removeFiles(List<String> fileNames);

    /**
     * @Description: 下载文件
     * @Param response: 响应
     * @Param fileName: 文件名
     * @Param filePath: 文件路径
     * @return: void
     */
    void downloadFile(HttpServletResponse response, String folderPath, String fileName);

    String getResignedObjectUrl( String folderPath, String fileName, Integer expires);

    List<OssFile> getOssFilesInFolder(String filePath);

    /**
     * 获取文件内部数据
     *
     * @param filePath 目录名称
     * @param fileNames  文件名列表，如果为null或空，则获取所有文件的内部数据
     * @return List<byte[]> 文件内部数据列表
     */
    List<String> getFileData(String filePath, List<String> fileNames);

    /**
     * 获取单个文件内部数据
     *
     * @param folderName 目录名称
     * @param fileName 文件名
     * @return String 文件内部数据
     */
    String getSingleFileData(String folderName, String fileName);
}
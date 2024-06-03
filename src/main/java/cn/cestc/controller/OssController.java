package cn.cestc.controller;

import cn.cestc.domain.model.Oss;
import cn.cestc.domain.vo.OssFile;
import cn.cestc.service.IOssService;
import cn.cestc.template.OssTemplate;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
@CrossOrigin
public class OssController{
    private final IOssService ossService;
    private final OssTemplate minioTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 上传文件接口
     *
     * @param file     要上传的文件
     * @param uid      用户ID（可选）
     * @param filePath 文件路径
     * @param extra    额外信息（可选）
     * @return 如果上传成功，则返回文件数据，否则返回上传失败消息
     */
    @PostMapping("upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam(required = false, name ="uid") Integer uid,
                             @RequestParam("filePath") String filePath,
                             @RequestParam(required = false,value = "extra") String extra) {
        boolean flag = ossService.uploadFile(file,uid,filePath,extra);
        // 获取文件内部数据
        List<String> fileData = minioTemplate.getFileData(filePath, Collections.singletonList(file.getOriginalFilename()));
        return flag ? AjaxResult.success(fileData.get(0)) : AjaxResult.error("上传失败");
    }

    /**
     * 根据文件路径获取该路径下的所有文件
     *
     * @param filePath 文件路径
     * @return 如果获取成功，则返回文件列表，否则返回获取失败消息
     */
    @GetMapping("getFilesByProject")
    public AjaxResult getFilesByProject(@RequestParam("filePath") String filePath) {
        List<OssFile> ossFileList = minioTemplate.getOssFilesInFolder(filePath);
        return AjaxResult.success("获取成功",ossFileList);
    }
    /**
     * 根据文件路径和文件名列表获取文件数据
     *
     * @param filePath  文件路径
     * @param fileNames 文件名列表
     * @return 如果获取成功，则返回文件数据列表，否则返回获取失败消息
     */
    @GetMapping("/fileData")
    public AjaxResult getFileData(@RequestParam("filePath") String filePath,
                                  @RequestParam(required = false,name = "fileNames") List<String> fileNames) {
        List<String> fileData = minioTemplate.getFileData(filePath, fileNames);
        return AjaxResult.success("获取成功",fileData);
    }
    /**
     * 下载文件接口
     *
     * @param folderPath 文件目录路径
     * @param fileName   文件名
     * @param response   响应对象
     */
    @GetMapping("/download")
    public void download(@RequestParam("folderPath") String folderPath,
                         @RequestParam(required = false, name = "fileName") String fileName,
                         HttpServletResponse response) {
        try {
            minioTemplate.downloadFile(response, folderPath, fileName);
        } catch (Exception e) {
            logger.error("文件下载过程中出错: ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.write(new ObjectMapper().writeValueAsString(AjaxResult.error("下载失败")));
                writer.flush();
            } catch (IOException ioException) {
                logger.error("写入错误响应时出错: ", ioException);
            }
        }
    }
    /**
     * 根据文件路径和文件名获取单个文件的数据
     *
     * @param folderPath 文件目录路径
     * @param fileName   文件名
     * @return 如果获取成功，则返回文件数据，否则返回获取失败消息
     */
    @GetMapping("/singleFileData")
    public AjaxResult getSingleFileData(@RequestParam("folderPath") String folderPath,
                                        @RequestParam(required = false,name = "fileName") String fileName) {
        String fileData = minioTemplate.getSingleFileData( folderPath,  fileName);
        return AjaxResult.success("获取成功",fileData);
    }

    /**
     * 获取文件外链，存在过期时间，单位s
     * @param folderPath 文件目录
     * @param fileName 文件名，需要带后缀
     * @param expires 过期时间，单位s
     * @return 文件下载链接
     */
    @GetMapping("/resignedObjectUrl")
    public AjaxResult getResignedObjectUrl(@RequestParam("folderPath") String folderPath,
                                           @RequestParam("fileName") String fileName,
                                           @RequestParam("expires") Integer expires){
        String url = minioTemplate.getResignedObjectUrl(folderPath, fileName, expires);
        return AjaxResult.success("获取成功",url);
    }

    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Oss oss){
        return AjaxResult.success(ossService.page(pageEntity.toPage(), Wrappers.lambdaQuery(oss)));
    }
    @GetMapping("/list")
    public AjaxResult list(Oss oss){
        return AjaxResult.success(ossService.list(Wrappers.lambdaQuery(oss)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Oss oss) {
        return AjaxResult.success(ossService.save(oss));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Oss oss) {
        return AjaxResult.success(ossService.updateById(oss));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        boolean flag = ossService.removeFilesByIds(ids);
        return flag?AjaxResult.success():AjaxResult.error();
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(ossService.getById(id));
    }
}

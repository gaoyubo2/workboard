package cn.cestc.controller;

import cn.cestc.domain.dto.UploadFileDTO;
import cn.cestc.domain.vo.OssFile;
import cn.cestc.service.IOssService;
import cn.cestc.template.OssTemplate;
import cn.hutool.core.net.multipart.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oss")
public class OssController {

    private final OssTemplate minioTemplate;
    private final IOssService ossService;


    @PostMapping("upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam(required = false, name ="uid") Integer uid,
                             @RequestParam("filePath") String filePath) {
        boolean flag = ossService.uploadFile(file,uid,filePath);
        return flag ? AjaxResult.success() : AjaxResult.error("上传失败");
    }
    @GetMapping("getFilesByProject")
    public AjaxResult getFilesByProject(@RequestParam("filePath") String filePath) {
        List<OssFile> ossFileList = minioTemplate.getOssFilesInFolder(filePath);
        return AjaxResult.success("获取成功",ossFileList);
    }
    @GetMapping("/fileData")
    public AjaxResult getFileData(@RequestParam("filePath") String filePath,
                                  @RequestParam(required = false,name = "fileNames") List<String> fileNames) {
        List<String> fileData = minioTemplate.getFileData(filePath, fileNames);
        return AjaxResult.success("获取成功",fileData);
    }

}

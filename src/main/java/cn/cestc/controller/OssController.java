package cn.cestc.controller;

import cn.cestc.domain.vo.OssFile;
import cn.cestc.template.OssTemplate;
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


    @PostMapping("upload")
    public AjaxResult upload(@RequestParam("path") String path,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("uid") String uid) {
        try {
            minioTemplate.upLoadFile(path, file.getOriginalFilename(), file);
            return AjaxResult.success("上传成功");
        } catch (Exception e) {
            return AjaxResult.error("上传失败: " + e.getMessage());
        }
    }
    @GetMapping("getFilesByProject")
    public AjaxResult getFilesByProject(@RequestParam("project") String project) {
        List<OssFile> ossFileList = minioTemplate.getOssFilesInFolder(project);
        return AjaxResult.success("获取成功",ossFileList);
    }
    @GetMapping("/fileData")
    public List<byte[]> getFileData(@RequestParam String bucketName, @RequestParam String folderName, @RequestParam(required = false) List<String> fileNames) {
        return minioTemplate.getFileData(bucketName, folderName, fileNames);
    }

}

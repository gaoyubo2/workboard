package cn.cestc.controller;

import cn.cestc.domain.Oss;
import cn.cestc.domain.vo.OssFile;
import cn.cestc.service.IOssService;
import cn.cestc.template.OssTemplate;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
public class OssController{
    private final IOssService ossService;
    private final OssTemplate minioTemplate;


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
        return AjaxResult.success(ossService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(ossService.getById(id));
    }
}

package cn.cestc.controller;
import cn.cestc.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;

import xin.altitude.cms.common.entity.PageEntity;
import cn.cestc.domain.Document;
@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController{
    private final IDocumentService documentService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Document document){
        return AjaxResult.success(documentService.page(pageEntity.toPage(), Wrappers.lambdaQuery(document)));
    }
    @GetMapping("/list")
    public AjaxResult list(Document document){
        return AjaxResult.success(documentService.list(Wrappers.lambdaQuery(document)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Document document) {
        return AjaxResult.success(documentService.save(document));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Document document) {
        return AjaxResult.success(documentService.updateById(document));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(documentService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(documentService.getById(id));
    }
}

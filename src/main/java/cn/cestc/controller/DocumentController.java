package cn.cestc.controller;
import cn.cestc.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import cn.cestc.mapper.DocumentMapper;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import cn.cestc.domain.Document;
@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
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

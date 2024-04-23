package cn.cestc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.cestc.service.ITemplateService;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import cn.cestc.domain.Template;
@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateController{
    private final ITemplateService templateService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Template template){
        return AjaxResult.success(templateService.page(pageEntity.toPage(), Wrappers.lambdaQuery(template)));
    }
    @GetMapping("/list")
    public AjaxResult list(Template template){
        return AjaxResult.success(templateService.list(Wrappers.lambdaQuery(template)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Template template) {
        return AjaxResult.success(templateService.save(template));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Template template) {
        return AjaxResult.success(templateService.updateById(template));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Long[] ids) {
        return AjaxResult.success(templateService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Long id) {
        return AjaxResult.success(templateService.getById(id));
    }

    /**
     * 点击模板，新建文件
     * @param id 模板主键
     * @return AjaxResult
     */
    @GetMapping("/createDoc")
    public AjaxResult createDocument(@RequestParam Integer id){
        String docUrl = templateService.createDoc(id);
        return docUrl == null ? AjaxResult.error("新建模板失败") : AjaxResult.success("新建模板成功",docUrl);
    }

    /**
     * 保存文档内容
     * @param padID 文档id
     * @param content   文档内容
     * @return AjaxResult
     */
    @GetMapping("/saveDoc")
    public AjaxResult saveDocument(@RequestParam String padID,
                                   @RequestParam String content,
                                   @RequestParam String padName){
        boolean flag = templateService.saveDoc(padID, content, padName);
        return flag ? AjaxResult.success() : AjaxResult.error("保存文档失败");
    }
}

package cn.cestc.controller;

import cn.cestc.domain.vo.CharsHistory;
import cn.cestc.domain.vo.DocUrl;
import cn.cestc.util.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.cestc.service.ITemplateService;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import cn.cestc.domain.Template;
@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
@CrossOrigin
public class TemplateController{
    private final ITemplateService templateService;
    private final TemplateUtil templateUtil;

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
        DocUrl docUrl = templateService.createDoc(id);
        return docUrl == null ? AjaxResult.error("新建模板失败") : AjaxResult.success("新建模板成功",docUrl);
    }

    @GetMapping("/createDocWithName")
    public AjaxResult createDocWithName(@RequestParam String name,
                                        @RequestParam String content,
                                        @RequestParam String author){
        DocUrl docUrl = templateService.createWithName(name, content, author);
        return docUrl == null ? AjaxResult.error("新建文件失败") : AjaxResult.success("新建文件成功",docUrl);
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
                                   @RequestParam String padName,
                                   @RequestParam(required = false) String authors){
        boolean flag = templateService.saveDoc(padID, content, padName, authors);
        return flag ? AjaxResult.success() : AjaxResult.error("保存文档失败");
    }

    /**
     * 获取历史聊天记录 - 单个文档
     * @param padID 文档id
     * @return  历史聊天记录
     */
    @GetMapping("/getChars")
    public AjaxResult getChars(@RequestParam String padID){
        List<CharsHistory> res = templateService.getChar(padID);
        return AjaxResult.success(res);
    }

    /**
     * 获取所有存储文档的聊天记录，并按时间顺序返回
     * @return 时间顺序的聊天记录
     */
    @GetMapping("/getAllChars")
    public AjaxResult getAllChars(){
        List<CharsHistory> res = templateService.getAllChar();
        return AjaxResult.success(res);
    }

    /**
     * 删除文档
     * @param padID 文档id
     * @return 封装结果
     */
    @GetMapping("/deletePad")
    public AjaxResult deletePad(@RequestParam String padID){
        boolean flag = templateUtil.deletePad(padID);
        return flag ? AjaxResult.success() : AjaxResult.error("删除文档失败");
    }

    /**
     * 获取文档内容
     * @param padID 文档id
     * @return 封装内容
     */
    @GetMapping("/getPad")
    public AjaxResult getPad(@RequestParam String padID){
        String content = templateUtil.getPad(padID);
        return AjaxResult.success(content);
    }
}

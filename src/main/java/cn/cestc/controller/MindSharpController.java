package cn.cestc.controller;
import cn.cestc.service.IMindSharpService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import cn.cestc.domain.MindSharp;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.cestc.mapper.MindSharpMapper;
import xin.altitude.cms.common.entity.AjaxResult;
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
@RestController
@RequestMapping("/cestc/mind/sharp")
public class MindSharpController{
    @Autowired
    private IMindSharpService mindSharpService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindSharp mindSharp){
        return AjaxResult.success(mindSharpService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindSharp)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindSharp mindSharp){
        return AjaxResult.success(mindSharpService.list(Wrappers.lambdaQuery(mindSharp)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindSharp mindSharp) {
        return AjaxResult.success(mindSharpService.save(mindSharp));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindSharp mindSharp) {
        return AjaxResult.success(mindSharpService.updateById(mindSharp));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindSharpService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindSharpService.getById(id));
    }
}

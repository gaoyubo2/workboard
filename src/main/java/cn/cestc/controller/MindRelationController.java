package cn.cestc.controller;
import cn.cestc.service.IMindRelationService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import cn.cestc.domain.MindRelation;
import java.util.List;
import cn.cestc.mapper.MindRelationMapper;
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
@RequestMapping("/cestc/mind/relation")
public class MindRelationController{
    @Autowired
    private IMindRelationService mindRelationService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindRelation mindRelation){
        return AjaxResult.success(mindRelationService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindRelation)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindRelation mindRelation){
        return AjaxResult.success(mindRelationService.list(Wrappers.lambdaQuery(mindRelation)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindRelation mindRelation) {
        return AjaxResult.success(mindRelationService.save(mindRelation));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindRelation mindRelation) {
        return AjaxResult.success(mindRelationService.updateById(mindRelation));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindRelationService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindRelationService.getById(id));
    }
}

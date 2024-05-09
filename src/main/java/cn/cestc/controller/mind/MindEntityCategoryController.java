package cn.cestc.controller.mind;

import cn.cestc.domain.model.MindEntityCategory;
import cn.cestc.service.IMindEntityCategoryService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/cestc/mind/entity/category")
public class MindEntityCategoryController{
    @Autowired
    private IMindEntityCategoryService mindEntityCategoryService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindEntityCategory mindEntityCategory){
        return AjaxResult.success(mindEntityCategoryService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindEntityCategory)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindEntityCategory mindEntityCategory){
        return AjaxResult.success(mindEntityCategoryService.list(Wrappers.lambdaQuery(mindEntityCategory)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindEntityCategory mindEntityCategory) {
        return AjaxResult.success(mindEntityCategoryService.save(mindEntityCategory));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindEntityCategory mindEntityCategory) {
        return AjaxResult.success(mindEntityCategoryService.updateById(mindEntityCategory));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindEntityCategoryService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindEntityCategoryService.getById(id));
    }
}

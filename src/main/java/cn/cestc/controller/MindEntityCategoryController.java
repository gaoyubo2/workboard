package cn.cestc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import cn.cestc.mapper.MindEntityCategoryMapper;
import cn.cestc.service.IMindEntityCategoryService;
import org.springframework.web.bind.annotation.RestController;
import cn.cestc.domain.MindEntityCategory;
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

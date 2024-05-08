package cn.cestc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import cn.cestc.domain.MindEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import cn.cestc.service.IMindEntityService;
import cn.cestc.mapper.MindEntityMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/cestc/mind/entity")
public class MindEntityController{
    @Autowired
    private IMindEntityService mindEntityService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindEntity mindEntity){
        return AjaxResult.success(mindEntityService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindEntity)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindEntity mindEntity){
        return AjaxResult.success(mindEntityService.list(Wrappers.lambdaQuery(mindEntity)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindEntity mindEntity) {
        return AjaxResult.success(mindEntityService.save(mindEntity));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindEntity mindEntity) {
        return AjaxResult.success(mindEntityService.updateById(mindEntity));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindEntityService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindEntityService.getById(id));
    }
}

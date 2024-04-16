package cn.cestc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.cestc.mapper.EventRoleMapper;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.cestc.domain.EventRole;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import cn.cestc.service.IEventRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/cestc/event/role")
public class EventRoleController{
    @Autowired
    private IEventRoleService eventRoleService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,EventRole eventRole){
        return AjaxResult.success(eventRoleService.page(pageEntity.toPage(), Wrappers.lambdaQuery(eventRole)));
    }
    @GetMapping("/list")
    public AjaxResult list(EventRole eventRole){
        return AjaxResult.success(eventRoleService.list(Wrappers.lambdaQuery(eventRole)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody EventRole eventRole) {
        return AjaxResult.success(eventRoleService.save(eventRole));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody EventRole eventRole) {
        return AjaxResult.success(eventRoleService.updateById(eventRole));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(eventRoleService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(eventRoleService.getById(id));
    }
}

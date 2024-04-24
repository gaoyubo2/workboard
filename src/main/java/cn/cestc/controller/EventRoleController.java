package cn.cestc.controller;

import cn.cestc.domain.EventRole;
import cn.cestc.service.IEventRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
@RestController
@RequestMapping("/cestc/event/role")
@RequiredArgsConstructor
@CrossOrigin
public class EventRoleController{
    private final IEventRoleService eventRoleService;
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

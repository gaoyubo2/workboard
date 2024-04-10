package cn.cestc.controller;
import cn.cestc.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import cn.cestc.domain.Event;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;

import xin.altitude.cms.common.entity.PageEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController{
    private final IEventService eventService;

    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Event event){
        return AjaxResult.success(eventService.page(pageEntity.toPage(), Wrappers.lambdaQuery(event)));
    }
    @GetMapping("/list")
    public AjaxResult list(Event event){
        return AjaxResult.success(eventService.list(Wrappers.lambdaQuery(event)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Event event) {
        return AjaxResult.success(eventService.save(event));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Event event) {
        return AjaxResult.success(eventService.updateById(event));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(eventService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(eventService.getById(id));
    }


}

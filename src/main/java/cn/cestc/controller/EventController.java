package cn.cestc.controller;

import cn.cestc.domain.Event;
import cn.cestc.service.IEventService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController{
    private final IEventService eventService;

    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Event event){
        return AjaxResult.success(eventService.page(pageEntity.toPage(), Wrappers.lambdaQuery(event)));
    }
    @GetMapping("/pagePlus")
    public AjaxResult pagePlus(@RequestParam Integer current,
                               @RequestParam(required = false) String key){
        List<Event> res = eventService.pagePlus(current, key);
        IPage<Event> page = new Page<>();
        page.setRecords(res);
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        if(key != null)
            eventQueryWrapper.like("name", key);
        page.setTotal(eventService.count(eventQueryWrapper));
        //page.setTotal(res.size());
        page.setSize(res.size());
        return AjaxResult.success(page);
    }
    @GetMapping("/list")
    public AjaxResult list(@RequestBody Event event){
        return AjaxResult.success(eventService.list(Wrappers.lambdaQuery(event)));
    }
    @GetMapping("/listCondition")
    public AjaxResult listByCondition(@RequestParam String key){
        // 构建查询条件
        LambdaQueryWrapper<Event> queryWrapper = Wrappers.lambdaQuery();

        // 如果 name 不为空，则添加名称模糊查询条件
        if(StringUtils.isNotBlank(key)) {
            queryWrapper.like(Event::getName, key);
        }



//        // 如果 remarks 不为空，则添加备注信息模糊查询条件
//        if(StringUtils.isNotBlank(key)) {
//            queryWrapper.like(Event::getRemarks, key).o;
//        }
//
//        // 如果 eventRole 不为空，则添加案件类型模糊查询条件
//        if(StringUtils.isNotBlank(key)) {
//            queryWrapper.like(Event::getEventRole, event.getEventRole());
//        }
        // 调用 service 的 list 方法并返回结果
        //List<Event> list = eventService.list(queryWrapper);
        return AjaxResult.success(eventService.list(queryWrapper));
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

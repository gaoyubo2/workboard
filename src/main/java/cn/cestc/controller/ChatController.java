package cn.cestc.controller;

import cn.cestc.domain.Chat;
import cn.cestc.service.IChatService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin
public class ChatController{
    private final IChatService chatService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Chat chat){
        return AjaxResult.success(chatService.page(pageEntity.toPage(), Wrappers.lambdaQuery(chat)));
    }
    @GetMapping("/list")
    public AjaxResult list(Chat chat,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        //return AjaxResult.success(chatService.list(Wrappers.lambdaQuery(chat)));
        LambdaQueryWrapper<Chat> queryWrapper = Wrappers.lambdaQuery(chat);
        // 添加日期范围筛选条件
        if (startDate != null && endDate != null) {
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
            queryWrapper.between(Chat::getCreateTime, startDateTime, endDateTime);
        }
        return AjaxResult.success(chatService.list(queryWrapper));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody Chat chat) {
        return AjaxResult.success(chatService.save(chat));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Chat chat) {
        return AjaxResult.success(chatService.updateById(chat));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(chatService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(chatService.getById(id));
    }
}

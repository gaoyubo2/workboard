package cn.cestc.controller;

import cn.cestc.domain.Chat;
import cn.cestc.domain.Event;
import cn.cestc.domain.vo.ChatWithEventVO;
import cn.cestc.service.IChatService;
import cn.cestc.service.IEventService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.BeanCopyUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin
public class ChatController{
    private final IChatService chatService;
    private final IEventService eventService;
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
        List<Chat> list = chatService.list(queryWrapper);
        List<ChatWithEventVO> res = new ArrayList<>();
        for (Chat c : list) {
            ChatWithEventVO vo = new ChatWithEventVO();
            BeanCopyUtils.copyProperties(c,vo);
            Event byId = eventService.getById(c.getEventId());
            vo.setSource("测试");
            res.add(vo);
        }
        return AjaxResult.success(res);
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

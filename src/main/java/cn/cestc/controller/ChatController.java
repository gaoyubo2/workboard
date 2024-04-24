package cn.cestc.controller;

import cn.cestc.domain.Chat;
import cn.cestc.service.IChatService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
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
    public AjaxResult list(Chat chat){
        return AjaxResult.success(chatService.list(Wrappers.lambdaQuery(chat)));
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

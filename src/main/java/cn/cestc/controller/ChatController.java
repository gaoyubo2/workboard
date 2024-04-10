package cn.cestc.controller;
import cn.cestc.service.IChatService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import cn.cestc.domain.Chat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/chat")
public class ChatController{
    private final IChatService chatService;

    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

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

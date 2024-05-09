package cn.cestc.service.impl;

import cn.cestc.domain.model.Chat;
import cn.cestc.mapper.ChatMapper;
import cn.cestc.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper,Chat> implements IChatService{
}

package cn.cestc.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cestc.domain.Chat;
import cn.cestc.mapper.ChatMapper;
import cn.cestc.service.IChatService;
import org.springframework.stereotype.Service;
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper,Chat> implements IChatService{
}

package cn.cestc.service.impl;
import cn.cestc.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cestc.domain.Event;
import cn.cestc.mapper.EventMapper;
import org.springframework.stereotype.Service;
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper,Event> implements IEventService {
}

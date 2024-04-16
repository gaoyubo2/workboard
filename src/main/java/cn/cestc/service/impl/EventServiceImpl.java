package cn.cestc.service.impl;
import cn.cestc.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cestc.domain.Event;
import cn.cestc.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl extends ServiceImpl<EventMapper,Event> implements IEventService {
    private final EventMapper eventMapper;

    @Override
    public List<Event> pagePlus(Integer current, String key) {
        if(current == 1){
            List<Event> res = eventMapper.pagePlus(7, key);
            return res;
        }
        else{
            List<Event> res = eventMapper.pagePlus2(12,7+(current-2)*12, key);
            return res;
        }
    }
}

package cn.cestc.service.impl;

import cn.cestc.domain.model.Event;
import cn.cestc.mapper.EventMapper;
import cn.cestc.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
            return eventMapper.pagePlus(7, key);
        }
        else{
            return eventMapper.pagePlus2(12,7+(current-2)*12, key);
        }
    }

    @Override
    public List<Event> pagefavor(Integer current, Integer favorite) {
        if(current == 1){
            return eventMapper.pagefavo(7, favorite);
        }
        else{
            return eventMapper.pagefavor(12,7+(current-2)*12, favorite);
        }
    }
}

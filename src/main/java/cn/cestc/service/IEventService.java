package cn.cestc.service;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.cestc.domain.Event;

import java.util.List;

public interface IEventService extends IService<Event>{
    List<Event> pagePlus(Integer current, String key);

    List<Event> pagefavor(Integer current, Integer favorite);
}

package cn.cestc.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.cestc.domain.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface EventMapper extends BaseMapper<Event>{

    List<Event> pagePlus(@Param("limit") int limit, String key);


    List<Event> pagePlus2(int limit, int offset, String key);
}

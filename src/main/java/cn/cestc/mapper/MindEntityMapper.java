package cn.cestc.mapper;
import cn.cestc.domain.model.MindEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper

public interface MindEntityMapper extends BaseMapper<MindEntity>{
}

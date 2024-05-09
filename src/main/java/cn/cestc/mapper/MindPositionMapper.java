package cn.cestc.mapper;
import cn.cestc.domain.model.MindPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper

public interface MindPositionMapper extends BaseMapper<MindPosition>{
}

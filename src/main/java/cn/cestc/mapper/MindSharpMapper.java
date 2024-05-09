package cn.cestc.mapper;
import cn.cestc.domain.model.MindSharp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface MindSharpMapper extends BaseMapper<MindSharp>{
}

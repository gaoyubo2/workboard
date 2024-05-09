package cn.cestc.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.cestc.domain.model.Oss;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface OssMapper extends BaseMapper<Oss>{
}

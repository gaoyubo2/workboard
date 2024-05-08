package cn.cestc.service.impl;
import cn.cestc.domain.MindEntity;
import cn.cestc.mapper.MindEntityMapper;
import cn.cestc.service.IMindEntityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MindEntityServiceImpl extends ServiceImpl<MindEntityMapper,MindEntity> implements IMindEntityService{
}

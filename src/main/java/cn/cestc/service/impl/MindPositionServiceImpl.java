package cn.cestc.service.impl;
import cn.cestc.domain.MindPosition;
import cn.cestc.mapper.MindPositionMapper;
import cn.cestc.service.IMindPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MindPositionServiceImpl extends ServiceImpl<MindPositionMapper,MindPosition> implements IMindPositionService{
}

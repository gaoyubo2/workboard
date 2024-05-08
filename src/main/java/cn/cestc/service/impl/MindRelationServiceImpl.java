package cn.cestc.service.impl;
import cn.cestc.domain.MindRelation;
import cn.cestc.mapper.MindRelationMapper;
import cn.cestc.service.IMindRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MindRelationServiceImpl extends ServiceImpl<MindRelationMapper,MindRelation> implements IMindRelationService{
}

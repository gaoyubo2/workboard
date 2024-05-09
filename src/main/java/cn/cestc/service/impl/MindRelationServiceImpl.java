package cn.cestc.service.impl;

import cn.cestc.domain.model.MindRelation;
import cn.cestc.mapper.MindRelationMapper;
import cn.cestc.service.IMindRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MindRelationServiceImpl extends ServiceImpl<MindRelationMapper,MindRelation> implements IMindRelationService{
    @Override
    public List<Integer> getChildren(Integer id) {
        return this.list(new QueryWrapper<MindRelation>().eq("prev_id", id))
                .stream()
                .map(MindRelation::getEntityId)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Integer getPrevId(Integer id) {
        return this.getOne(new QueryWrapper<MindRelation>().eq("entity_id", id).last("LIMIT 1")).getPrevId();

    }
}

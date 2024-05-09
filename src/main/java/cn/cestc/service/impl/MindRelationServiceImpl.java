package cn.cestc.service.impl;

import cn.cestc.domain.model.MindEntity;
import cn.cestc.domain.model.MindRelation;
import cn.cestc.mapper.MindEntityMapper;
import cn.cestc.mapper.MindPositionMapper;
import cn.cestc.mapper.MindRelationMapper;
import cn.cestc.service.IMindRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MindRelationServiceImpl extends ServiceImpl<MindRelationMapper,MindRelation> implements IMindRelationService{
    private final MindRelationMapper mindRelationMapper;
    private final MindPositionMapper mindPositionMapper;
    private final MindEntityMapper mindEntityMapper;

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

    @Override
    public boolean deleteSelf(Integer id) {
        // 删除自己
        QueryWrapper<MindRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entity_id", id)
                .or()
                .eq("prev_id", id)
                .or()
                .eq("next_id", id);
        int delete = mindRelationMapper.delete(queryWrapper);
        System.out.println("Relation:"+delete);
        if(delete < 1)
            throw new RuntimeException("删除节点失败");
        return true;
    }
}

package cn.cestc.service.impl;

import cn.cestc.domain.dto.MindEntityDTO;
import cn.cestc.domain.vo.MindEntityVO;
import cn.cestc.domain.model.MindEntity;
import cn.cestc.domain.model.MindPosition;
import cn.cestc.domain.model.MindRelation;
import cn.cestc.mapper.MindEntityMapper;
import cn.cestc.mapper.MindRelationMapper;
import cn.cestc.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MindEntityServiceImpl extends ServiceImpl<MindEntityMapper,MindEntity> implements IMindEntityService{
    private final IMindRelationService relationService;
    private final IMindSharpService sharpService;
    private final IMindPositionService positionService;
    private final IMindEntityCategoryService categoryService;
    private final PlatformTransactionManager transactionManager;
    private final MindRelationMapper relationMapper;


    @Override
    public List<MindEntityVO> getNextList(Integer id) {
        // 创建用于存储结果的列表
        List<MindEntityVO> resultList = new ArrayList<>();

        // 获取给定实体ID的所有子节点ID列表
        List<Integer> childIds = relationService.getChildren(id);
        System.out.println("children:"+childIds);

        // 遍历子节点ID列表，并递归地获取每个子节点的信息
        for (Integer childId : childIds) {
            MindEntityVO childDto = getEntityInfo(childId);
            childDto.setPrevId(id);
            resultList.add(childDto);
        }

        return resultList;
    }

    @Override
    public List<MindEntityVO> getNextChildList(Integer id) {
        List<Integer> childIds = relationService.getChildren(id);
        List<MindEntityVO> result = new ArrayList<>(childIds.size());
        for (Integer childId : childIds){
            result.add(getEntityInfoById(childId));
        }
        return result;
    }

    @Override
    @Transactional
    public boolean deleteSelf(Integer id) {
//        int i = baseMapper.deleteById(id);
//        if(i < 1){
//            return false;
//        }
//        System.out.println("Entity:"+i);
        boolean b = relationService.deleteSelf(id);
        boolean c = positionService.deleteSelf(id);
        return b && c;
    }

    @Override
    @Transactional
    public boolean changePre(Integer id, Integer newPreId) {
        // 更新操作
        UpdateWrapper<MindRelation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("entity_id", id)
                .set("prev_id", newPreId);

        // 执行更新
        relationMapper.update(null, updateWrapper);

        // 删除操作
        UpdateWrapper<MindRelation> deleteWrapper = new UpdateWrapper<>();
        deleteWrapper.eq("next_id", id);

        // 执行删除
        relationMapper.delete(deleteWrapper);

        return true;
    }
    // 添加节点
    @Override
    @Transactional
    public Integer addEntity(MindEntityDTO entityDTO) {
        MindEntity entity = createEntity(entityDTO);
        saveEntity(entity);

        MindPosition position = createPosition(entityDTO, entity.getId());
        savePosition(position);

        MindRelation relation = createRelation(entityDTO, entity.getId());
        saveRelation(relation);

        return entity.getId();
    }
    private MindEntity createEntity(MindEntityDTO entityDTO) {
        MindEntity entity = new MindEntity();
        BeanUtils.copyProperties(entityDTO, entity);
        return entity;
    }

    private void saveEntity(MindEntity entity) {
        save(entity);
    }

    private MindPosition createPosition(MindEntityDTO entityDTO, Integer entityId) {
        MindPosition position = new MindPosition();
        position.setEntityId(entityId);
        position.setPositionX(entityDTO.getPositionX());
        position.setPositionY(entityDTO.getPositionY());
        return position;
    }

    private void savePosition(MindPosition position) {
        positionService.save(position);
    }

    private MindRelation createRelation(MindEntityDTO entityDTO, Integer entityId) {
        // 创建父节点指向自己的指针
        saveRelationPreToSelf(entityDTO, entityId);

        // 创建自己的
        MindRelation relation = new MindRelation();
        relation.setEntityId(entityId);
        relation.setPrevId(entityDTO.getPrevId());
        return relation;
    }

    private void saveRelationPreToSelf(MindEntityDTO entityDTO, Integer entityId) {
        MindRelation relationPreToSelf = new MindRelation();
        //查询父节点的prevId
        Integer preEntityPrevId = relationService.getOne(new QueryWrapper<MindRelation>()
                .eq("entity_id", entityDTO.getPrevId())
                .eq("next_id", 0)).getPrevId();
        relationPreToSelf.setEntityId(entityDTO.getPrevId());
        relationPreToSelf.setPrevId(preEntityPrevId);
        relationPreToSelf.setNextId(entityId);
        relationService.save(relationPreToSelf);
    }

    private void saveRelation(MindRelation relation) {
        relationService.save(relation);
    }


    // 递归方法，用于获取给定实体ID的信息及其所有子节点的信息
    private MindEntityVO getEntityInfo(Integer entityId) {
        // 获取给定实体ID的信息，样式id,类型id等
         MindEntityVO entityDto = getEntityInfoById(entityId);

        // 获取给定实体ID的所有子节点ID列表
        List<Integer> childIds = relationService.getChildren(entityId);

        // 如果没有子节点，则直接返回当前实体的信息
        if (childIds.isEmpty()) {
            // 返回当前实体的信息
             return entityDto;
        }

        // 创建用于存储子节点信息的列表
        List<MindEntityVO> children = new ArrayList<>();

        // 遍历子节点ID列表，并递归地获取每个子节点的信息
        for (Integer childId : childIds) {
            MindEntityVO childDto = getEntityInfo(childId);
            childDto.setPrevId(entityId);
            children.add(childDto);
        }

        // 构造包含当前实体及其所有子节点信息的 MindEntityDTO 对象
         entityDto.setChildren(children);
         return entityDto;
    }

    /**
     * 获取实体DTO
     * @param entityId id
     * @return 实体DTO
     */
    private MindEntityVO getEntityInfoById(Integer entityId) {
        MindEntityVO dto = getEntityBasicInfoById(entityId);
        return getEntityExtraInfoById(dto);
    }

    /**
     * 获取给定实体ID的基本实体信息
     * @param entityId id
     * @return 基本实体
     */
    private MindEntityVO getEntityBasicInfoById(Integer entityId) {

        // 调用MindEntityMapper的selectById方法，根据实体ID获取实体信息
        MindEntity entity = getById(entityId);
        // 构造MindEntityDTO对象，并设置实体的属性
        MindEntityVO dto = new MindEntityVO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    /**
     * 获取给定实体ID的额外信息
     * @param dto 基本实体
     * @return 携带额外信息的entity
     */
    private MindEntityVO getEntityExtraInfoById(MindEntityVO dto){
        //获取样式id
        Integer sharpId = categoryService.getById(dto.getCategoryId()).getSharpId();
        dto.setSharpId(sharpId);
        //获取position信息
        MindPosition position = positionService.getOne(new QueryWrapper<MindPosition>()
                .eq("entity_id", dto.getId()));
        dto.setPositionX(position.getPositionX());
        dto.setPositionY(position.getPositionY());
        return dto;
    }

}

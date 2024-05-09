package cn.cestc.service.impl;
import cn.cestc.domain.model.MindPosition;
import cn.cestc.mapper.MindPositionMapper;
import cn.cestc.service.IMindPositionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MindPositionServiceImpl extends ServiceImpl<MindPositionMapper,MindPosition> implements IMindPositionService{

    @Override
    public boolean deleteSelf(Integer id) {
        int delete = baseMapper.delete(new QueryWrapper<MindPosition>().eq("entity_id", id));
        System.out.println("Position:"+delete);
        if(delete < 1) {
            System.out.println("000000");
            throw new RuntimeException("删除节点失败");
        }
        System.out.println("11111");
        return true;
    }
}

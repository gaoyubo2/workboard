package cn.cestc.service;
import cn.cestc.domain.model.MindRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMindRelationService extends IService<MindRelation>{
    List<Integer> getChildren(Integer id);

    Integer getPrevId(Integer id);
}

package cn.cestc.service;

import cn.cestc.domain.dto.MindEntityDTO;
import cn.cestc.domain.model.MindEntity;
import cn.cestc.domain.vo.MindEntityVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMindEntityService extends IService<MindEntity>{
    List<MindEntityVO> getNextList(Integer id);

    List<MindEntityVO> getNextChildList(Integer id);

    boolean deleteSelf(Integer id);

    boolean changePre(Integer id, Integer newPreId);

    Integer addEntity(MindEntityDTO entityDTO);
}

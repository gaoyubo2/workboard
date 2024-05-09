package cn.cestc.service;
import cn.cestc.domain.dto.MindEntityDTO;
import cn.cestc.domain.model.MindEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMindEntityService extends IService<MindEntity>{
    List<MindEntityDTO> getNextList(Integer id);

    List<MindEntityDTO> getNextChildList(Integer id);

    boolean deleteSelf(Integer id);

    boolean changePre(Integer id, Integer newPreId);
}

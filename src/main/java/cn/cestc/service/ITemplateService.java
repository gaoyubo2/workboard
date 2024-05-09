package cn.cestc.service;
import cn.cestc.domain.model.Template;
import cn.cestc.domain.vo.CharsHistory;
import cn.cestc.domain.vo.DocUrl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITemplateService extends IService<Template>{
    DocUrl createDoc(Integer id);

    boolean saveDoc(String padID, String content, String padName, String authors);

    List<CharsHistory> getChar(String padID);

    List<CharsHistory> getAllChar();

    DocUrl createWithName(String name, String content, String author, String source);
}

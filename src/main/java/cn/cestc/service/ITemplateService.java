package cn.cestc.service;
import cn.cestc.domain.Template;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ITemplateService extends IService<Template>{
    String createDoc(Integer id);

    boolean saveDoc(String padID, String content, String padName);
}

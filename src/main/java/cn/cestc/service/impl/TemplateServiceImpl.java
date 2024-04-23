package cn.cestc.service.impl;
import cn.cestc.domain.Document;
import cn.cestc.domain.Template;
import cn.cestc.mapper.DocumentMapper;
import cn.cestc.mapper.TemplateMapper;
import cn.cestc.service.ITemplateService;
import cn.cestc.util.TemplateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper,Template> implements ITemplateService{
    private final TemplateMapper templateMapper;
    private final TemplateUtil templateUtil;
    private final DocumentMapper documentMapper;


    @Override
    public String createDoc(Integer id) {
        Template template = templateMapper.selectById(id);
        if(template == null){   //模板id错误
            return null;
        }
        String content = template.getContent();
        String padID = RandomStringUtils.randomAlphanumeric(8);
        return templateUtil.createPad(padID, content);
    }

    @Override
    @Transactional
    public boolean saveDoc(String padID, String content, String padName) {
        boolean flag = templateUtil.savePad(padID, content);
        if(flag){
            Document document = Document
                    .builder()
                    .padid(padID)
                    .name(padName)
                    .author("test")         //TODO：先写死
                    .build();
            int insert = documentMapper.insert(document);
            return insert > 0;
        }
        else{
            return false;
        }
    }
}

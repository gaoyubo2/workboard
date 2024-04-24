package cn.cestc.service.impl;
import cn.cestc.domain.Document;
import cn.cestc.domain.Template;
import cn.cestc.domain.vo.CharsHistory;
import cn.cestc.domain.vo.DocUrl;
import cn.cestc.mapper.DocumentMapper;
import cn.cestc.mapper.TemplateMapper;
import cn.cestc.service.ITemplateService;
import cn.cestc.util.TemplateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper,Template> implements ITemplateService{
    private final TemplateMapper templateMapper;
    private final TemplateUtil templateUtil;
    private final DocumentMapper documentMapper;


    @Override
    public DocUrl createDoc(Integer id) {
        Template template = templateMapper.selectById(id);
        if(template == null){   //模板id错误
            return null;
        }
        String content = template.getContent();
        String padID = RandomStringUtils.randomAlphanumeric(8);
        return templateUtil.createPad(padID, content);    //远程创建
    }

    @Override
    @Transactional
    public boolean saveDoc(String padID, String content, String padName, String authors) {
        boolean flag = templateUtil.savePad(padID, content);    //更新文件内容
        QueryWrapper<Document> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("padID", padID);
        Document document1 = documentMapper.selectOne(queryWrapper);
        document1.setName(padName);
        document1.setAuthor(authors);
        documentMapper.updateById(document1);
        return flag;
    }

    @Override
    public List<CharsHistory> getChar(String padID) {
        List<CharsHistory> result = templateUtil.getChar(padID);
        System.out.println(result);
        return result;
    }

    @Override
    public List<CharsHistory> getAllChar() {
        List<Document> documents = documentMapper.selectList(null);
        List<CharsHistory> result = new ArrayList<>();
        for(Document document : documents){
            String padid = document.getPadid();
            List<CharsHistory> list = getChar(padid);
            result.addAll(list);
        }
        // 使用 Stream API 对 List 进行排序
        return result.stream()
                .sorted(Comparator.comparingLong(history -> -Long.parseLong(history.getTime())))
                .collect(Collectors.toList());
    }

    @Override
    public DocUrl createWithName(String name, String content, String author) {
        String padID = RandomStringUtils.randomAlphanumeric(10);
        DocUrl docUrl = templateUtil.createPad(padID, content);
        Document document = Document
                .builder()
                .author(author)
                .name(name)
                .padid(padID)
                .build();

        documentMapper.insert(document);
        return docUrl;
    }
}

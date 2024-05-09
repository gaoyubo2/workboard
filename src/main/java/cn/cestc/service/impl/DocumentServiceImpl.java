package cn.cestc.service.impl;
import cn.cestc.domain.model.Document;
import cn.cestc.mapper.DocumentMapper;
import cn.cestc.service.IDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper,Document> implements IDocumentService{
}

package cn.cestc.service.impl;
import cn.cestc.domain.Document;
import cn.cestc.mapper.DocumentMapper;
import cn.cestc.service.IDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper,Document> implements IDocumentService{
}

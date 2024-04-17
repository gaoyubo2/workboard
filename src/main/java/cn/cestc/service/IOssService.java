package cn.cestc.service;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.cestc.domain.Oss;
import org.springframework.web.multipart.MultipartFile;

public interface IOssService extends IService<Oss>{
    boolean uploadFile(MultipartFile file, Integer uid, String filePath, String extra);
}

package cn.cestc.service;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.cestc.domain.model.Oss;
import org.springframework.web.multipart.MultipartFile;

public interface IOssService extends IService<Oss>{
    boolean uploadFile(MultipartFile file, Integer uid, String filePath, String extra);

    boolean removeFilesByIds(Integer[] ids);
}

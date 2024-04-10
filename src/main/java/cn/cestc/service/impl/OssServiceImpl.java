package cn.cestc.service.impl;
import cn.cestc.domain.Oss;
import cn.cestc.mapper.OssMapper;
import cn.cestc.service.IOssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements IOssService {
}

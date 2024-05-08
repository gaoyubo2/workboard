package cn.cestc.service.impl;
import cn.cestc.domain.MindSharp;
import cn.cestc.mapper.MindSharpMapper;
import cn.cestc.service.IMindSharpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MindSharpServiceImpl extends ServiceImpl<MindSharpMapper,MindSharp> implements IMindSharpService{
}

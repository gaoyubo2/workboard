package cn.cestc.service.impl;
import cn.cestc.domain.MindEntityCategory;
import cn.cestc.mapper.MindEntityCategoryMapper;
import cn.cestc.service.IMindEntityCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MindEntityCategoryServiceImpl extends ServiceImpl<MindEntityCategoryMapper,MindEntityCategory> implements IMindEntityCategoryService{
}

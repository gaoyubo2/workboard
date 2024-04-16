package cn.cestc.service.impl;
import cn.cestc.domain.EventRole;
import cn.cestc.mapper.EventRoleMapper;
import cn.cestc.service.IEventRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EventRoleServiceImpl extends ServiceImpl<EventRoleMapper,EventRole> implements IEventRoleService{
}

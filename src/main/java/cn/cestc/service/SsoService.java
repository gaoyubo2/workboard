package cn.cestc.service;

import cn.cestc.domain.vo.UserInfoVO;

public interface SsoService {
    UserInfoVO getUserInfoById(Integer uid);
}

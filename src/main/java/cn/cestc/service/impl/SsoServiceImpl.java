package cn.cestc.service.impl;

import cn.cestc.constant.SsoUrl;
import cn.cestc.domain.vo.UserInfoVO;
import cn.cestc.service.SsoService;
import cn.cestc.util.RestTemplateUtil;
import cn.cestc.util.UrlBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SsoServiceImpl implements SsoService {
    private final RestTemplateUtil restTemplateUtil;
    private final UrlBuilderUtil urlUtil;
    @Override
    public UserInfoVO getUserInfoById(Integer uid) {
        String getUserById = urlUtil.buildApiUrl("sso", SsoUrl.GET_USER_WITH_ROLE.getMethodName());
        System.out.println("userWithRole:"+getUserById);
        Map<String, Object> map = Collections.singletonMap("uid", uid);
        return restTemplateUtil.get(getUserById, UserInfoVO.class,map);
    }
}

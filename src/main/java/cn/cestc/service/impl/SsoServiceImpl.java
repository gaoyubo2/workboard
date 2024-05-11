package cn.cestc.service.impl;

import cn.cestc.constant.SsoUrl;
import cn.cestc.domain.vo.UserInfoVO;
import cn.cestc.service.SsoService;
import cn.cestc.util.RestTemplateUtil;
import cn.cestc.util.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class SsoServiceImpl implements SsoService {
    private final RestTemplateUtil restTemplateUtil;
    private final UrlBuilder urlUtil;

    @Autowired
    public SsoServiceImpl(RestTemplateUtil restTemplateUtil, @Qualifier("ssoUrlBuilderUtil") UrlBuilder urlUtil) {
        this.restTemplateUtil = restTemplateUtil;
        this.urlUtil = urlUtil;
    }

    @Override
    public UserInfoVO getUserInfoById(Integer uid) {
        String getUserById = urlUtil.buildApiUrl("sso", SsoUrl.GET_USER_WITH_ROLE.getMethodName());
        Map<String, String> map = Collections.singletonMap("uid", String.valueOf(uid));
//        方法一：
        return restTemplateUtil.get(getUserById, UserInfoVO.class, map);
        //方法二：
//        String strUserInfo = restTemplateUtil.get(getUserById, String.class, map);
//        return JSON.toJavaObject(JSON.parseObject(strUserInfo), UserInfoVO.class);
    }
}

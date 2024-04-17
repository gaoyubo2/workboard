package cn.cestc.service.impl;

import cn.cestc.domain.vo.UserInfoVO;
import cn.cestc.service.SsoService;
import cn.cestc.util.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SsoServiceImpl implements SsoService {
    private final RestTemplateUtil restTemplateUtil;
    private final RestTemplate restTemplate;
    @Value("${sso.getUserById}")
    String getUserById;
    @Override
    public UserInfoVO getUserInfoById(Integer uid) {
        Map<String, Object> map = Collections.singletonMap("uid", uid);
        return restTemplateUtil.get(getUserById, UserInfoVO.class,map);
    }
}

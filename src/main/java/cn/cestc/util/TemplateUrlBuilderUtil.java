package cn.cestc.util;

import cn.cestc.domain.property.ServiceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
@RequiredArgsConstructor
public class TemplateUrlBuilderUtil implements UrlBuilder {
    private final ServiceProperties serviceProperties;
    private final Map<String, String> cachedUrls = new ConcurrentHashMap<>();

    @Override
    public String buildApiUrl(String serviceName, String methodName) {
        String cacheKey = serviceName + "_" + methodName;
        if (cachedUrls.containsKey(cacheKey)) {
            return cachedUrls.get(cacheKey);
        }

        ServiceProperties.ServiceConfig serviceConfig = getServiceConfig(serviceName);
        String methodPath = getMethodPath(serviceConfig, methodName);
        String apiUrl = buildUrl(serviceConfig.getDomain(), serviceConfig.getPort(), methodPath,serviceConfig.getApikey());

        cachedUrls.put(cacheKey, apiUrl);
        return apiUrl;
    }

    private ServiceProperties.ServiceConfig getServiceConfig(String serviceName) {
        ServiceProperties.ServiceConfig serviceConfig = serviceProperties.getServices().get(serviceName);
        if (serviceConfig == null) {
            throw new IllegalArgumentException("Service not found: " + serviceName);
        }
        return serviceConfig;
    }

    private String getMethodPath(ServiceProperties.ServiceConfig serviceConfig, String methodName) {
        String methodPath = serviceConfig.getMethods().get(methodName);
        if (methodPath == null) {
            throw new IllegalArgumentException("Method not found: " + methodName);
        }
        return methodPath;
    }

    @Override
    public String buildUrl(String domain, String port, String methodPath,String apikey) {
        return domain + ":" + port + "/" + methodPath + "?apikey="+apikey;
    }

}
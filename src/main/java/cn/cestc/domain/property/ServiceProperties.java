package cn.cestc.domain.property;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "cestc")
@Data
public class ServiceProperties {

    private Map<String, ServiceConfig> services;

    @Data
    public static class ServiceConfig {
        private String domain;
        private String port;
        private Map<String, String> methods;
    }
}
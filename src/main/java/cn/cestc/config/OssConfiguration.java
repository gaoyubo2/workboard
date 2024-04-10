package cn.cestc.config;

import cn.cestc.template.OssTemplate;
import cn.cestc.template.impl.MinioTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfiguration {

    /**
     * 定义OssTemplate的实现为MinioTemplate
     * @return MinioTemplate
     */
    @Bean
    public OssTemplate ossTemplate() {
        return new MinioTemplate();
    }

}

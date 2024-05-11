package cn.cestc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        new RestTemplate();
        RestTemplate restTemplate;
        //解决乱码问题，遍历寻找，进行覆盖
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //添加额外的HttpMessageConverter，在构建器上配置的任何转换器都将替换RestTemplate的默认转换器。
        restTemplate = new RestTemplateBuilder().additionalMessageConverters(messageConverter).build();
        //添加转化器
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;

    }
}

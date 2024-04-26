package cn.cestc.config;


import cn.cestc.incepter.CorsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.format.FormatterRegistry;



@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsInterceptor corsInterceptor;

    public WebConfig(CorsInterceptor corsInterceptor) {
        this.corsInterceptor = corsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨域拦截器需放在最上面
        registry.addInterceptor(corsInterceptor);
    }
    /**
     * 注册转化器
     * @param registry 格式化
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 调用registry.addFormatter添加格式化器即可
        registry.addConverter(new StringToLocalDateTimeConverter());
    }
}
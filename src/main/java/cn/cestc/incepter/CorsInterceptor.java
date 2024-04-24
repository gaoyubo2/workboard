package cn.cestc.incepter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) {
        String origin = request.getHeader("Origin");
        if (origin != null && (origin.equals("http://10.56.181.117:5173") ||
                origin.equals("http://10.56.181.180:8084"))) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "token, *");


            // 如果是OPTIONS则结束请求
            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
                response.setStatus(HttpStatus.NO_CONTENT.value());
                return false;
            }
        }
        return true;
    }

}
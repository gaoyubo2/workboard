package cn.cestc.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.entity.AjaxResult;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage());
        log.debug(e.getMessage());
        log.info(e.getMessage());
        log.warn(e.getMessage());
        System.out.println(e.getMessage());
        return AjaxResult.error("请求处理失败");
    }
}

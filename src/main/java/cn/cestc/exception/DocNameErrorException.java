package cn.cestc.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

@ExceptionMapper(code = "500", msg = "文档名重复")
public final class DocNameErrorException extends  RuntimeException{
}


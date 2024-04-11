package cn.cestc.handler;

import java.io.IOException;
import java.io.InputStream;

/**
 * 处理文件接口（策略模式）
 */
public interface FileHandler {
    String handleFile(InputStream inputStream) throws IOException;
}


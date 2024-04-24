package cn.cestc.util;

public interface UrlBuilder {
    String buildUrl(String domain, String port, String methodPath,String apikey);
    String buildApiUrl(String serviceName, String methodName);
}

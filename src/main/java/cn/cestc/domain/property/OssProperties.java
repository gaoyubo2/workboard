package cn.cestc.domain.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Minio参数配置类
 *
 * @author gyb
 */
@Data
@ConfigurationProperties(prefix = OssProperties.PREFIX)
public class OssProperties {

    /**
     * 配置前缀
     */
    public static final String PREFIX = "oss";

    /**
     * 对象存储名称
     */
    private String name;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access key 账户ID
     */
    private String accessKey;

    /**
     * Secret key 密码
     */
    private String secretKey;

    /**
     * 默认的存储桶名称
     */
    private String bucketName = "iris";

    /**
     * 可上传的文件后缀名
     */
    private List<String> fileExt;

}
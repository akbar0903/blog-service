package com.akbar.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt令牌相关配置
 */
@Component
@ConfigurationProperties(prefix = "akbar.jwt")
@Data
public class JwtProperties {

    private String secretKey;
    private Long tTl;
    private String tokenName;
}

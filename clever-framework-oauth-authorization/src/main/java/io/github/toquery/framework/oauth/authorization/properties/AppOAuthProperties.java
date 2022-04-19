package io.github.toquery.framework.oauth.authorization.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 */
@Data
@ConfigurationProperties(prefix = AppOAuthProperties.PREFIX)
public class AppOAuthProperties {

    public static final String PREFIX = "app.oauth";

    private boolean enabled = true;

    private String issuer;

}

package io.github.toquery.framework.oauth.authorization.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author toquery
 */
@Data
@ConfigurationProperties(prefix = AppOauthProperties.PREFIX)
public class AppOauthProperties {

    public static final String PREFIX = "app.oauth";

    private boolean enabled = true;

    private String issuer;

}

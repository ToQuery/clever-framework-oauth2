package io.github.toquery.framework.oauth.authorization;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 *
 */
public class AppJdbcOAuth2AuthorizationConsentService extends JdbcOAuth2AuthorizationConsentService {
    /**
     * Constructs a {@code JdbcOAuth2AuthorizationConsentService} using the provided parameters.
     *
     * @param jdbcOperations             the JDBC operations
     * @param registeredClientRepository the registered client repository
     */
    public AppJdbcOAuth2AuthorizationConsentService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        super(jdbcOperations, registeredClientRepository);
    }
}

package io.github.toquery.framework.oauth.authorization;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 *
 */
public class AppJdbcOauth2AuthorizationService extends JdbcOAuth2AuthorizationService {
    /**
     * Constructs a {@code JdbcOAuth2AuthorizationService} using the provided parameters.
     *
     * @param jdbcOperations             the JDBC operations
     * @param registeredClientRepository the registered client repository
     */
    public AppJdbcOauth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        super(jdbcOperations, registeredClientRepository);
    }

    /**
     * Constructs a {@code JdbcOAuth2AuthorizationService} using the provided parameters.
     *
     * @param jdbcOperations             the JDBC operations
     * @param registeredClientRepository the registered client repository
     * @param lobHandler                 the handler for large binary fields and large text fields
     */
    public AppJdbcOauth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository, LobHandler lobHandler) {
        super(jdbcOperations, registeredClientRepository, lobHandler);
    }
}

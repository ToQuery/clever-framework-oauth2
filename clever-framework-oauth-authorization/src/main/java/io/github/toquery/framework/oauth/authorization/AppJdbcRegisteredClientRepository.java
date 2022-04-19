package io.github.toquery.framework.oauth.authorization;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;

/**
 *
 * @author toquery
 */
public class AppJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {

    public AppJdbcRegisteredClientRepository(JdbcOperations jdbcOperations) {
        super(jdbcOperations);
    }
}

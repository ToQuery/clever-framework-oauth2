package io.github.toquery.framework.oauth.authorization.autoconfig;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import io.github.toquery.framework.dao.EnableAppJpaRepositories;
import io.github.toquery.framework.oauth.authorization.AppJdbcOauth2AuthorizationConsentService;
import io.github.toquery.framework.oauth.authorization.AppJdbcOauth2AuthorizationService;
import io.github.toquery.framework.oauth.authorization.AppJdbcRegisteredClientRepository;
import io.github.toquery.framework.oauth.authorization.jose.Jwks;
import io.github.toquery.framework.oauth.authorization.properties.AppOauthProperties;
import io.github.toquery.framework.oauth.authorization.rest.OauthRegisteredClientRest;
import io.github.toquery.framework.oauth.authorization.services.IOauthRegisteredClientService;
import io.github.toquery.framework.oauth.authorization.services.impl.OauthRegisteredClientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;

/**
 *
 * @author toquery
 */
@Slf4j
@Lazy(false)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@EnableConfigurationProperties({AppOauthProperties.class})
@EntityScan(basePackages = "io.github.toquery.framework.oauth.authorization.entity")
@EnableAppJpaRepositories(basePackages = "io.github.toquery.framework.oauth.authorization.repository")
@ConditionalOnProperty(prefix = AppOauthProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AppOauthAuthorizationAutoConfiguration {
    @Autowired
    private AppOauthProperties appOauthProperties;

    public AppOauthAuthorizationAutoConfiguration() {
        log.info("AppOauthAuthorizationAutoConfiguration");
    }

    @Bean
    @ConditionalOnMissingBean
    public OauthRegisteredClientRest oauthRegisteredClientRest() {
        return new OauthRegisteredClientRest();
    }

    @Bean
    @ConditionalOnMissingBean
    public IOauthRegisteredClientService oauthRegisteredClientService() {
        return new OauthRegisteredClientServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new AppJdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new AppJdbcOauth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new AppJdbcOauth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    @ConditionalOnMissingBean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().issuer(appOAuthProperties.getIssuer()).build();
    }

//    @Bean
//    public EmbeddedDatabase embeddedDatabase() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql")
//                .build();
//    }
}

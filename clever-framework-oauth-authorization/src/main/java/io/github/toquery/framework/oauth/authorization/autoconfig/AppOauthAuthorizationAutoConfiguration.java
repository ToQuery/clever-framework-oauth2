package io.github.toquery.framework.oauth.authorization.autoconfig;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import io.github.toquery.framework.dao.EnableAppJpaRepositories;
import io.github.toquery.framework.oauth.authorization.AppJdbcOAuth2AuthorizationConsentService;
import io.github.toquery.framework.oauth.authorization.AppJdbcOAuth2AuthorizationService;
import io.github.toquery.framework.oauth.authorization.AppJdbcRegisteredClientRepository;
import io.github.toquery.framework.oauth.authorization.jose.Jwks;
import io.github.toquery.framework.oauth.authorization.properties.AppOAuthProperties;
import io.github.toquery.framework.oauth.authorization.rest.OAuthRegisteredClientRest;
import io.github.toquery.framework.oauth.authorization.services.IOAuthRegisteredClientService;
import io.github.toquery.framework.oauth.authorization.services.impl.OAuthRegisteredClientServiceImpl;
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
@EnableConfigurationProperties({AppOAuthProperties.class})
@EntityScan(basePackages = "io.github.toquery.framework.oauth.authorization.entity")
@EnableAppJpaRepositories(basePackages = "io.github.toquery.framework.oauth.authorization.repository")
@ConditionalOnProperty(prefix = AppOAuthProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class AppOauthAuthorizationAutoConfiguration {
    @Autowired
    private AppOAuthProperties appOAuthProperties;

    public AppOauthAuthorizationAutoConfiguration() {
        log.info("AppOauthAuthorizationAutoConfiguration");
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuthRegisteredClientRest oauthRegisteredClientRest() {
        return new OAuthRegisteredClientRest();
    }

    @Bean
    @ConditionalOnMissingBean
    public IOAuthRegisteredClientService oauthRegisteredClientService() {
        return new OAuthRegisteredClientServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new AppJdbcRegisteredClientRepository(jdbcTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new AppJdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new AppJdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
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

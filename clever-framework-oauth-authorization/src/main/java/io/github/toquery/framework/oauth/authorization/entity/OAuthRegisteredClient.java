package io.github.toquery.framework.oauth.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.toquery.framework.dao.entity.AppBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.Set;

/**
 *
 */
@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
@Table(name = "oauth2_registered_client")
public class OAuthRegisteredClient extends AppBaseEntity {

    @Column(name = "client_id", length = 100, updatable = false)
    private String clientId;
    @Column(name = "client_id_issued_at")
    private Instant clientIdIssuedAt;
    @Column(name = "client_secret", length = 200)
    private String clientSecret;
    @Column(name = "client_secret_expires_at")
    private Instant clientSecretExpiresAt;
    @Column(name = "client_name", length = 100)
    private String clientName;

    @Column(name = "client_authentication_methods", length = 1000)
    private String clientAuthenticationMethodsString;
    @Column(name = "authorization_grant_types", length = 1000)
    private String authorizationGrantTypesString;
    @Column(name = "redirect_uris", length = 1000)
    private String redirectUrisString;
    @Column(name = "scopes", length = 1000)
    private String scopesString;
    @Column(name = "client_settings", length = 2000)
    private String clientSettingsString;
    @Column(name = "token_settings", length = 2000)
    private String tokenSettingsString;

    @Transient
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    @Transient
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    @Transient
    private Set<String> redirectUris;
    @Transient
    private Set<String> scopes;
    @Transient
    private ClientSettings clientSettings;
    @Transient
    private TokenSettings tokenSettings;


}

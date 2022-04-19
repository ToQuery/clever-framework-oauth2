package io.github.toquery.framework.oauth.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.toquery.framework.dao.entity.AppBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author toquery
 * @link org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql
 *
 */
@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
@Table(name = "oauth2_registered_client")
public class OauthAuthorization extends AppBaseEntity {
}

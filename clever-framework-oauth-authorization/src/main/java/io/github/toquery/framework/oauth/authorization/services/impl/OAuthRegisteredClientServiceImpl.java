package io.github.toquery.framework.oauth.authorization.services.impl;

import io.github.toquery.framework.crud.service.impl.AppBaseServiceImpl;
import io.github.toquery.framework.oauth.authorization.entity.OAuthRegisteredClient;
import io.github.toquery.framework.oauth.authorization.repository.OAuthRegisteredClientRepository;
import io.github.toquery.framework.oauth.authorization.services.IOAuthRegisteredClientService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class OAuthRegisteredClientServiceImpl extends AppBaseServiceImpl<OAuthRegisteredClient, OAuthRegisteredClientRepository> implements IOAuthRegisteredClientService {

    /**
     * 查询条件表达式
     */
    public static final Map<String, String> expressionMap = new LinkedHashMap<String, String>() {
        {
            put("id", "id:EQ");
            put("clientId", "clientId:LIKE");
            put("clientName", "clientName:LIKE");
            put("clientAuthenticationMethodsString", "clientAuthenticationMethodsString:LIKE");
            put("authorizationGrantTypesString", "authorizationGrantTypesString:LIKE");
            put("redirectUrisString", "redirectUrisString:LIKE");
            put("scopesString", "scopesString:LIKE");
        }
    };

    @Override
    public Map<String, String> getQueryExpressions() {
        return expressionMap;
    }
}

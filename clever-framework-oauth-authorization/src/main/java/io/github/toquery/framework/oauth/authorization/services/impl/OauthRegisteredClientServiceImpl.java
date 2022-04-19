package io.github.toquery.framework.oauth.authorization.services.impl;

import io.github.toquery.framework.crud.service.impl.AppBaseServiceImpl;
import io.github.toquery.framework.oauth.authorization.entity.OauthRegisteredClient;
import io.github.toquery.framework.oauth.authorization.repository.OauthRegisteredClientRepository;
import io.github.toquery.framework.oauth.authorization.services.IOauthRegisteredClientService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author toquery
 */
public class OauthRegisteredClientServiceImpl extends AppBaseServiceImpl<OauthRegisteredClient, OauthRegisteredClientRepository> implements IOauthRegisteredClientService {

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

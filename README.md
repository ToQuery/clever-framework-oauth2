# clever-framework-oauth2
clever-framework 的 Oauth认证系统

- [OAuth2](https://oauth.net/2/)

## 使用方法

## 数据库表



- 客户端配置信息注册 （oauth2_registered_client）

授权服务器要求客户端必须是已经注册的，避免非法的客户端发起授权申请。就像你平常去一些开放平台申请一个`ClientID`和`Secret`。下面是定义脚本（oauth2-registered-client-schema.sql）:

```sql
CREATE TABLE oauth2_registered_client
(
    id                            varchar(100)                        NOT NULL,
    client_id                     varchar(100)                        NOT NULL,
    client_id_issued_at           timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)                        NULL,
    client_secret_expires_at      timestamp                           NULL,
    client_name                   varchar(200)                        NOT NULL,
    client_authentication_methods varchar(1000)                       NOT NULL,
    authorization_grant_types     varchar(1000)                       NOT NULL,
    redirect_uris                 varchar(1000)                       NULL,
    scopes                        varchar(1000)                       NOT NULL,
    client_settings               varchar(2000)                       NOT NULL,
    token_settings                varchar(2000)                       NOT NULL,
    PRIMARY KEY (id)
);
```



- OAuth2授权信息持久化（oauth2_authorization）（）

记录授权的资源拥有者（Resource Owner）对某个客户端的某次授权记录。对应的Java类为`OAuth2Authorization`。下面是定义脚本（oauth2-authorization-schema.sql）:

```sql

CREATE TABLE oauth2_authorization
(
    id                            varchar(100)  NOT NULL,
    registered_client_id          varchar(100)  NOT NULL,
    principal_name                varchar(200)  NOT NULL,
    authorization_grant_type      varchar(100)  NOT NULL,
    attributes                    varchar(4000) NULL,
    state                         varchar(500)  NULL,
    authorization_code_value      blob          NULL,
    `authorization_code_issued_at`  timestamp     NULL,
    authorization_code_expires_at timestamp     NULL,
    authorization_code_metadata   varchar(2000) NULL,
    access_token_value            blob          NULL,
    access_token_issued_at        timestamp     NULL,
    access_token_expires_at       timestamp     NULL,
    access_token_metadata         varchar(2000) NULL,
    access_token_type             varchar(100)  NULL,
    access_token_scopes           varchar(1000) NULL,
    oidc_id_token_value           blob          NULL,
    oidc_id_token_issued_at       timestamp     NULL,
    oidc_id_token_expires_at      timestamp     NULL,
    oidc_id_token_metadata        varchar(2000) NULL,
    refresh_token_value           blob          NULL,
    refresh_token_issued_at       timestamp     NULL,
    refresh_token_expires_at      timestamp     NULL,
    refresh_token_metadata        varchar(2000) NULL,
    PRIMARY KEY (id)
);
```



- 确认授权持久化（oauth2_authorization_consent）

资源拥有者（Resource Owner）对授权的确认信息`OAuth2AuthorizationConsent`的持久化，这个比较简单。下面是定义脚本（oauth2-authorization-consent-schema.sql）:

```sql

CREATE TABLE oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);
```



## 文章连接



- https://blog.csdn.net/j3T9Z7H/article/details/121413063

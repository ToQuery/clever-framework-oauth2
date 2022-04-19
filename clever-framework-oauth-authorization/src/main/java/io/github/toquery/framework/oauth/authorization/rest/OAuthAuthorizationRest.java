package io.github.toquery.framework.oauth.authorization.rest;

import com.google.common.collect.Sets;
import io.github.toquery.framework.core.exception.AppException;
import io.github.toquery.framework.core.log.AppLogType;
import io.github.toquery.framework.core.log.annotation.AppLogMethod;
import io.github.toquery.framework.crud.controller.AppBaseCrudController;
import io.github.toquery.framework.oauth.authorization.entity.OAuthRegisteredClient;
import io.github.toquery.framework.oauth.authorization.services.IOAuthRegisteredClientService;
import io.github.toquery.framework.web.domain.ResponseBodyWrap;
import io.micrometer.core.annotation.Timed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 *

@RestController
@RequestMapping("/sys/oauth/authorization")
@Timed(value = "system-oauth", description = "系统-授权")
public class OAuthAuthorizationRest extends AppBaseCrudController<IOAuthRegisteredClientService, OAuthRegisteredClient> {

    private static final String[] sort = new String[]{};

    public static final String MODEL_NAME = "系统管理";

    public static final String BIZ_NAME = "授权管理";


    @AppLogMethod(value = OAuthRegisteredClient.class, logType = AppLogType.QUERY, modelName =  MODEL_NAME, bizName = BIZ_NAME)
    @PreAuthorize("hasAnyAuthority('system:oauth:query')")
    @GetMapping
    public ResponseBodyWrap<?> pageResponseResult() {
        return super.pageResponseResult(sort);
    }

    @AppLogMethod(value = OAuthRegisteredClient.class, logType = AppLogType.QUERY, modelName =  MODEL_NAME, bizName = BIZ_NAME)
    @PreAuthorize("hasAnyAuthority('system:oauth:query')")
    @GetMapping(value = "/list")
    public ResponseBodyWrap<?> listResponseResult() {
        return super.listResponseResult(sort);
    }

    @AppLogMethod(value = OAuthRegisteredClient.class, logType = AppLogType.MODIFY, modelName =  MODEL_NAME, bizName = BIZ_NAME)
    @PreAuthorize("hasAnyAuthority('system:oauth:modify')")
    @PutMapping
    public ResponseBodyWrap<?> updateResponseResult(@RequestBody OAuthRegisteredClient OAuthRegisteredClient) throws AppException {
        return super.handleResponseBody(domainService.update(OAuthRegisteredClient, Sets.newHashSet("name", "code")));
    }

    @AppLogMethod(value = OAuthRegisteredClient.class, logType = AppLogType.DELETE, modelName =  MODEL_NAME, bizName = BIZ_NAME)
    @PreAuthorize("hasAnyAuthority('system:oauth:delete')")
    @DeleteMapping
    public ResponseBodyWrap<?> deleteSysRoleCheck(@RequestParam Set<Long> ids) throws AppException {
        domainService.deleteByIds(ids);
        return ResponseBodyWrap.builder().success().build();
    }

    @AppLogMethod(value = OAuthRegisteredClient.class, logType = AppLogType.QUERY, modelName =  MODEL_NAME, bizName = BIZ_NAME)
    @PreAuthorize("hasAnyAuthority('system:oauth:query')")
    @GetMapping("{id}")
    public ResponseBodyWrap<?> detailResponseBody(@PathVariable Long id) {
        return super.detailResponseBody(id);
    }
}
 */

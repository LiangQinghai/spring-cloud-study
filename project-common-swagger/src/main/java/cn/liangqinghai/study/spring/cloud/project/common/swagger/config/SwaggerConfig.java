package cn.liangqinghai.study.spring.cloud.project.common.swagger.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author LiangQinghai
 * @title SwaggerConfig
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 11:10
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerConfig {

    /**
     * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

    private static final String BASE_PATH = "/**";

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Docket docket(SwaggerProperties swaggerProperties) {

        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add(BASE_PATH);
        }

        List<Predicate<String>> basePath = new ArrayList<>();
        swaggerProperties.getBasePath().forEach(path -> basePath.add(PathSelectors.ant(path)));

        if (swaggerProperties.getExcludePath().isEmpty()) {
            swaggerProperties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
        }

        List<Predicate<String>> excludedPath = new ArrayList<>();
        swaggerProperties.getExcludePath().forEach(path -> excludedPath.add(PathSelectors.ant(path)));

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(Predicates.and(Predicates.not(Predicates.or(excludedPath)), Predicates.or(basePath)))
                .build()
                .securitySchemes(Collections.singletonList(securitySchema(swaggerProperties)))
                .securityContexts(Collections.singletonList(securityContext(swaggerProperties)))
                .pathMapping("/");

    }

    private SecurityContext securityContext(SwaggerProperties swaggerProperties) {

        return SecurityContext.builder()
                .securityReferences(defaultAuth(swaggerProperties))
                .forPaths(PathSelectors.regex(swaggerProperties.getAuthorization().getAuthRegex()))
                .build();

    }

    /**
     * 默认的全局鉴权策略
     *
     * @return
     */
    private List<SecurityReference> defaultAuth(SwaggerProperties swaggerProperties)
    {
        ArrayList<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        swaggerProperties.getAuthorization().getAuthorizationScopeList().forEach(authorizationScope -> authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription())));
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
        return Collections.singletonList(SecurityReference.builder()
                .reference(swaggerProperties.getAuthorization().getName())
                .scopes(authorizationScopeList.toArray(authorizationScopes))
                .build());
    }

    private OAuth securitySchema(SwaggerProperties swaggerProperties) {

        ArrayList<AuthorizationScope> scopeArrayList = new ArrayList<>();
        swaggerProperties.getAuthorization()
                .getAuthorizationScopeList()
                .forEach(scope -> scopeArrayList.add(new AuthorizationScope(scope.getScope(), scope.getDescription())));
        ArrayList<GrantType> grantTypes = new ArrayList<>();
        swaggerProperties.getAuthorization().getTokenUrlList()
                .forEach(token -> grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(token)));

        return new OAuth(swaggerProperties.getAuthorization().getName(), scopeArrayList, grantTypes);

    }

    /**
     * 基础信息配置
     *
     * @param swagger
     * @return
     */
    private ApiInfo apiInfo(SwaggerProperties swagger) {

        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .version(swagger.getVersion())
                .license(swagger.getLicence())
                .licenseUrl(swagger.getLicenceUrl())
                .termsOfServiceUrl(swagger.getTermsOfServiceUrl())
                .contact(new Contact(swagger.getContact().getName(), swagger.getContact().getUrl(), swagger.getContact().getEmail()))
                .build();

    }

}

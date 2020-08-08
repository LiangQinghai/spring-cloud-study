package cn.liangqinghai.study.spring.cloud.project.common.swagger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangQinghai
 * @title SwaggerProperties
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 11:12
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {


    private Boolean enabled = false;

    /**
     * 扫描路径
     */
    private String basePackage = "";

    /**
     * url
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * 排除url
     */
    private List<String> excludePath = new ArrayList<>();

    private String title = "";

    private String description = "";

    private String version = "";

    private String licence = "";

    private String licenceUrl = "";

    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";

    private String host = "";

    private Contact contact = new Contact();

    /**
     * 鉴权配置
     */
    private Authorization authorization = new Authorization();


    /**
     * 联系信息
     */
    @Getter
    @Setter
    public static class Contact {

        private String name = "";

        private String url = "";

        private String email = "";

    }

    /**
     * 鉴权
     */
    @Getter
    @Setter
    public static class Authorization {

        private String name = "";

        /**
         * url匹配
         */
        private String authRegex = "^.*$";

        /**
         * token url
         */
        private List<String> tokenUrlList = new ArrayList<>();

        /**
         * 鉴权作用域
         */
        private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();

    }

    /**
     * 作用域
     */
    @Getter
    @Setter
    public static class AuthorizationScope {

        private String scope = "";

        private String description = "";

    }

}

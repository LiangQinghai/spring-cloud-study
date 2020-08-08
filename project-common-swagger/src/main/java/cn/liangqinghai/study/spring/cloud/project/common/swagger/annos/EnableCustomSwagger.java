package cn.liangqinghai.study.spring.cloud.project.common.swagger.annos;

import cn.liangqinghai.study.spring.cloud.project.common.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author LiangQinghai
 * @title EnableCustomSwagger
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 12:01
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerConfig.class})
public @interface EnableCustomSwagger {
}

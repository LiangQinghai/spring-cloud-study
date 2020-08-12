package cn.liangqinghai.study.spring.cloud.project.common.core.utils.annos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LiangQinghai
 * @title Excels
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/12 9:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excels
{
    Excel[] value();
}

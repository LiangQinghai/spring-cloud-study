package cn.liangqinghai.study.spring.cloud.common.dto;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @title ResultDto
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto<T> implements Serializable {

    private static final long serialVersionUID = 3465570593760692889L;

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResultDto<T> ok(Integer code, String msg, T data) {

        return new ResultDto<>(code, msg, data);

    }

    public static <T> ResultDto<T> ok(T data) {

        return ok(HttpStatus.HTTP_OK, null, data);

    }

    public static <T> ResultDto<T> fail(Integer code, String msg) {

        return new ResultDto<>(code, msg, null);

    }

    public static <T> ResultDto<T> fail(String msg) {

        return fail(HttpStatus.HTTP_INTERNAL_ERROR, msg);

    }

}

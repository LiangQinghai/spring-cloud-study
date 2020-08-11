package cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto;

import java.io.Serializable;

/**
 * @author LiangQinghai
 * @title ResultDto
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/11 15:26
 */
public class ResultDto<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = 200;

    /** 失败 */
    public static final int FAIL = 500;

    private int code;

    private String msg;

    private T data;

    public static <T> ResultDto<T> ok()
    {
        return restResult(null, SUCCESS, null);
    }

    public static <T> ResultDto<T> ok(T data)
    {
        return restResult(data, SUCCESS, null);
    }

    public static <T> ResultDto<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> ResultDto<T> fail()
    {
        return restResult(null, FAIL, null);
    }

    public static <T> ResultDto<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> ResultDto<T> fail(T data)
    {
        return restResult(data, FAIL, null);
    }

    public static <T> ResultDto<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> ResultDto<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    private static <T> ResultDto<T> restResult(T data, int code, String msg)
    {
        ResultDto<T> apiResult = new ResultDto<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
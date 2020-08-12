package cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.user;

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.BaseException;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}

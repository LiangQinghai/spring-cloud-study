package cn.liangqinghai.study.spring.cloud.project.common.security.handler

import cn.hutool.http.HttpStatus
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.BaseException
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.CustomException
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.DemoModeException
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(BaseException::class)
    fun baseException(e: BaseException?): ResultDto<Any> {

        return ResultDto.fail(e?.message)

    }

    @ExceptionHandler(CustomException::class)
    fun businessException(e: CustomException): ResultDto<Any> {
        return if (e.code == null) {
            ResultDto.fail(e.message)
        } else ResultDto.fail(e.code, e.message)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handlerNoFoundException(e: Exception): ResultDto<Any> {
        log.error(e.message, e)
        return ResultDto.fail(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确")
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAuthorizationException(e: AccessDeniedException): ResultDto<Any> {
        log.error(e.message)
        return ResultDto.fail(HttpStatus.HTTP_FORBIDDEN, "没有权限，请联系管理员授权")
    }

    @ExceptionHandler(AccountExpiredException::class)
    fun handleAccountExpiredException(e: AccountExpiredException): ResultDto<Any> {
        log.error(e.message, e)
        return ResultDto.fail(e.message)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(e: UsernameNotFoundException): ResultDto<Any> {
        log.error(e.message, e)
        return ResultDto.fail(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResultDto<Any> {
        log.error(e.message, e)
        return ResultDto.fail(e.message)
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException::class)
    fun validatedBindException(e: BindException): ResultDto<Any> {
        log.error(e.message, e)
        val message = e.allErrors[0].defaultMessage
        return ResultDto.fail(message)
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validExceptionHandler(e: MethodArgumentNotValidException): ResultDto<Any> {
        log.error(e.message, e)
        val message = e.bindingResult.fieldError?.defaultMessage
        return ResultDto.fail(message)
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException::class)
    fun demoModeException(e: DemoModeException?): ResultDto<Any> {
        return ResultDto.fail("演示模式，不允许操作")
    }

}
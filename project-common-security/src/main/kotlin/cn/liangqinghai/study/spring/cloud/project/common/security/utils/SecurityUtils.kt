package cn.liangqinghai.study.spring.cloud.project.common.security.utils

import cn.liangqinghai.study.spring.cloud.project.common.security.domain.LoginUser
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object SecurityUtils {
    /**
     * 获取Authentication
     */
    val authentication: Authentication
        get() = SecurityContextHolder.getContext().authentication

    /**
     * 获取用户
     */
    @JvmStatic
    val username: String
        get() = loginUser!!.username

    /**
     * 获取用户
     */
    fun getLoginUser(authentication: Authentication): LoginUser? {
        val principal = authentication.principal
        return if (principal is LoginUser) {
            principal
        } else null
    }

    /**
     * 获取用户
     */
    @JvmStatic
    val loginUser: LoginUser?
        get() {
            val authentication = authentication ?: return null
            return getLoginUser(authentication)
        }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    @JvmStatic
    fun encryptPassword(password: String?): String {
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.encode(password)
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    @JvmStatic
    fun matchesPassword(rawPassword: String?, encodedPassword: String?): Boolean {
        val passwordEncoder = BCryptPasswordEncoder()
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    @JvmStatic
    fun isAdmin(userId: Long?): Boolean {
        return userId != null && 1L == userId
    }
}
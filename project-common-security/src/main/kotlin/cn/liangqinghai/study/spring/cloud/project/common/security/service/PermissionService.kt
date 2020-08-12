package cn.liangqinghai.study.spring.cloud.project.common.security.service

import cn.liangqinghai.study.spring.cloud.project.common.security.utils.SecurityUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import org.springframework.util.PatternMatchUtils
import org.springframework.util.StringUtils

@Service("ss")
class PermissionService {
    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    fun hasPermi(permission: String): Boolean {
        if (StringUtils.isEmpty(permission)) {
            return false
        }
        val loginUser = SecurityUtils.loginUser
        return if (StringUtils.isEmpty(loginUser) || CollectionUtils.isEmpty(loginUser?.authorities)) {
            false
        } else hasPermissions(loginUser?.authorities!!, permission)
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    fun lacksPermi(permission: String): Boolean {
        return hasPermi(permission) != true
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    fun hasAnyPermi(permissions: String): Boolean {
        if (StringUtils.isEmpty(permissions)) {
            return false
        }
        val loginUser = SecurityUtils.loginUser
        if (StringUtils.isEmpty(loginUser) || CollectionUtils.isEmpty(loginUser?.authorities)) {
            return false
        }
        val authorities = loginUser?.authorities
        for (permission in permissions.split(PERMISSION_DELIMETER).toTypedArray()) {
            if (hasPermissions(authorities!!, permission)) {
                return true
            }
        }
        return false
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    fun hasRole(role: String?): Boolean {
        if (StringUtils.isEmpty(role)) {
            return false
        }
        val loginUser = SecurityUtils.loginUser
        if (StringUtils.isEmpty(loginUser) || CollectionUtils.isEmpty(loginUser?.authorities)) {
            return false
        }
        for (authorities in loginUser?.authorities!!) {
            val roleKey = authorities.authority
            if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(role!!)) {
                return true
            }
        }
        return false
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    fun lacksRole(role: String?): Boolean {
        return hasRole(role) != true
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    fun hasAnyRoles(roles: String): Boolean {
        if (StringUtils.isEmpty(roles)) {
            return false
        }
        val loginUser = SecurityUtils.loginUser
        if (StringUtils.isEmpty(loginUser) || CollectionUtils.isEmpty(loginUser?.authorities)) {
            return false
        }
        for (role in roles.split(ROLE_DELIMETER).toTypedArray()) {
            if (hasRole(role)) {
                return true
            }
        }
        return false
    }

    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private fun hasPermissions(authorities: Collection<GrantedAuthority>, permission: String): Boolean {
        return authorities.stream().map { obj: GrantedAuthority -> obj.authority }
            .filter { str: String? -> StringUtils.hasText(str) }
            .anyMatch { x: String? -> ALL_PERMISSION.contains(x!!) || PatternMatchUtils.simpleMatch(permission, x) }
    }

    companion object {
        /** 所有权限标识  */
        private const val ALL_PERMISSION = "*:*:*"

        /** 管理员角色权限标识  */
        private const val SUPER_ADMIN = "admin"
        private const val ROLE_DELIMETER = ","
        private const val PERMISSION_DELIMETER = ","
    }
}
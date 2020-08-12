package cn.liangqinghai.study.spring.cloud.project.common.security.service

import cn.liangqinghai.study.spring.cloud.project.api.RemoteUserService
import cn.liangqinghai.study.spring.cloud.project.api.model.UserInfo
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.enums.UserStatus
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.exception.BaseException
import cn.liangqinghai.study.spring.cloud.project.common.security.domain.LoginUser
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.HashSet

@Service
class UserDetailsServiceImpl : UserDetailsService {

    companion object {
        private val log = LoggerFactory.getLogger(UserDetailsServiceImpl::class.java)
    }

    @Autowired
    val remoteUserService: RemoteUserService? = null
    override fun loadUserByUsername(username: String?): UserDetails? {

        val userInfo = remoteUserService?.getUserInfo(username)
        if (username != null) {
            checkUser(userInfo, username)
        }
        return getUserDetails(userInfo)

    }

    private fun checkUser(userResult: ResultDto<UserInfo?>?, username: String) {

        if (userResult != null) {
            if (userResult.data == null) {
                log.info("登录用户：{} 不存在.", username)
                throw UsernameNotFoundException("登录用户：$username 不存在")
            } else if (userResult != null) {
                if (UserStatus.DELETED.code == userResult.data!!.sysUser?.delFlag) {
                    log.info("登录用户：{} 已被删除.", username)
                    throw BaseException("对不起，您的账号：$username 已被删除")
                } else if (UserStatus.DISABLE.code == userResult.data!!.sysUser?.status) {
                    log.info("登录用户：{} 已被停用.", username)
                    throw BaseException("对不起，您的账号：$username 已停用")
                }
            }
        }

    }

    private fun getUserDetails(result: ResultDto<UserInfo?>?): UserDetails? {
        val info = result?.data
        val dbAuthsSet: MutableSet<String> = HashSet()
        if (info != null) {
            if (StringUtils.isNotEmpty(info.roles.toString())) {
                // 获取角色
                if (info != null) {
                    info.roles?.let { dbAuthsSet.addAll(it) }
                }
                // 获取权限
                if (info != null) {
                    info.permissions?.let { dbAuthsSet.addAll(it) }
                }
            }
        }
        val authorities: Collection<GrantedAuthority?> = AuthorityUtils
            .createAuthorityList(*dbAuthsSet.toTypedArray())
        val user = info?.sysUser
        if (user != null) {
            return LoginUser(
                user.userId, user.userName, user.password, true, true, true, true,
                authorities
            )
        }
        return null
    }

}
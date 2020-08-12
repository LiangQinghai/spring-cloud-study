package cn.liangqinghai.study.spring.cloud.project.common.security.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class LoginUser(
    val userId: Long?,
    username: String?,
    password: String?,
    enabled: Boolean,
    accountNonExpired: Boolean,
    credentialsNonExpired: Boolean,
    accountNonLocked: Boolean,
    authorities: Collection<GrantedAuthority?>
) : User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
}
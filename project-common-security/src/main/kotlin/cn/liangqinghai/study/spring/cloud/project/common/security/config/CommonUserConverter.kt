package cn.liangqinghai.study.spring.cloud.project.common.security.config

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.SecurityConstants
import cn.liangqinghai.study.spring.cloud.project.common.security.domain.LoginUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.AUTHORITIES
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter.USERNAME
import org.springframework.util.StringUtils
import java.lang.IllegalArgumentException

class CommonUserConverter : UserAuthenticationConverter {

    override fun extractAuthentication(map: MutableMap<String, *>?): Authentication? {

        if (map!!.contains(USERNAME)) {

            val authorities = getAuthorities(map)

            val userId = map[SecurityConstants.DETAILS_USER_ID] as Long
            val username = map[SecurityConstants.DETAILS_USERNAME] as String

            val loginUser = LoginUser(
                userId = userId,
                username = username,
                password = N_A,
                enabled = true,
                accountNonExpired = true,
                credentialsNonExpired = true,
                accountNonLocked = true,
                authorities = authorities
            )

            return UsernamePasswordAuthenticationToken(loginUser, N_A, authorities)

        }

        return null

    }

    private fun getAuthorities(map: MutableMap<String, *>): Collection<GrantedAuthority?> {

        val authorities = map[AUTHORITIES]

        if (authorities is String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities as String?)
        }

        if (authorities is Collection<*>) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                StringUtils.collectionToCommaDelimitedString(
                    authorities as Collection<*>?
                )
            )
        }

        throw IllegalArgumentException("Authorities must be either a String or a Collection")

    }

    override fun convertUserAuthentication(userAuthentication: Authentication?): MutableMap<String, *> {

        val authMap: MutableMap<String, Any> = LinkedHashMap()
        authMap[USERNAME] = userAuthentication!!.name

        if (userAuthentication.authorities != null && !userAuthentication.authorities.isEmpty()) {

            authMap[AUTHORITIES] = AuthorityUtils.authorityListToSet(userAuthentication.authorities)

        }

        return authMap

    }

    companion object {

        private const val N_A = "N/A"

    }

}
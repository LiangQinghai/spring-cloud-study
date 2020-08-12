package cn.liangqinghai.study.spring.cloud.project.common.security.service

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.CacheConstants
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.SecurityConstants
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import javax.sql.DataSource

open class RedisClientDetailsService(dataSource: DataSource) : JdbcClientDetailsService(dataSource) {

    init {
        super.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT)
        super.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT)
    }

    @Cacheable(value = [CacheConstants.CLIENT_DETAILS_KEY], key = "#clientId", unless = "#result == null ")
    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return super.loadClientByClientId(clientId)
    }

}
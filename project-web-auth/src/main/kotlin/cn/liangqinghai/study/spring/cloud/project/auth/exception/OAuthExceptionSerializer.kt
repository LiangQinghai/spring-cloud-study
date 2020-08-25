package cn.liangqinghai.study.spring.cloud.project.auth.exception

import cn.hutool.http.HttpStatus
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory

class OAuthExceptionSerializer : StdSerializer<OAuthException>(OAuthException::class.java) {

    override fun serialize(value: OAuthException?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeStartObject()
        gen?.writeNumberField("code", HttpStatus.HTTP_INTERNAL_ERROR)
        if (StringUtils.equals(value?.message, BAD_CREDENTIALS)) {
            gen?.writeStringField("msg", "用户名密码错误")
        } else {

            log.error("认证异常", value)
            gen?.writeStringField("msg", value?.message)

        }
        gen?.writeEndObject()
    }

    companion object {

        const val BAD_CREDENTIALS = "Bad credentials"

        private val log = LoggerFactory.getLogger(OAuthExceptionSerializer::class.java)

    }

}
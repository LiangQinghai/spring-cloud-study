package cn.liangqinghai.study.spring.cloud.project.common.redis.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author LiangQinghai
 * @title FastJson2JsonRedisSerializer
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 14:14
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private Class<T> clazz;

    static {

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

    }

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {

        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);

    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        String res = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(res, clazz);

    }

    public void setObjectMapper(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;

    }

}

package cn.liangqinghai.study.spring.cloud.project.common.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @title RedisService
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 14:42
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
public class RedisService {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 对象存储
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(String key, T value) {

        redisTemplate.opsForValue().set(key, value);

    }

    /**
     * 带存活时间对象存储
     *
     * @param key
     * @param value
     * @param ttl
     * @param timeUnit
     * @param <T>
     */
    public <T> void setCacheObject(String key, T value, Integer ttl, TimeUnit timeUnit) {

        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);

    }

    /**
     * 设置存活时间
     *
     * @param key
     * @param ttl
     * @param timeUnit
     * @return
     */
    public boolean expire(String key, long ttl, TimeUnit timeUnit) {

        Boolean expire = redisTemplate.expire(key, ttl, timeUnit);
        return expire == null ? false : expire;

    }

    /**
     * 设置存活时间，默认单位s
     *
     * @param key
     * @param ttl
     * @return
     */
    public boolean expire(String key, long ttl) {

        return expire(key, ttl, TimeUnit.SECONDS);

    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(String key) {

        ValueOperations<String, T> ops = redisTemplate.opsForValue();

        return ops.get(key);

    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public boolean deleteObject(String key) {

        Boolean delete = redisTemplate.delete(key);

        return delete == null ? false : delete;

    }

    /**
     * 删除多个缓存
     *
     * @param collection
     * @return
     */
    public long deleteObject(Collection collection) {

        Long delete = redisTemplate.delete(collection);

        return delete == null ? 0 : delete;

    }

    /**
     * 添加列表缓存
     *
     * @param key
     * @param data
     * @param <T>
     * @return
     */
    public <T> long setCacheList(String key, List<T> data) {

        Long all = redisTemplate.opsForList().rightPushAll(key, data);

        return all == null ? 0 : all;

    }

    /**
     * 获取列表数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheList(String key) {

        return redisTemplate.opsForList().range(key, 0, -1);

    }

    /**
     * 添加set缓存
     *
     * @param key
     * @param data
     * @param <T>
     * @return
     */
    public <T> long setCacheSet(String key, Set<T> data) {

        Long add = redisTemplate.opsForSet().add(key, data);

        return add == null ? 0 : add;

    }

    /**
     * 获取set缓存
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getCacheSet(String key) {

        return redisTemplate.opsForSet().members(key);

    }


    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }

}

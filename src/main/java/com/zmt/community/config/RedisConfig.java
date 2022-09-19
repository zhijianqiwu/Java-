package com.zmt.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis配置类
 *  @author zmt
 */
@Configuration
public class RedisConfig {

    /**
     * 配置组件RedisTemplate，主要配序列化的方法:Java数据存入数据库需要序列化
     * @param factory 后面会使用配置好的RedisTemplate来访问Redis，
     *                Template要想具备访问数据库的能力，需要能创建连接
     *                连接是由连接工厂创建，故需要将连接工厂注入，
     *                RedisConnectionFactory已经被容器管理
     * @return RedisTemplate这个Bean，key设置为String
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 设置key的序列化方式
        //RedisSerializer.string()返回能够序列化字符串的序列化器
        template.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式
        // JSON格式的数据是格式化的，便于识别和读取
        template.setValueSerializer(RedisSerializer.json());
        // 设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        //触发让template的参数生效
        template.afterPropertiesSet();
        return template;
    }

}

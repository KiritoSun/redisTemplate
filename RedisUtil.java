package com.zt.util;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/*
 **  RedisUtil： 对redis操作的封装
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String,String> template;

    /*
     **  对字符串操作
     */
    public void set(String key,String value){
        template.opsForValue().set(key, value);
    }
    public String get(String key){
        return template.opsForValue().get(key);
    }
    public void incr(String key){ 
        template.opsForValue().increment(key,1); 
    }
    public void decr(String key){ 
        template.opsForValue().increment(key,-1); 
    }
    /*
     ** 对哈希操作
     */
    public void hmset(String key,String field,String value){
        template.opsForHash().put(key,field,value);
    }
    public String hmget(String key,String field){
        return template.opsForHash().get(key,field).toString();
    }
    /*
     ** 对链表操作
     */
    public void lpush(String key,String value){
        template.opsForList().leftPush(key,value);
    }
    public void rpush(String key,String value){
        template.opsForList().rightPush(key,value);
    }
    public String lpop(String key){
        return template.opsForList().leftPop(key);
    }
    public String rpop(String key){
        return template.opsForList().rightPop(key);
    }
    public long llen(String key){
        return template.opsForList().size(key);
    }
    public List<String> lrange(String key){
        return template.opsForList().range(key,0,template.opsForList().size(key)-1);
    }
    /*
     ** 对集合操作
     */
    public void sadd(String key,String member){
        template.opsForSet().add(key,member);
    }
    public String spop(String key){
        return template.opsForSet().pop(key);
    }
    public long slen(String key){
        return template.opsForSet().size(key);
    }
    public Set<String> smembers(String key){
        return template.opsForSet().members(key);
    }
    /*
     ** 对有序集合操作
     */
    public void zadd(String key,String member,double score){
        template.opsForZSet().add(key,member,score);
    }
    public long zlen(String key){
        return template.opsForZSet().size(key);
    }
    public Set<String> zrange(String key){
        return template.opsForZSet().range(key,0,template.opsForZSet().size(key));
    }
    /*
     **  redis的一些其他功能
     */
    public void del(String key){ 
        template.delete(key); 
    }
}

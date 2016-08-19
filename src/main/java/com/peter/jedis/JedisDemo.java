package com.peter.jedis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisDemo {
    private static final int MAX_IDLE = 8;
    private static final int MAX_TOTAL=8;
    private static final int MAX_WAIT_MILLS=500;
    private static final String HOST = "192.168.99.100";
    private static final int PORT = 32768;
    private static final int TIMEOUT = 500;
    private static JedisPoolConfig config;
    private static JedisPool pool;
    static {
        config = new JedisPoolConfig();
        config.setMaxIdle(MAX_IDLE);
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxWaitMillis(MAX_WAIT_MILLS);
        config.setTestOnReturn(true);
        pool = new JedisPool(config,HOST,PORT,TIMEOUT);
    }
    public void testStrValue(){
        Jedis conn = pool.getResource();
        String key = "peter_test";
        String value = "hello world";
        conn.set(key,value);
        System.out.println(conn.get(key));
        conn.del(key);
        conn.close();
    }
    public void addSets(){
        String key = "members";
        String member1 = "member1";
        String member2 = "member2";
        String member3 = "member3";
        Jedis jedis = pool.getResource();
        jedis.sadd(key,member1,member2,member3);
        Set<String> members = jedis.smembers(key);
        for (String member: members){
            System.out.println(member);
        }
        if(jedis != null){
            jedis.close();
        }
    }
    public void addHash(){
        String key = "redisHash";
        Map<String,String> map = new HashMap<>();
        map.put("name","java");
        map.put("domain","www.java.com");
        map.put("description","learn how to programe in java");
        Jedis jedis = pool.getResource();
        jedis.hmset(key,map);
        Map<String,String> retreveMap = jedis.hgetAll(key);
        for (String keyMap : retreveMap.keySet()){
            System.out.println(keyMap+" " + retreveMap.get(keyMap));
        }
        jedis.close();
    }
//    public static void main(String[] args) {
//        JedisDemo jedisDemo = new JedisDemo();
//        jedisDemo.testStrValue();
//        jedisDemo.addSets();
//        jedisDemo.addHash();
//    }
}

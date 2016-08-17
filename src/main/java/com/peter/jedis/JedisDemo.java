package com.peter.jedis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
    public static void testStrValue(){
        Jedis conn = pool.getResource();
        String key = "peter_test";
        String value = "hello world";
        conn.set(key,value);
        System.out.println(conn.get(key));
        conn.del(key);
        conn.close();
    }

    public static void main(String[] args) {
        testStrValue();
    }
}

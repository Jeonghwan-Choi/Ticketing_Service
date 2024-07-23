package com.example.redis.ex;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class string {
    public static void main(String[] args) {

        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set("users:300:email", "jack3@naver.com");
                jedis.set("users:300:name", "jack3");
                jedis.set("users:300:age", "31");


                var userEmail = jedis.get("users:300:email");
                System.out.println(userEmail);

                List<java.lang.String> userInfo = jedis.mget("users:300:email", "users:300:name", "users:300:age");
                userInfo.forEach(System.out::println);

                long counter = jedis.incr("counter");
                System.out.println(counter);

                counter = jedis.incrBy("counter", 10L);
                System.out.println(counter);

                counter = jedis.decr("counter");
                System.out.println(counter);

                counter = jedis.decrBy("counter", 20);
                System.out.println(counter);

                Pipeline pipeline = jedis.pipelined();
                pipeline.set("users:400:email", "jack4@naver.com");
                pipeline.set("users:400:name", "jack4");
                pipeline.set("users:400:age", "34");
                List<Object> ob = pipeline.syncAndReturnAll();
                ob.forEach(i -> System.out.println(i.toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

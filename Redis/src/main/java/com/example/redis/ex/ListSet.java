package com.example.redis.ex;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

public class ListSet {
    public static void main(String[] args) {
        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
//                list
//                1.stack
                jedis.rpush("stack1", "aaaa");
                jedis.rpush("stack1", "bbbb");
                jedis.rpush("stack1", "cccc");

                List<String> stack1 =  jedis.lrange("stack1", 0, -1);
                stack1.forEach(System.out::println);

                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));
                System.out.println(jedis.rpop("stack1"));

//                2.queue
                jedis.rpush("queue2", "zzzz");
                jedis.rpush("queue2", "yyyy");
                jedis.rpush("queue2", "xxxx");

                System.out.println(jedis.lpop("queue2"));
                System.out.println(jedis.lpop("queue2"));
                System.out.println(jedis.lpop("queue2"));
                System.out.println(jedis.lpop("queue2"));

                List<String> queue2 = jedis.lrange("queue2", 0 , -1);
                queue2.forEach(System.out::println);

//                3.block brpop, blpop
//                while문 쓰면 계속 기다리면서 값 받기
                while (true) {
                    List<String> blpop = jedis.blpop(10, "queue:blocking");
                    if (blpop != null) {
                        blpop.forEach(System.out::println);
                    }
                }

//                4.set
                jedis.sadd("users:500:follow","100", "200", "300", "120");
                jedis.srem("users:500:follow", "100");

                Set<String> smembers = jedis.smembers("users:500:follow");
                smembers.forEach(System.out::println);

                System.out.println(jedis.sismember("users:500:follow", "200"));
                System.out.println(jedis.sismember("users:500:follow", "120"));

                System.out.println(jedis.scard("users:500:follow"));
                System.out.println(jedis.sinter("users:500:follow", "users:100:follow"));
            }
        }
    }

}
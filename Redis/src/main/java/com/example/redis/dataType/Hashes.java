package com.example.redis.dataType;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Hashes {
    public static void main(String[] args) {
        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
//                hash
                jedis.hset("users:2:info", "name", "jack2");

                var map = new HashMap<String, String>();
                map.put("email", "jack2@naver.com");
                map.put("phone", "010-2345-2345");

                jedis.hset("users:2:info", map);

                Map<String, String> hsetAll =  jedis.hgetAll("users:2:info");
                jedis.hincrBy("users:2:info", "visits", 1);
                hsetAll.forEach((s, s2) -> System.out.println(s +" : "+ s2));

            }
        }
    }
}

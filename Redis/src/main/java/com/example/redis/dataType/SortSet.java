package com.example.redis.dataType;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.HashMap;
import java.util.List;

public class SortSet {
    public static void main(String[] args) {
        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
                var scores = new HashMap<String, Double>();
                scores.put("user1", 100.0);
                scores.put("user2", 200.0);
                scores.put("user3", 300.0);
                scores.put("user4", 80.0);
                scores.put("user5", 15.0);

                jedis.zadd("game2:scores", scores);

//                기본 오름차순
                List<String> zrange = jedis.zrange("game2:scores", 0, Long.MAX_VALUE);
                zrange.forEach(System.out::println);

//                ++ name
                List<Tuple> tuples = jedis.zrangeWithScores("game2:scores", 0, Long.MAX_VALUE);
                tuples.forEach(System.out::println);
                tuples.forEach(i-> System.out.println("%s %s".formatted(i.getElement(), i.getScore())) );

//                  count
                System.out.println(jedis.zcard("game2:scores"));

                System.out.println(jedis.zincrby("game2:scores", 100.0, "user5"));
                List<Tuple> tuples2 = jedis.zrangeWithScores("game2:scores", 0, Long.MAX_VALUE);
                tuples2.forEach(i-> System.out.println("%s %s".formatted(i.getElement(), i.getScore())) );

            }
        }
    }
}

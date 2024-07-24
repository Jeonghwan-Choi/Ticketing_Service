package com.example.redis.dataType;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

public class BitMap {
    public static void main(String[] args) {
        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
                Pipeline pipeline = jedis.pipelined();
                IntStream.rangeClosed(0, 1000000).forEach(i -> {
                    pipeline.sadd("request-somepage-set-20230306", String.valueOf(i), "1");
                    pipeline.setbit("request-somepage-bit-20230306", i,true);

                    if(i == 1000) {
                        pipeline.sync();
                    }
                });
            }
        }
    }
}

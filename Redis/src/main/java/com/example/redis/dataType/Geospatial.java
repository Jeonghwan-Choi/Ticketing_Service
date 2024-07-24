package com.example.redis.dataType;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.params.GeoSearchParam;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;

public class Geospatial {
    public static void main(String[] args) {
        try (var jedisPool = new JedisPool("127.0.0.1", 6378)) {
            try (Jedis jedis = jedisPool.getResource()) {
                //geo add
                jedis.geoadd("stores2:geo", 127.02985530619755, 37.49911212874, "some1");
                jedis.geoadd("stores2:geo", 127.0333352287619, 37.491921163986234, "some2");

                //geo dist
                Double dist = jedis.geodist("stores2:geo", "some1", "some2");
                System.out.println(dist);

                //geo search
                List<GeoRadiusResponse> radiusResponses = jedis.geosearch(
                        "stores2:geo",
                        new GeoCoordinate(127.031, 37.495),
                        500,
                        GeoUnit.M
                );

                radiusResponses.forEach(response -> {
                    System.out.println(response.getMemberByString() );
                });

//                radiusResponses.forEach(response -> {
//                    System.out.println("%s %f %f".formatted(
//                            response.getMemberByString(),
//                            response.getCoordinate().getLatitude(),
//                            response.getCoordinate().getLongitude()
//                    ));
//                });


            }
        }
    }

}

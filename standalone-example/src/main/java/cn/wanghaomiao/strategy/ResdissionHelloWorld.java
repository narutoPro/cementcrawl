package cn.wanghaomiao.strategy;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class ResdissionHelloWorld {
    public static void main(String[] args) {
        Config config = new Config();

        config.useSingleServer().setAddress("127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);
        RMap map = redisson.getMap("myMap");



    }
}

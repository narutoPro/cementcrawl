package cn.chende.strategy;


import cn.chende.dao.mybatis.MybatisStoreDAO;
import cn.chende.model.Page;
import cn.chende.util.Consumer;
import cn.chende.util.PageStoreDBUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Main2 {
    static  Logger logger = LoggerFactory.getLogger(Main2.class);
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("seimi-mybatis.xml");
        MybatisStoreDAO dao=(MybatisStoreDAO)context.getBean("mybatisStoreDAO");
        Config config = new Config();
        //config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        String startUrl = "http://www.ccement.com/";
        logger.info("start to crawling ");

        BlockingQueue<String> urlQuene=redisson.getBlockingQueue("urls4");
        //记录已经访问的url
        Set<String> visitedUrls= Collections.newSetFromMap(new HashMap<>());
                //redisson.getSet("visitedUrls2");

        //另一个队列放 page
        BlockingQueue<Page> pages=redisson.getBlockingQueue("pages4");;

        try {
            urlQuene.put(startUrl);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Consumer consumers1=new Consumer(visitedUrls,pages,urlQuene);

        new Thread(consumers1).start();
        new Thread(new Consumer(visitedUrls,pages,urlQuene)).start();
        new Thread(new Consumer(visitedUrls,pages,urlQuene)).start();
        new Thread(new PageStoreDBUtil(pages,dao)).start();


    }
}

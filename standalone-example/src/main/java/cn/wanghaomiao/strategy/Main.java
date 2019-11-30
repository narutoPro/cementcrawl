package cn.wanghaomiao.strategy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String startUrl = "http://www.ccement.com/";
        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        BlockingQueue<String> urlQuene = new LinkedBlockingQueue<String>();
        data.put(startUrl, 1);
        urlQuene.put(startUrl);
        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data)).start();
    }

    public static void start(String startUrl, ConcurrentHashMap<String, Integer> data, BlockingQueue<String> urlQuene) {


    }
}

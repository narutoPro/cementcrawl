package cn.wanghaomiao.seimi.service;

import cn.wanghaomiao.seimi.utils.UrlConsumer;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class CrawlService {

    @Value("${startUrl}")
    String starUrl;

    List<UrlConsumer> consumers=new ArrayList<>();

    public void startCrawlData() throws InterruptedException {
        String startUrl = "http://www.ccement.com/";
        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        BlockingQueue<String> urlQuene = new LinkedBlockingQueue<String>();
        data.put(startUrl, 1);
        urlQuene.put(startUrl);
        new Thread(new UrlConsumer(urlQuene, data)).start();
    }

    public void stopCrawlData(){
        for (UrlConsumer urlConsumer:consumers
             ) {
            urlConsumer.setFlag(false);
        }
    }
}

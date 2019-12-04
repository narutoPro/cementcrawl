package cn.chende.seimi.service;

import cn.chende.seimi.model.Page;
import cn.chende.seimi.repository.PageRepository;
import cn.chende.seimi.utils.Consumer;
import cn.chende.seimi.utils.PageStoreDBUtil;
import cn.chende.seimi.utils.UrlConsumer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.redisson.api.RedissonClient;
import org.seimicrawler.xpath.JXDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

@Service
public class CrawlService {

    Logger logger = LoggerFactory.getLogger(CrawlService.class);

    @Value("${startUrl}")
    String starUrl;

    @Autowired
    RedissonClient redisson;

    @Autowired
    PageRepository pageRepository;
     List<UrlConsumer> consumers=new ArrayList<>();

     //网页处理分析
    public void analysisPage(Long id){
        Page page=pageRepository.findOne(id);
        JXDocument document=JXDocument.create(page.getHtmlText());
        Document doc= Jsoup.parse(page.getHtmlText());

         System.out.println(page.getHtmlText());


    }



    public void startCrawlData() throws InterruptedException {

        String startUrl = "http://www.ccement.com/";
        logger.info("start to crawling ");
        BlockingQueue<String> urlQuene=redisson.getBlockingQueue("urls");
        //记录已经访问的url
        Set<String> visitedUrls=redisson.getSet("visitedUrls");

        //另一个队列放 page
        BlockingQueue<Page> pages=redisson.getBlockingQueue("pages");;

        urlQuene.put(startUrl);
        Consumer consumers1=new Consumer(visitedUrls,pages,urlQuene);

        new Thread(consumers1).start();
        new Thread(new Consumer(visitedUrls,pages,urlQuene)).start();
        new Thread(new Consumer(visitedUrls,pages,urlQuene));
        new Thread(new PageStoreDBUtil(pages,pageRepository)).start();

    }

    public void stopCrawlData(){
        for (UrlConsumer urlConsumer:consumers
             ) {
            urlConsumer.setFlag(false);
        }
    }
}

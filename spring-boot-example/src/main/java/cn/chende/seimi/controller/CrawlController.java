package cn.chende.seimi.controller;

import cn.chende.seimi.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlController {
    @Autowired
    CrawlService crawlService;

    @RequestMapping("/start")
    public String start(){
        try {
            crawlService.startCrawlData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "start";
    }
    @RequestMapping("/stop")
    public String stop(){
        crawlService.stopCrawlData();
        return  "stop";
    }
}

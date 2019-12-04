package cn.chende.seimi.crawlers;


import cn.chende.seimi.model.Page;
import cn.chende.seimi.repository.PageRepository;
import cn.chende.seimi.annotation.Crawler;
import cn.chende.seimi.def.BaseSeimiCrawler;
import cn.chende.seimi.def.DefaultRedisQueue;
import cn.chende.seimi.model.Urls;
import cn.chende.seimi.repository.UrlsRepository;
import cn.chende.seimi.struct.Request;
import cn.chende.seimi.struct.Response;
import cn.chende.seimi.utils.Urlutil;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 爬取水泥网网页
 */
@Crawler(name = "cement",queue = DefaultRedisQueue.class,useUnrepeated = false)
public class CementPage extends BaseSeimiCrawler {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    UrlsRepository urlsRepository;
    @Override
    public String[] startUrls() {
        pageRepository.deleteAll();
        urlsRepository.deleteAll();
        return new String[]{"http://www.ccement.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();

            List<Object> urls = doc.sel("//a/@href");
            logger.info("{}", urls.size());
            for (Object s:urls){
                String u=s.toString().trim();
                if (!Urlutil.goodUrl(u)){continue;}
                if (Urlutil.haveVisitedUrl(u)) {
                continue;
                }
                push(Request.build(u,CementPage::getTitle));
                Urlutil.visitUrl(u);

            }

    }
    public void getTitle(Response response){
       // JXDocument doc = response.document();

            Page page=new Page();
            page.setUrl(response.getRealUrl());
            page.setHtmlText(response.getContent());
            pageRepository.save(page);

            Urls url=new Urls();
            url.setUrl(page.getUrl());
            url.setPageId(page.getId());
            urlsRepository.save(url);
            urlsRepository.flush();

            pageRepository.flush();
          //  logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
          logger.info("save page{}--> {}",page.getId(),page.getUrl().substring(0,20));
            //do something

    }
}

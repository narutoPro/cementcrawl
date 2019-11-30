package cn.wanghaomiao.seimi.crawlers;


import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.model.Page;
import cn.wanghaomiao.seimi.repository.PageRepository;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
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
    @Override
    public String[] startUrls() {
        return new String[]{"http://www.ccement.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a/@href");
            logger.info("{}", urls.size());
            for (Object s:urls){
                push(Request.build(s.toString(),CementPage::getTitle));
                String u=s.toString().trim();
                logger.info("cement start:"+u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            Page page=new Page();
            page.setUrl(response.getRealUrl());
            page.setHtmlText(response.getContent());
            pageRepository.save(page);
            pageRepository.flush();
          //  logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
          logger.info("save page:"+page.getHtmlText());
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cn.wanghaomiao.seimi.crawlers;


import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.model.Page;
import cn.wanghaomiao.seimi.model.Urls;
import cn.wanghaomiao.seimi.repository.PageRepository;
import cn.wanghaomiao.seimi.repository.UrlsRepository;
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
    @Autowired
    UrlsRepository urlsRepository;
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
                String u=s.toString().trim();
                if (!u.contains("cement")){
                    logger.info("url{} dont contain 'cement' ",u);
                    continue;
                }
                Urls urls1=urlsRepository.findOneByUrlEquals(u);
                if (urls1==null || urls1.getUrl()==null)
                {
                    logger.info("find same url:{}",u);
                    continue;}
                push(Request.build(u,CementPage::getTitle));


            }
        } catch (Exception e) {
            e.printStackTrace();
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

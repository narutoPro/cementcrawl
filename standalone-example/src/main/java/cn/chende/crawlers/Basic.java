package cn.chende.crawlers;

import cn.chende.seimi.annotation.Crawler;
import cn.chende.seimi.struct.Request;
import cn.chende.seimi.struct.Response;
import cn.chende.seimi.def.BaseSeimiCrawler;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2015/10/21.
 */
@Crawler(name = "basic")
public class Basic extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        //两个是测试去重的
        return new String[]{"http://www.cnblogs.com/","http://www.cnblogs.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
            logger.info("{}", urls.size());
            for (Object s:urls){
                push(Request.build(s.toString(),Basic::getTitle)
                        .useSeimiAgent()
                        .setSeimiAgentRenderTime(205000)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getTitle(Response response){
        JXDocument doc = response.document();
        System.out.println(response.getContent());
        try {
            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));

            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

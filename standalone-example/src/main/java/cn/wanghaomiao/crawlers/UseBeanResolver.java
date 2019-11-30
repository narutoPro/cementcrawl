package cn.wanghaomiao.crawlers;

import cn.wanghaomiao.dao.mybatis.MybatisStoreDAO;
import cn.wanghaomiao.model.BlogContent;
import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2015/10/21.
 */
@Crawler(name = "BeanResolver")
public class UseBeanResolver extends BaseSeimiCrawler {
    @Autowired
    MybatisStoreDAO mybatisStoreDAO;
    @Override
    public String[] startUrls() {
        return new String[]{"http://www.cnblogs.com/"};
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
            logger.info("{}", urls.size());
            for (Object s:urls){
                push(Request.build(s.toString(),UseBeanResolver::renderBean));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void renderBean(Response response){
        try {
            BlogContent blog = response.render(BlogContent.class);
            //接下来拿着个bean可以该干嘛干嘛去了，比如存储到DB中
            mybatisStoreDAO.save(blog);
            logger.info("bean resolve res:{}",blog);
            logger.info(blog.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

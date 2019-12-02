package cn.wanghaomiao.dao.mybatis;

import cn.wanghaomiao.model.BlogContent;
import cn.wanghaomiao.model.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author 汪浩淼 et.tw@163.com
 * @since 2016/7/27.
 */
public interface MybatisStoreDAO {

    @Insert("insert into blog (title,content,update_time) values (#{blog.title},#{blog.content},now())")
    @Options(useGeneratedKeys = true, keyProperty = "blog.id")
    int save(@Param("blog") BlogContent blog);

    @Insert("insert into page(id,url,html_text) values (#{page.id},#{page.url},#{page.htmlText})")
    //@Options(useGeneratedKeys = true, keyProperty = "page.id")
    long savePage(@Param("page") Page page);
}

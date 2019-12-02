package cn.wanghaomiao.strategy;

import cn.wanghaomiao.dao.mybatis.MybatisStoreDAO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Resource resource = new ClassPathResource("seimi-mybatis.xml");
//        BeanFactory factory = new XmlBeanFactory(resource);
//        factory.getBean("mybatisStoreDAO");
        ApplicationContext context=new ClassPathXmlApplicationContext("seimi-mybatis.xml");
        MybatisStoreDAO dao=(MybatisStoreDAO)context.getBean("mybatisStoreDAO");

        String startUrl = "http://www.ccement.com/";
        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        BlockingQueue<String> urlQuene = new LinkedBlockingQueue<String>();
        data.put(startUrl, 1);
        urlQuene.put(startUrl);
//        new Thread(new UrlConsumer(urlQuene, data)).start();
//        new Thread(new UrlConsumer(urlQuene, data)).start();
//        new Thread(new UrlConsumer(urlQuene, data)).start();
//        new Thread(new UrlConsumer(urlQuene, data)).start();
//        new Thread(new UrlConsumer(urlQuene, data)).start();
        new Thread(new UrlConsumer(urlQuene, data,dao)).start();
    }

    public static void start(String startUrl, ConcurrentHashMap<String, Integer> data, BlockingQueue<String> urlQuene) {


    }
}

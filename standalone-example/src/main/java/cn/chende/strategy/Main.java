package cn.chende.strategy;

import cn.chende.dao.mybatis.MybatisStoreDAO;
import cn.chende.model.Page;
import cn.chende.util.ReadyToStoreDBUtil;
import cn.chende.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
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
       WebDriver driver= WebDriverUtil.getDriver();

       driver.get(startUrl);
       // List<WebElement> elements= driver.findElements(By.xpath("//a/@href"));
        List<WebElement> elements=driver.findElements(By.tagName("a"));
        for (WebElement e:elements
             ) {
            String hrf=e.getAttribute("href");
            System.out.println(e.getText()+" --->"+hrf);
        }

        ConcurrentHashMap<String, Integer> data = new ConcurrentHashMap<>();
        BlockingQueue<String> urlQuene = new LinkedBlockingQueue<String>();
        BlockingQueue<Page> readyToStoreToDB = new LinkedBlockingQueue<Page>();
        data.put(startUrl, 1);
        urlQuene.put(startUrl);
//        new Thread(new UrlConsumer(urlQuene, data)).start();

 //       new Thread(new UrlConsumer(urlQuene, data,dao,readyToStoreToDB)).start();

 //       new Thread(new ReadyToStoreDBUtil(readyToStoreToDB,dao)).start();
    }

    public static void start(String startUrl, ConcurrentHashMap<String, Integer> data, BlockingQueue<String> urlQuene) {


    }
}

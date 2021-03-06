package cn.chende.strategy;

import cn.chende.dao.mybatis.MybatisStoreDAO;
import cn.chende.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seimicrawler.xpath.JXDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class UrlConsumer implements Runnable {

    Logger logger = LoggerFactory.getLogger(UrlConsumer.class);

    private ConcurrentHashMap<String,Integer> dataStatics;
    private BlockingQueue<String> urlQuene;
    BlockingQueue<Page> pages;
    private WebDriver driver;

    private MybatisStoreDAO dao;

    private volatile boolean flag=false;

    public UrlConsumer(BlockingQueue<String> urls ,ConcurrentHashMap<String,Integer> dataStatics,MybatisStoreDAO dao,BlockingQueue<Page> pages) {
        this.dao=dao;
        this.pages=pages;
        this.urlQuene = urls;
        this.dataStatics=dataStatics;
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);//todo 设置渲染超时时间
    }

    /**
     * 消费者同时也是生产者
     */
    @Override
    public void run() {
        logger.info("线程开始运行！！！"+Thread.currentThread().getName());
        while (true) {
            try {
                String url=urlQuene.take();
                driver.get(url);
                Document document= Jsoup.parse(driver.getPageSource());

                Page page=new Page();
                page.setUrl(url);
                page.setHtmlText(document.html());
               // dao.savePage(page);
                pages.put(page);


                JXDocument doc=JXDocument.create(document);
                List<Object> urls = doc.sel("//a/@href");
                for (Object urlInPage:urls
                     ) {
                    String s=urlInPage.toString().trim();
                    if (isUrl(s)){
                        if (dataStatics.containsKey(s)){
                            dataStatics.put(s,dataStatics.get(s)+1);
                          //  logger.info(Thread.currentThread().getName()+"  :"+s);
                        }
                        else {
                            if (s.contains("cement.com")){
                            dataStatics.put(s,1);
                            urlQuene.put(s);
                            logger.info(Thread.currentThread().getName()+"  添加链接:"+s);}
                        }
                    }
                }
                logger.info("目前队列里链接数"+urlQuene.size()+"  已经处理的链接数据"+dataStatics.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static boolean isUrl(String urlString){
        try  {
            URL url =  new  URL( urlString );


        }  catch  (MalformedURLException e) {
            return false;
        }
        return true;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

package cn.chende.strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seimicrawler.xpath.JXDocument;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCementDemo {

    static  String url="http://www.ccement.com/";
    static  int  count=0; //统计水泥网首页链接数
    static ConcurrentHashMap<String,Integer> data=new ConcurrentHashMap<>();

    //尝试二次爬取  将url放入阻塞队列里面
    BlockingQueue<String>  urlQuene=new LinkedBlockingQueue<String>();
    public void start(){

    }

    /**
     *
     */
    public void consume(){
        String url;
        try {
             url=urlQuene.take();
          //查询该页面是否已经访问过
            // 已经访问过 则记录被引用/链接的信息
            // 分析网页内容 与主题的关系度：
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.get(url);   //todo 设置渲染超时时间

        Document document= Jsoup.parse(driver.getPageSource());

        JXDocument doc=JXDocument.create(document);
        List<Object> urls = doc.sel("//a/@href");
        for (Object url:urls
        ) {
            isUrl(url.toString());
          //  System.out.println(url.toString());
//            if (isUrl(url.toString())){
//                driver.get(url.toString());
//                 System.out.println(url+" ---->  "+driver.getTitle());
//            }

        }
        System.out.println("总共链接数字"+count);
        driver.quit();
    }

    public static  boolean isUrl(String url){
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
        String regex2 = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式

        Pattern patt = Pattern.compile(regex2 );
        Matcher matcher = patt.matcher(url);
        boolean  isMatch = matcher.matches();
        if  (!isMatch) {
            System.out.println( "您输入的URL地址不正确"+url );
            return false;
        } else {
            count++;
            return true;
        }
    }
    //394 总共链接数字406

    /**
     * 还是这个好用,但会产生很多URL对象 先不管！
     * 官方的权威，正则有些情况不对
     * @param urlString
     * @return
     */
    public static boolean isUrl2(String urlString){
        try  {
            URL url =  new  URL( urlString );
            System.out.println(urlString);
            count++;
        }  catch  (MalformedURLException e) {
            return false;
        }
        return true;
    }
}

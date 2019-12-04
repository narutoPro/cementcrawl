package cn.chende.strategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

public class BaseTest {

    static String url="https://www.cnblogs.com/yifachen/p/11929652.html";
    static  String url2="http://www.ccement.com/";

    public static void main(String[] args) {
        // 设置ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        for (int i=0;i<20;i++){

            driver.get(url);
            Document document1=Jsoup.parse(driver.getPageSource());
            Element e1=document1.getElementById("post_next_prev");
            System.out.println("e1:"+e1.html());
        }


        Document document=Jsoup.parse(driver.getPageSource());
        Element e2=document.getElementById("post_next_prev");
        System.out.println("e2:"+e2.html());
        JXDocument doc=JXDocument.create(document);
        List<Object> urls = doc.sel("//a/@href");
        for (Object url:urls
             ) {
            System.out.println(url.toString());
        }

//        WebElement searchBox = driver.findElement(By.id("kw"));
//
//        searchBox.sendKeys("水泥熟料热耗");
//        searchBox.submit();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
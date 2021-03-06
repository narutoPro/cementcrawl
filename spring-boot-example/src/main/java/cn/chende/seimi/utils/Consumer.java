package cn.chende.seimi.utils;



import cn.chende.seimi.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.seimicrawler.xpath.JXDocument;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    WebDriver driver=WebDriverUtil.getDriver();
    private volatile boolean flag = true;
    //记录已经访问的url
    Set<String> visitedUrls;

    public Consumer(Set<String> visitedUrls, BlockingQueue<Page> pages, BlockingQueue<String> urlQuene) {
        this.visitedUrls = visitedUrls;
        this.pages = pages;
        this.urlQuene = urlQuene;
    }

    //另一个队列放 page
    BlockingQueue<Page> pages;

    BlockingQueue<String> urlQuene;

    @Override
    public void run() {
        while(flag) {
            String url = null;
            try {
                url = urlQuene.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  if(visitedUrls.contains(url) )continue;
            visitedUrls.add(url);
            driver.get(url);
            Document document = Jsoup.parse(driver.getPageSource());

            Page page=new Page();
            page.setUrl(url);
            page.setHtmlText(document.html());
            try {
                pages.put(page);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JXDocument doc = JXDocument.create(document);

            List<Object> urls = doc.sel("//a/@href");
            for (Object urlInPage : urls ) {
                String s = urlInPage.toString().trim();
                if (!visitedUrls.contains(s)) visitedUrls.add(s);
            }
        }

    }

    public static boolean isUrl(String urlString) {
        try {
            URL url = new URL(urlString);


        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}

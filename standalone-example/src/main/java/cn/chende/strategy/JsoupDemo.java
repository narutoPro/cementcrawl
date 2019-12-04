package cn.chende.strategy;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupDemo {

    static  String url="https://www.cnblogs.com/yoke/p/11536871.html";
    static  String url2="http://www.ccement.com/";
    static  String url3="http://www.ccement.com/";
    public static void main(String[] args) {
        try {
            Document doc= Jsoup.connect(url2).get();
            System.out.println("doc title:"+doc.title());
         //   doc.getAllElements()

            System.out.println("outhtml####");
            System.out.println(doc.text());
         Elements links=   doc.getElementsByTag("a");
         for (Element link:links){

             String linkHref=link.attr("href");
             System.out.println("href:"+linkHref);
             String linktext=link.text();
             System.out.println("linktext "+linktext);
         }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package cn.chende.seimi.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class Urlutil {

    private static  ConcurrentHashMap<String,Integer> urlRecord=new ConcurrentHashMap<>();

    public  static  boolean goodUrl(String url){
        URL u;
        try {
             u=new URL(url);
        } catch (MalformedURLException e) {
          //  e.printStackTrace();
            u=null;
            return false;
        }
        return true;
    }

    public static boolean haveVisitedUrl( String url ){
        if (urlRecord.containsKey(url)){return true;}

        return false;
    }

    public static void  visitUrl(String url){
        urlRecord.put(url,1);
    }
}

package cn.wanghaomiao.strategy;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 先保存在内存 ，数据库后面在弄
 */
public class DataBase {
  static   private ConcurrentHashMap<String,Object> data;

    private DataBase(){}

    public static synchronized Map getInstance(){
        if (data==null) {data =new ConcurrentHashMap<String,Object>();}
        return data;
    }
}

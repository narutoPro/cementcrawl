package cn.chende.util;

import cn.chende.dao.mybatis.MybatisStoreDAO;
import cn.chende.model.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReadyToStoreDBUtil implements Runnable {
    Logger logger = LoggerFactory.getLogger(ReadyToStoreDBUtil.class);

    BlockingQueue<Page> readyToStoreToDB ;
    private MybatisStoreDAO dao;
    private volatile boolean flag=true;
    public ReadyToStoreDBUtil(BlockingQueue<Page> readyToStoreToDB,MybatisStoreDAO dao){
        this.dao=dao;
        this.readyToStoreToDB=readyToStoreToDB;}

    @Override
    public void run() {
        List<Page> pages=new ArrayList<>();
        while(flag){
            pages.clear();
            for (int i=0;i<100;i++){
                try {
                    pages.add(readyToStoreToDB.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dao.insertPageList(pages);

            logger.info("save {} pages to db",pages.size());
        }
    }
}

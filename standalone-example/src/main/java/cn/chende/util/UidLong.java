package cn.chende.util;

import java.util.concurrent.atomic.AtomicLong;

public class UidLong {

    private  static AtomicLong atomicLong=new AtomicLong(2);
    public static Long uuidLong(){
      return atomicLong.getAndIncrement();
    }
}

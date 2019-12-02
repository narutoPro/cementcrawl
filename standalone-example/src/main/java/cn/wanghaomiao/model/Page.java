package cn.wanghaomiao.model;

import cn.wanghaomiao.util.UidLong;
import lombok.Data;


import java.util.UUID;

@Data
public class Page {

    public Page(){
       this.id= UidLong.uuidLong();
    }
    private Long id;

    private String url;

    private String htmlText;
}

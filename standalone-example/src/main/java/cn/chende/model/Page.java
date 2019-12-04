package cn.chende.model;

import cn.chende.util.UidLong;
import lombok.Data;


public class Page {

    public Page(){
       this.id= UidLong.uuidLong();
    }
    private Long id;

    private String url;

    private String htmlText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }
}

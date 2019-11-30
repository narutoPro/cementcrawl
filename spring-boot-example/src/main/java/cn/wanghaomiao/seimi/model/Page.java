package cn.wanghaomiao.seimi.model;

import javax.persistence.*;


@Entity
@Table
public class Page {


    @Id
    @GeneratedValue
    private Long id;

    private String url;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text",nullable = true)
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

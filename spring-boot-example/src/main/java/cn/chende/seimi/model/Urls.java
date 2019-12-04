package cn.chende.seimi.model;


import lombok.Data;

import javax.persistence.*;

/**
 * 记录已经访问的url
 */
@Data
@Entity
@Table(name="urls",uniqueConstraints = {@UniqueConstraint(columnNames = {"url"})})
public class Urls {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    //对应的 page id
    private Long pageId;
}

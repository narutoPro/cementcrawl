package cn.chende.seimi.spring.boot;

import cn.chende.seimi.Constants;
import cn.chende.seimi.annotation.EnableSeimiCrawler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/5/7.
 */
@Configuration
@ConditionalOnProperty(name = {Constants.SEIMI_CRAWLER_BOOTSTRAP_ENABLED})
@EnableConfigurationProperties({CrawlerProperties.class})
@ComponentScan({"**/crawlers", "**/queues", "**/interceptors", "cn.wanghaomiao.seimi"})
@EnableSeimiCrawler
public class SeimiCrawlerAutoConfiguration {
}

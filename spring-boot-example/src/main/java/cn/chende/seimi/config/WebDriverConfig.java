package cn.chende.seimi.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
                //可以用"rediss://"来启用SSL连接
              //  .addNodeAddress("redis://127.0.0.1:7181");
        RedissonClient redisson = Redisson.create(config);

        return redisson;

    }

    public WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        return driver;
    }
}

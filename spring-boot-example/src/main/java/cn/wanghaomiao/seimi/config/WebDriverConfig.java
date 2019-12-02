package cn.wanghaomiao.seimi.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {


    public WebDriver webDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        return  driver;
    }
}

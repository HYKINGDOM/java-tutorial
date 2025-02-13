package com.java.tutorial.project.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author kscs
 */

public class SeleniumService {

    /**
     * 获取浏览器的连接
     *
     * @return
     */
    public static WebDriver openAccess() {

        String driverPath =
            System.getProperty("user.dir") + File.separator + "driver" + File.separator + "chromedriver.exe";

        //在idea运行的谷歌驱动路径
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        //打jar包后的谷歌驱动路径
        /*String driverPath = System.getProperty("user.dir")+File.separator+"driver"+ File.separator+"chromedriver.exe";*/
        HashMap<String, Object> chromePrefs = new HashMap<>();
        //设置为禁止弹出下载窗口
        chromePrefs.put("profile.default_content_settings.popups", 0);
        //设置为文件下载路径
        chromePrefs.put("download.default_directory", "D:\\download");
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        //取消Chrome正在受到自动测试软件的控制
        options.addArguments("disable-infobars");
       /*
        用户浏览器地址，用于加载浏览器的用户信息，
        这一步将增加浏览器的性能消耗，
        如果不加这一行，浏览器默认已访客模式进入浏览器，
        可根据自己的需求来选择是否使用
         */
        options.addArguments("user-data-dir=C:\\Users\\KSCS\\AppData\\Local\\Google\\Chrome\\User Data");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = null;
        boolean flag = true;
        while (flag) {
            try {
                flag = false;
                driver = new ChromeDriver(cap);
                //响应时间超过8秒，则重新开启浏览器连接
                driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                //                driver.get(url);

            } catch (Exception e) {
                flag = true;
                if (driver != null) {
                    driver.quit();
                }
                System.out.println("wait for connection browser ");
            }
        }
        return driver;
    }

}

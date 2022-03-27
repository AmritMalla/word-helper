package com.amrit.wordhelper.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;


public class WebDriverUtility {


    public static WebDriver operaWebDriver() {
        System.setProperty("webdriver.opera.driver", "lib/driver/operadriver.exe");
        return new OperaDriver();
    }

    public static WebDriver operaWebDriver(String url){
        System.setProperty("webdriver.opera.driver", "lib/driver/operadriver.exe");
        OperaDriver operaDriver = new OperaDriver();
        operaDriver.get(url);
        return operaDriver;
    }

}

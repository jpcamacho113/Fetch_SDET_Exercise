package com.weightscale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScaleClass {

    static public void driver_Start(WebDriver driver) throws InterruptedException {
        driver.manage().window().fullscreen();
        driver.get("http://sdetchallenge.fetch.com/");
        driver.wait(1000);
        driver_Close(driver);
    }

    static public void driver_Close(WebDriver driver) {
        driver.close();
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        System.out.println("hello");
        driver_Start(driver);
    }
    
}
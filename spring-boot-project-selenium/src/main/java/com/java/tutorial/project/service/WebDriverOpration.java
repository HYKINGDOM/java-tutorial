package com.java.tutorial.project.service;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public class WebDriverOpration {

    public static void main(String[] args) throws InterruptedException, AWTException {

        System.setProperty("java.awt.headless", "false");

        String url = "http://www.google.com";
        WebDriver webDriver = SeleniumService.openAccess();

        webDriver.get(url);
        Thread.sleep(1000);
        //根绝class寻找元素，并且点击
        WebElement addpBtn = webDriver.findElement(By.className("addp"));
        addpBtn.click();

        Thread.sleep(1000);
        //根据id寻找元素，并且点击
        WebElement genLayoutBtn = webDriver.findElement(By.id("genLayout"));
        genLayoutBtn.click();

        //performKeys(webDriver);

        robotKeys();

        String currentWin = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (currentWin.equals(handle)) {
                continue;
            }
            webDriver = webDriver.switchTo().window(handle);
        }
        webDriver.close();

    }

    private static void robotKeys() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //睡眠7S，因为谷歌浏览器进入打印要先进行渲染，这个需要一段时间
        Thread.sleep(7000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    private static void performKeys(WebDriver webDriver) {
        Actions action = new Actions(webDriver);
        // 按下 Ctrl 键
        action.keyDown(Keys.CONTROL);
        action.sendKeys("p");
        // 释放 Ctrl 键
        action.keyUp(Keys.CONTROL);
        //发送组合按键
        action.perform();
    }
}

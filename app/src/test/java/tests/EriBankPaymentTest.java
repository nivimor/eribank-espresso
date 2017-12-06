package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class EriBankPaymentTest{
    private String testName = "EriBank CICD - Payment";
    protected AndroidDriver<AndroidElement> driver = null;
    //private String accessKey = System.getenv("SEETEST_IO_ACCESS_KEY");
    private String accessKey = "eyJ4cC51Ijo5OSwieHAucCI6MiwieHAubSI6Ik1UVXhNalUzTXpRMk16YzNNUSIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE4Mjc5MzQ0OTgsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.WFdCTeO1H9IO81CRNtf5SVuWUph2jvAe4ct5Jg2qhoI";

    DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws MalformedURLException {
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("fullReset", true);
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cloud:com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        dc.setCapability("deviceQuery", "@os='android'");
        driver = new AndroidDriver<AndroidElement>(new URL("https://sales.experitest.com:443/wd/hub"), dc);
    }

    @Test
    public void testUntitled() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id='countryTextField']")).sendKeys("US");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='button1']")).click();
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
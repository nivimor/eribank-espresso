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
import io.appium.java_client.remote.MobileCapabilityType;

public class EriBankPaymentTest{
    private String testName = "EriBank CICD - Added Countries to the List in Payment Screen";
    private String accessKey = System.getenv("SEETEST_IO_ACCESS_KEY");

    DesiredCapabilities dc = new DesiredCapabilities();
    protected AndroidDriver<AndroidElement> driver = null;

    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("fullReset", true);
        dc.setCapability("instrumented", true);
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "cloud:com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        dc.setCapability("deviceQuery", "@os='android' and @manufacture='samsung'");
        driver = new AndroidDriver<AndroidElement>(new URL("https://cloud.seetest.io:443/wd/hub"), dc);

    }

    @Test
    public void chooseCountry() {

        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id='countryTextField']")).sendKeys("US");
        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='button1']")).click();

    }


    @After
    public void tearDown() {
       driver.quit();
    }

}
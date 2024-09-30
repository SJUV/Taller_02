package taller_02;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrearContactosTest {
    private static AppiumDriver android;
    @BeforeAll
    public static void setup() throws MalformedURLException {
        DesiredCapabilities config = new DesiredCapabilities();
        config.setCapability("appium:deviceName","Galaxy A51");
        config.setCapability("appium:platformVersion","13.0");
        config.setCapability("appium:appPackage","com.vrproductiveapps.whendo");
        config.setCapability("appium:appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        config.setCapability("platformName","Android");
        config.setCapability("appium:automationName","uiautomator2");
        android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),config);
        android.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    @Order(1)
    public void verifyCreateTaskContact() throws InterruptedException {
        String nameTask1 = "Eli" + new Date().getTime();
        String nameTask2 = "Shirly" + new Date().getTime();

        // click [+] button (add task)
        android.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // type [title]
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(nameTask1);
        // type [notes]
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Mama");
        // click [saveList] button
        android.findElement(By.id("com.vrproductiveapps.whendo:id/saveAndNew")).click();
        // click [+] button (add task)  String nameTask1 = "Eli" + new Date().getTime();
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(nameTask2);
        // type [notes]
        android.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Hija");
        // click [save] button
        android.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        //verification 1
        String expectedResult1 = nameTask1;
        String actualResult1 = android.findElement(By.xpath("//android.widget.TextView[@text='" + nameTask1 + "']")).getText();
        Assertions.assertEquals(expectedResult1, actualResult1, "ERROR ! no se creo el contacto");
        String expectedResult2 = nameTask2;
        String actualResult2 = android.findElement(By.xpath("//android.widget.TextView[@text='" + nameTask2 + "']")).getText();
        Assertions.assertEquals(expectedResult2, actualResult2, "ERROR ! no se creo el contacto");
    }
    @Test
    @Order(2)
    public void buscarContacto () throws InterruptedException
        {
            String nameb = "Eli";
            android.findElement(By.id("com.vrproductiveapps.whendo:id/search")).click();
            android.findElement(By.id("com.vrproductiveapps.whendo:id/search_src_text")).sendKeys(nameb);
            android.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
            System.out.println("Eli"+nameb);
        }


   @AfterAll
    public static void closeApp(){
       android.quit();
    }

}

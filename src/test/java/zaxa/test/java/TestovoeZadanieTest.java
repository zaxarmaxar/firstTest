package zaxa.test.java;

import java.lang.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestovoeZadanieTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setup() {
        String driverpath = ("C:/Users/zaxar/Desktop/Selenium/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driverpath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://team.cft.ru/triangle/zadanie/triangle.html?token=$2a$10$lIotrTwEfTfUw.inbxn0Kuv3pZSegv9TkSfAHntnlyr4jJ8rw8miu");

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}


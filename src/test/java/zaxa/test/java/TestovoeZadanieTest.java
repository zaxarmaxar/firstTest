package zaxa.test.java;

import java.lang.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import java.util.concurrent.TimeUnit;

public class TestovoeZadanieTest {

    private static WebDriver driver;

    By inputALocator = By.xpath("(//input[@type='text'])[1]");
    By inputBLocator = By.xpath("(//input[@type='text'])[2]");
    By inputCLocator = By.xpath("(//input[@type='text'])[3]");
    By okButtonLocator = By.xpath("//div[@id='content']/div/div/table/tbody/tr[4]/td/button");
    By resultLocator = By.xpath("//div[@id='content']/div/div/table/tbody/tr[5]/td/div");
    By errorALocator = By.xpath("//div[@id='content']/div/div/table/tbody/tr/td[2]/div");
    By errorBLocator = By.xpath("//div[@id='content']/div/div/table/tbody/tr[2]/td[2]/div");
    By errorCLocator = By.xpath("//div[@id='content']/div/div/table/tbody/tr[3]/td[2]/div");

    WebElement inputFieldA = driver.findElement(inputALocator);
    WebElement inputFieldB = driver.findElement(inputBLocator);
    WebElement inputFieldC = driver.findElement(inputCLocator);
    WebElement okButton = driver.findElement(okButtonLocator);


    WebElement errorC = driver.findElement(errorCLocator);

    private SoftAssert softAssert = new SoftAssert();


    @BeforeClass
    public static void setup() {
        String driverpath = ("C:/Users/zaxar/Desktop/Selenium/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", driverpath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://team.cft.ru/triangle/zadanie/triangle.html?token=$2a$10$lIotrTwEfTfUw.inbxn0Kuv3pZSegv9TkSfAHntnlyr4jJ8rw8miu");

    }

    private void triangleFormHelper(String valueA, String valueB, String valueC) {
        inputFieldA.sendKeys(valueA);
        inputFieldB.sendKeys(valueB);
        inputFieldC.sendKeys(valueC);
        okButton.click();
    }

    private String findAnswer(){
        WebElement answer = driver.findElement(resultLocator);
        return answer.getText();
    }

    private String validationA() {
        WebElement errorA = driver.findElement(errorALocator);
        return errorA.getText();
    }

    private String validationB() {
        WebElement errorB = driver.findElement(errorBLocator);
        return errorB.getText();
    }
    private String validationC() {
        WebElement errorC = driver.findElement(errorCLocator);
        return errorC.getText();
    }


    @After
    public void clearing() {
        inputFieldA.clear();
        inputFieldB.clear();
        inputFieldC.clear();
    }


    @Test
    public void IsoscelesTest() {
        this.triangleFormHelper("100", "100", "90");
        String result = this.findAnswer();
        Assert.assertEquals("Равнобедренный", result);
    }
    @Test
    public void NotTriangleTest() {
        this.triangleFormHelper("0", "0", "0");
        String result = this.findAnswer();
        Assert.assertEquals("Не треугольник", result);
    }
    @Test
    public void NotTriangleTest2() {
        this.triangleFormHelper("100", "2", "1");
        String result = this.findAnswer();
        Assert.assertEquals("Не треугольник", result);
    }
    @Test
    public void RectangularTest() {
        this.triangleFormHelper("3", "4", "5");
        String result = this.findAnswer();
        Assert.assertEquals("Прямоугольный", result);
    }
    @Test
    public void SimpleTest() {
        this.triangleFormHelper("100", "90", "80");
        String result = this.findAnswer();
        Assert.assertEquals("Разносторонний" , result);
    }
    @Test
    public void EquilateralTest() {
        this.triangleFormHelper("100", "100", "100");
        String result = this.findAnswer();
        assertThat(result, anyOf(is("Равносторонний"), is("Равнобедренный")));
        }
    @Test
    public void EmptyFieldATest() {
        this.triangleFormHelper("", "", "");
        String resultA = this.validationA();
        String resultB = this.validationB();
        String resultC = this.validationC();
        softAssert.assertEquals(resultA, "Поле не должно быть пустым");
        softAssert.assertEquals(resultB, "Поле не должно быть пустым");
        softAssert.assertEquals(resultC, "Поле не должно быть пустым");
        softAssert.assertAll();
    }
    @Test
    public void SpeciaSymbolATest() {
        this.triangleFormHelper("@#$", "@#$", "@#$");
        String resultA = this.validationA();
        String resultB = this.validationB();
        String resultC = this.validationC();
        softAssert.assertEquals(resultA, "'@#$'"+"не является допустимым числом");
        softAssert.assertEquals(resultB, "'@#$'"+"не является допустимым числом");
        softAssert.assertEquals(resultC, "'@#$'"+"не является допустимым числом");
        softAssert.assertAll();
}
//    @Test
//    public void LettersATest() {
//        String result = this.validationA("@#$", "", "");
//        Assert.assertEquals("Поле не должно быть пустым" , result);
//    }



    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}


package zaxa.test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TriangleFormPage extends PageObject {

    @FindBy(xpath = ("(//input[@type='text'])[1]"))
    private WebElement inputFieldA;

    @FindBy(xpath = ("(//input[@type='text'])[2]"))
    private WebElement inputFieldB;

    @FindBy(xpath = ("(//input[@type='text'])[3]"))
    private WebElement inputFieldC;

    @FindBy(xpath = ("//div[@id='content']/div/div/table/tbody/tr[4]/td/button"))
    private WebElement okButton;

    @FindBy(xpath = (("//div[@id='content']/div/div/table/tbody/tr[5]/td/div")))
    private WebElement answer;

    @FindBy(xpath = ("//div[@id='content']/div/div/table/tbody/tr/td[2]/div"))
    private WebElement errorA;

    @FindBy(xpath = ("//div[@id='content']/div/div/table/tbody/tr[2]/td[2]/div"))
    private WebElement errorB;

    @FindBy(xpath = ("//div[@id='content']/div/div/table/tbody/tr[3]/td[2]/div"))
    private WebElement errorC;

    public TriangleFormPage(WebDriver driver) {
        super(driver);
    }

    public void triangleFormHelper(String valueA, String valueB, String valueC) {
        inputFieldA.sendKeys(valueA);
        inputFieldB.sendKeys(valueB);
        inputFieldC.sendKeys(valueC);
        okButton.click();
    }

    public void clearing() {
        inputFieldA.clear();
        inputFieldB.clear();
        inputFieldC.clear();
    }

    public String findAnswer(){
        return answer.getText();
    }

    public String validationA() {
        return errorA.getText();
    }

    public String validationB() {
        return errorB.getText();
    }
    public String validationC() {
        return errorC.getText();
    }

}




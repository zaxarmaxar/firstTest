package zaxa.test.java;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.asserts.SoftAssert;

public class FunctionalTests extends TestovoeZadanieTest {

    private SoftAssert softAssert = new SoftAssert();
    TriangleFormPage triangleFormPage = new TriangleFormPage(driver);

    @After
    public void clear() {
        triangleFormPage.clearing();
    }

    @Test
    public void isoscelesTest() {
        triangleFormPage.clearing();
        triangleFormPage.triangleFormHelper("100", "100", "90");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Равнобедренный", result);
    }

    @Test
    public void notTriangleTest() {
        triangleFormPage.triangleFormHelper("0", "0", "0");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Не треугольник", result);
    }

    @Test
    public void notTriangleTest2() {
        triangleFormPage.triangleFormHelper("100", "2", "1");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Не треугольник", result);
    }

    @Test
    public void rectangularTest() {
        triangleFormPage.triangleFormHelper("3", "4", "5");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Прямоугольный", result);
    }

    @Test
    public void simpleTest() {
        triangleFormPage.triangleFormHelper("100", "90", "80");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Разносторонний", result);
    }

    @Test
    public void equilateralTest() {
        triangleFormPage.triangleFormHelper("100", "100", "100");
        String result = triangleFormPage.findAnswer();
        assertThat(result, anyOf(is("Равносторонний"), is("Равнобедренный")));
    }

    @Test
    public void emptyFieldATest() {
        triangleFormPage.triangleFormHelper("", "", "");
        String resultA = triangleFormPage.validationA();
        String resultB = triangleFormPage.validationB();
        String resultC = triangleFormPage.validationC();
        softAssert.assertEquals(resultA, "Поле не должно быть пустым");
        softAssert.assertEquals(resultB, "Поле не должно быть пустым");
        softAssert.assertEquals(resultC, "Поле не должно быть пустым");
        softAssert.assertAll();
    }

    @Test
    public void speciaSymbolATest() {
        triangleFormPage.triangleFormHelper("@#$", "@#$", "@#$");
        String resultA = triangleFormPage.validationA();
        String resultB = triangleFormPage.validationB();
        String resultC = triangleFormPage.validationC();
        softAssert.assertEquals(resultA, "'@#$'" + "не является допустимым числом");
        softAssert.assertEquals(resultB, "'@#$'" + "не является допустимым числом");
        softAssert.assertEquals(resultC, "'@#$'" + "не является допустимым числом");
        softAssert.assertAll();
    }

    @Test
    public void lettersTest() {
        triangleFormPage.triangleFormHelper("12q","12w","e12");
        String resultA = triangleFormPage.validationA();
        String resultB = triangleFormPage.validationB();
        String resultC = triangleFormPage.validationC();
        softAssert.assertEquals(resultA, "'12q'" + "не является допустимым числом");
        softAssert.assertEquals(resultB, "'12w'" + "не является допустимым числом");
        softAssert.assertEquals(resultC, "'e12'" + "не является допустимым числом");
        softAssert.assertAll();
    }

    @Test
    public void noResulTest() {
        triangleFormPage.triangleFormHelper("100","100","100");
        triangleFormPage.clearing();
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("", result);
    }

    @Test
    public void decimalTest() {
        triangleFormPage.triangleFormHelper("14.5","14.5","10");
        String result = triangleFormPage.findAnswer();
        Assert.assertEquals("Равнобедренный", result);
    }

    @Test
    public void maxSymbolsTest () {
        triangleFormPage.triangleFormHelper("10000000001","10000000001","10000000001");
        String result = triangleFormPage.findAnswer();
        assertThat(result, anyOf(is("Равносторонний"), is("Равнобедренный")));
    }

    @Test
    public void withSpaceTest () {
        triangleFormPage.triangleFormHelper("1 0 0","1 0 0","1 0 0");
        String resultA = triangleFormPage.validationA();
        String resultB = triangleFormPage.validationB();
        String resultC = triangleFormPage.validationC();
        softAssert.assertEquals(resultA, "'1 0 0'" + "не является допустимым числом");
        softAssert.assertEquals(resultB, "'1 0 0'" + "не является допустимым числом");
        softAssert.assertEquals(resultC, "'1 0 0'" + "не является допустимым числом");
        softAssert.assertAll();
    }
    @Test
    public void afterErrorTest () {
        triangleFormPage.triangleFormHelper("100","100","100");
        triangleFormPage.triangleFormHelper("qwe","qwe","qwe");
        String resultA = triangleFormPage.validationA();
        String resultB = triangleFormPage.validationB();
        String resultC = triangleFormPage.validationC();
        softAssert.assertEquals(resultA, "'100qwe'" + "не является допустимым числом");
        softAssert.assertEquals(resultB, "'qwe'" + "не является допустимым числом");
        softAssert.assertEquals(resultC, "'100qwe'" + "не является допустимым числом");
        softAssert.assertAll();
    }
}

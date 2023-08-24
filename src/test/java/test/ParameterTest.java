
package test;  //Nefunguje

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.PrimeTest;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterTest {
    private WebDriver driver;
    private int number;
    private boolean expectedPrime;
    private final String BASE_URL = "http://localhost/primenumber.php";

    @Parameterized.Parameters
    public static List<Object[]> getData(){
        return Arrays.asList(new Object [] [] {{1,true}, {2,true}, {482, false}});
    }

    public ParameterTest(int number, boolean expectedPrime) {
        this.number = number;
        this.expectedPrime = expectedPrime;
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testOptimusUsingParameters(){
        WebElement numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        WebElement button = driver.findElement(By.cssSelector("button.btn"));

        numberInput.clear();
        numberInput.sendKeys(String.valueOf(number));
        button.click();

        checkResult(expectedPrime);
    }

    @After
    public void tearDown(){
        //driver.quit();
    }

    public void checkResult(boolean expectedPrime){
        String xpathExpression = expectedPrime ? "//div[text()='Optimus approves']" : "//div[text()='Optimus is sad']";
        new WebDriverWait(driver, PrimeTest.WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
    }
}

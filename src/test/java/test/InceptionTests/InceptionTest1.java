
package test;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InceptionTest1 {

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/inception.php";

    private final int WAIT_TIMEOUT = 5;

    public void checkingVisiblityOfElements(){
        WebElement h1Element = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(h1Element.isDisplayed());
        String h1Text = h1Element.getText();
        System.out.println("The h1 element is: " +h1Text);
        System.out.println("\n");
    }

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/test/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        String parentWindow = driver.getWindowHandle();
        WebElement element = driver.findElement(By.id("letsGoDeeper"));
        element.click();
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        checkingVisiblityOfElements();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        driver.findElement(By.xpath("//input[1]")).sendKeys("Some message");
        driver.close();

        driver.switchTo().window(parentWindow);
        WebElement element2 = driver.findElement(By.id("letsGoDeeper"));
        element2.click();
    }

    @After
    public void tearDown(){
        //driver.quit();
    }
}

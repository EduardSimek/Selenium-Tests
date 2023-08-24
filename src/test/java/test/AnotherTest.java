
package test;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AnotherTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://localhost/waitforit.php";
    private WebElement h1, id, id2;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void checkTitle(){
        h1 = driver.findElement(By.xpath("h1"));
        Assert.assertEquals("WAIT FOR IT !", h1.getText());
    }

    @Test
    @Ignore
    public void checkLegendary(){
        id2 = driver.findElement(By.id("startWaitForText"));
        id2.click();

        id = driver.findElement(By.id("waitForTextInput"));
        Assert.assertEquals("dary !!!", id.getAttribute("value"));
    }
}


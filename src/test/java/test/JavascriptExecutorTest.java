
package test;

import Base.TestBase;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavascriptExecutorTest{

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/tabulka.php";
    private JavascriptExecutor js;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        js = (JavascriptExecutor) driver;
    }



    @Test
    public void testHighLight(){
        //js = (JavascriptExecutor) driver;
        //driver.get(BASE_URL + "tabulka.php");

        WebElement lastRow = driver.findElement(By.xpath("//table//tbody//tr[last()]"));
        js.executeScript("arguments[0].scrollIntoView()", lastRow);
        testScroll();
    }

    public void testScroll(){
        System.out.println(js.executeScript("return document.body.scrollHeight"));
    }

    @After
    public void tearDown(){
        //driver.quit();
    }



}


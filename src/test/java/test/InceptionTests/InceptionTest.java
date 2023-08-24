
package test.InceptionTests;

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

public class InceptionTest {
    private WebDriver driver;
    private InceptionPage inceptionPage;
    public static final String BASE_URL = "http://localhost/inception.php";

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        inceptionPage = new InceptionPage(driver);
        inceptionPage.openWebPage();
    }

    @Test
    public void test(){
        inceptionPage.clickLetsGoDeeper();
        inceptionPage.switchToChildWindow();
        inceptionPage.checkH1Element();
        inceptionPage.closeChildWindow();
    }

    @After
    public void tearDown(){
        //driver.quit();
        //driver.close();
    }
}

class InceptionPage {
    private WebDriver driver;
    private final int WAIT_TIMEOUT = 5;
    private By letsGoDeeperButton = By.id("letsGoDeeper");
    private By h1Element = By.tagName("h1");

    public InceptionPage(WebDriver driver){
        this.driver = driver;
    }

    public void openWebPage(){
        driver.get(InceptionTest.BASE_URL);
        driver.manage().window().maximize();
    }

    public void clickLetsGoDeeper(){
        WebElement element = driver.findElement(letsGoDeeperButton);
        element.click();
        new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public void switchToChildWindow(){
        for (String handle: driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public void closeChildWindow(){
        driver.findElement(By.xpath("//input[1]")).sendKeys("Some message");
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

    public void checkH1Element(){
        WebElement h1 = driver.findElement(h1Element);
        Assert.assertTrue(h1.isDisplayed());
        String h1Text = h1.getText();
        System.out.println("The h1 element is: " +h1Text);
        System.out.println("\n");
    }
}

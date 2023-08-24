package Base;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    //private WebDriver driver;
    public static final String BASE_URL = "http://localhost/";

    @BeforeMethod
    public void setUp(){

    }

    @AfterMethod
    public void tearDown(){
        WebDriverSingletone.getWebDriverInstance().close();
        WebDriverSingletone.getWebDriverInstance().quit();
    }

    public WebDriver getDriver() {
        return WebDriverSingletone.getWebDriverInstance();
    }
}


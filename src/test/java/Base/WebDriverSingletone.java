package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingletone {
    private static WebDriver driver;

    private static void initialize(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getWebDriverInstance(){
        if (driver == null) {
            initialize();
        }
        return driver;
    }


}

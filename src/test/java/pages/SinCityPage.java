package pages;

import Base.WebDriverSingletone;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SinCityPage {

    WebDriver driver;

    public SinCityPage(){
        driver = WebDriverSingletone.getWebDriverInstance();
    }

    public void fillOutSinForm(Sin sin) {
        WebElement titleInput = driver.findElement(By.name("title"));
        WebElement authorInput = driver.findElement(By.name("author"));
        WebElement messageInput = driver.findElement(By.name("message"));

        enterText(titleInput, sin.getTitle());
        enterText(authorInput, sin.getAuthor());
        enterText(messageInput, sin.getMessage());
    }

    private void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }


}

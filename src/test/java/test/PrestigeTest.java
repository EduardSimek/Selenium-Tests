package test;

import Base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

class ElementChecker {
    private WebDriver driver;

    public ElementChecker(WebDriver driver){
        this.driver = driver;
    }

    public void checkHElement(String tagName){
        WebElement hElement = driver.findElement(By.tagName(tagName));
        Assert.assertTrue(hElement.isDisplayed());
        String hText = hElement.getText();
        System.out.println("The " +tagName+ " element is: " +hText);
    }

    public void checkHatImageVisibility(By selector, int timeout){
        WebElement displayingHat = driver.findElement(selector);
        displayingHat.click();
        Assert.assertTrue(displayingHat.isDisplayed());

        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.invisibilityOfElementLocated(selector));
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}

public class PrestigeTest extends TestBase {
    private int WAIT_TIMEOUT = 5;

    @Test
    public void prestigeTest(){
        getDriver().get(BASE_URL + "prestige.php");
        ElementChecker element = new ElementChecker(getDriver());

        element.checkHElement("h1");
        element.checkHElement("h2");

        By hatImageSelector = By.cssSelector("div.hat img");
        element.checkHatImageVisibility(hatImageSelector, WAIT_TIMEOUT);

    }

}

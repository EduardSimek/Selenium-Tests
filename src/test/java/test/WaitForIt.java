
package test;

import Base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.util.List;

public class WaitForIt extends TestBase {

    private final int WAIT_TIMEOUT = 10;

    private void waitForAttribute(By locator, String attributeName, String expectedValue) {
        new WebDriverWait(getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.attributeToBe(locator, attributeName, expectedValue));
    }

    private void waitForAttribute2(By locator, String attributeName, String expectedValue) {
        new WebDriverWait(getDriver(), WAIT_TIMEOUT)
                .until(ExpectedConditions.attributeContains(locator, attributeName, expectedValue));
    }

    public void visibilityOfElements() {
        WebElement navbar = getDriver().findElement(By.xpath("//nav[@class='navbar navbar-default']"));
        Assert.assertTrue(navbar.isDisplayed());

        List<WebElement> navbarElements = navbar.findElements(By.tagName("li"));
        for (WebElement element : navbarElements) {
            System.out.println("The Li element is: " + element.getText());
        }
        System.out.println("\n");

        WebElement h1Element = getDriver().findElement(By.tagName("h1"));
        Assert.assertTrue(h1Element.isDisplayed());
        String h1Text = h1Element.getText();
        System.out.println("The h1 element is: " + h1Text);
        System.out.println("\n");

        List<WebElement> h2Elements = getDriver().findElements(By.tagName("h2"));
        for (WebElement h2Element : h2Elements) {
            System.out.println("The h2 element is: " + h2Element.getText());
        }

    }

    @Test
    public void waitForInputText() {
        getDriver().get(BASE_URL + "waitforit.php");
        visibilityOfElements();

        WebElement start = getDriver().findElement(By.id("startWaitForText"));
        start.click();
        waitForAttribute(By.id("waitForTextInput"), "value", "dary !!!");
        System.out.println(getDriver().findElement(By.id("waitForTextInput")).getAttribute("value"));
    }

    @Test
    public void waitForClass() {
        getDriver().get(BASE_URL + "waitforit.php");

        WebElement start = getDriver().findElement(By.id("startWaitForProperty"));
        start.click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains(By.id("waitForProperty"), "class", "error"));
        waitForAttribute2(By.id("waitForProperty"), "class", "error");
        Assert.assertFalse(start.isEnabled());
    }

    @Test
    public void bluredButton() {
        getDriver().get(BASE_URL + "waitforit.php");

        WebElement blur = getDriver().findElement(By.id("waitForBlur"));
        blur.click();
        blur.sendKeys("Hello world");

        WebElement h2Element = getDriver().findElements(By.tagName("h2")).get(2);
        h2Element.click();
    }
}


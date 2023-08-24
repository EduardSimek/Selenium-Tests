
package test;

import Base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WaitForMinions extends TestBase {

    private int NUMBER_OF_MINIONS = 11;
    private final int WAIT_TIMEOUT = 10;
    private final String ErrorMsg = "Timeout waiting for number of minions";

    @Test
    public void waitForMinions(){
        getDriver().get(BASE_URL + "minions.php");
        visibilityOfElements();

        WebElement element = getDriver().findElement(By.xpath("//input[@type='number']"));
        element.sendKeys(String.valueOf(NUMBER_OF_MINIONS));
        Assert.assertTrue(element.isDisplayed());

        WebElement button = getDriver().findElement(By.xpath("//button[@class='btn btn-warning btn-block']"));
        button.click();

        String minionsXPath = "//div[@class='minions']//img";
        new WebDriverWait(getDriver(), WAIT_TIMEOUT).withMessage(ErrorMsg + NUMBER_OF_MINIONS)
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(minionsXPath), NUMBER_OF_MINIONS));
        Assert.assertEquals(NUMBER_OF_MINIONS, getDriver().findElements(By.xpath(minionsXPath)).size());
        System.out.println("Was added " +NUMBER_OF_MINIONS + " minions.");

    }

    public void visibilityOfElements(){
        WebElement h1Element = getDriver().findElement(By.tagName("h1"));
        Assert.assertTrue(h1Element.isDisplayed());
        String h1Text = h1Element.getText();
        System.out.println("The h1 element is: " +h1Text);
        System.out.println("\n");

        WebElement button = getDriver().findElement(By.xpath("//button"));
        Assert.assertTrue(button.isDisplayed());
        System.out.println("\n");

        WebElement checkBox = getDriver().findElement(By.xpath("//input[@type='number']"));
        Assert.assertTrue(checkBox.isDisplayed());

    }

}

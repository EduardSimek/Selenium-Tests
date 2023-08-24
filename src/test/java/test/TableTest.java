package test;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/*
public class TableTest extends TestBase {

    @Test
    public void test(){
        getDriver().get(BASE_URL + "tabulka.php");
        verificationSections();

        List <String> vendelinovia = getNamesByFilter("Vendelin");
        System.out.println(vendelinovia);
    }

    private void verificationSections(){
        assertElementDisplayed("//nav[@class='navbar navbar-default']", "Text navbaru je: ");
        assertElementDisplayed("//h1[@class='text-center']", "H1 element je: ");
    }

    private void assertElementDisplayed(String xpathExpression, String message){
        WebElement navbar = getDriver().findElement(By.xpath(xpathExpression));
        Assert.assertTrue(navbar.isDisplayed());
        System.out.println(message + navbar.getText());
        System.out.println("\n");
    }

    private List<String> getNamesByFilter(String name){
        List <WebElement> rows = getDriver().findElements(By.xpath("//table/tbody/tr"));
        return rows.stream()
                .filter(element -> getName(element).equals(name))
                .map(this::getSurname)
                .collect(Collectors.toList());
    }

    private String getName(WebElement element){
        return element.findElement(By.xpath("./td[2]")).getText();
    }

    private String getSurname(WebElement element){
        return element.findElement(By.xpath("./td[3]")).getText();
    }
}
*/


class Page {
    private final WebDriver driver;

    @FindBy(xpath = "//nav[@class='navbar navbar-default']")
    private WebElement navbar;

    @FindBy(xpath = "//h1[@class='text-center']")
    private WebElement h1;

    public Page (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifySections() {
        assertElementDisplayed(navbar, "Text navbaru je: ");
        assertElementDisplayed(h1, "H1 element je: ");
    }

    private void assertElementDisplayed(WebElement element, String message){
        Assert.assertTrue(element.isDisplayed());
        System.out.println(message + element.getText());
        System.out.println("\n");
    }

    public List<String> getNamesByFilter(String name) {
        List <WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        return rows.stream()
                .filter(element -> getName(element).equals(name))
                .map(this::getSurname)
                .collect(Collectors.toList());
    }

    private String getName(WebElement element) {
        return element.findElement(By.xpath("./td[2]")).getText();
    }

    private String getSurname(WebElement element) {
        return element.findElement(By.xpath("./td[3]")).getText();
    }

}

public class TableTest extends TestBase {
    @Test
    public void test(){
        getDriver().get(BASE_URL + "tabulka.php");

        Page page = new Page(getDriver());
        page.verifySections();

        List<String> vendelinovia = page.getNamesByFilter("Vendelin");
        System.out.println(vendelinovia);
    }
}

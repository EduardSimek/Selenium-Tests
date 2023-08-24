
package test;

import Base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SinCityTest extends TestBase {

    @Test
    public void testNewSin(){
        getDriver().get(BASE_URL + "sincity.php");
        Sin spiderSin = new Sin ("Zabil som pavúka", "Ja", "Some message");

        List <SinType> spiderSinTags = new ArrayList<>();
        spiderSinTags.add(SinType.HIJACK);
        spiderSinTags.add(SinType.MURDER);
        spiderSinTags.add(SinType.BLACKMAIL);

        spiderSin.setTags(spiderSinTags);

        SinCityPage sinCityPage = new SinCityPage(getDriver());
        sinCityPage.fillOutSinForm(spiderSin);
        sinCityPage.markTags(spiderSin.getTags());


    }
}

class SinCityPage {
    private final WebDriver driver;

    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(name = "author")
    private WebElement authorInput;

    @FindBy(name = "message")
    private WebElement messageInput;

    public SinCityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutSinForm(Sin sin){
        titleInput.sendKeys(sin.getTitle());
        authorInput.sendKeys(sin.getAuthor());
        messageInput.sendKeys(sin.getMessage());
    }

    public void markTags(List<SinType> tags){
        for (SinType tag : tags) {
            markTag(tag);
        }
    }

    private void markTag(SinType tag){
        String xpathValue = tag.getXpathValue();
        driver.findElement(By.xpath("//input[@value='" + xpathValue + "']")).click();
    }
}


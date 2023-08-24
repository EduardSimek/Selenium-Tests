package test;

import Base.TestBase;
import enumerators.SinType;
import models.Sin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SinCityTest2 extends TestBase {

    @Test
    public void testNewSin() {
        getDriver().get(BASE_URL + "sincity.php");
        Sin spiderSin = new Sin("Zabil som pavúka", "Ja", "Some message");

        List<SinType> spiderSinTags = new ArrayList<SinType>();
        spiderSinTags.add(SinType.HIJACK);
        spiderSinTags.add(SinType.MURDER);

        spiderSin.setTags(spiderSinTags);
        fillOutSinForm(spiderSin);
        markTags(spiderSin.getTags());
    }

    private void markTags(List<SinType> tags) {
        for (SinType tag : tags) {
            markTag(tag);
        }
    }

    public void fillOutSinForm(Sin sin) {
        WebElement titleInput = getDriver().findElement(By.name("title"));
        WebElement authorInput = getDriver().findElement(By.name("author"));
        WebElement messageInput = getDriver().findElement(By.name("message"));

        enterText(titleInput, sin.getTitle());
        enterText(authorInput, sin.getAuthor());
        enterText(messageInput, sin.getMessage());
    }

    private void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    private void markTag(SinType tag) {
        String xpathValue = tag.getXpathValue();
        getDriver().findElement(By.xpath("//input[@value='" + xpathValue + "']")).click();
    }

}


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckInfo {
    private WebDriver driver;

    public CheckInfo(WebDriver driver) {
        this.driver = driver;
    }

    public String getPointsValue() {
        return driver.findElement(By.cssSelector("div.points-left h2")).getText();
    }

    public boolean isHeaderDisplayed() {
        return driver.findElement(By.xpath("//h1[@class='title col-md-10']")).isDisplayed();
    }

    public boolean isListOfFellowsDisplayed() {
        return driver.findElement(By.xpath("//ul[@class='row list-of-fellows']")).isDisplayed();
    }

    public boolean isPointsLeftDisplayed() {
        return driver.findElement(By.xpath("//div[@class='points-left col-md-2 box-shadow']")).isDisplayed();
    }

    public List<String> getAllMembers() {
        return driver.findElements(By.cssSelector("ul.list-of-fellows div.col h1"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAllFellowMembersAge() {
        return driver.findElements(By.cssSelector("ul.list-of-fellows h2:nth-child(3)")).stream()
                .map(WebElement::getText)
                .map(fellowAge -> fellowAge.replace("Age", "").trim()).collect(Collectors.toList());
    }

    public List<WebElement> getAllHobbits() {
        return driver.findElements(By.cssSelector("ul.list-of-fellows li"));
    }


}



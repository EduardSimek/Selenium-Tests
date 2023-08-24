
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAssertJ2 {

    private WebDriver driver;
    private final String BASE_URL = "http://localhost/";
    private int ExpectedNumber = 9;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void shouldContainInitialValue(){
        String expectedValue = "25";
        String actualValue = driver.findElement(By.cssSelector("div.points-left h2")).getText();
        Assertions.assertThat(actualValue).as("Checking point value").isEqualTo(expectedValue);
    }

    @Test
    public void shouldShowAllMembers(){
        List<String> allMembers = driver.findElements(By.cssSelector("ul.list-of-fellows div.col h1"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        assertThat(allMembers).hasSize(ExpectedNumber)
                .contains("Frodo", "Samwise", "Gandalf", "Gimli", "Aragorn", "Boromir", "Meriadoc", "Peregrin");

    }

    @Test
    public void checkingAttributes(){
        WebElement h1 = driver.findElement(By.xpath("//h1[@class='title col-md-10']"));
        Assert.assertTrue(h1.isDisplayed());
        System.out.println(h1.getText());

        WebElement allElements = driver.findElement(By.xpath("//ul[@class='row list-of-fellows']"));
        Assert.assertTrue(allElements.isDisplayed());

        WebElement checkingAllPoints = driver.findElement(By.xpath("//div[@class='points-left col-md-2 box-shadow']"));
        Assert.assertTrue(checkingAllPoints.isDisplayed());
        System.out.println(checkingAllPoints.getText());
    }

    @Test
    public void shouldContainAge(){
        List<String> fellowMembersAge = driver.findElements(By.cssSelector("ul.list-of-fellows h2:nth-child(3)"))
                .stream()
                .map(WebElement::getText)
                .map(fellowAge -> fellowAge.replace("Age", "")
                        .trim()).collect(Collectors.toList());

        assertThat(fellowMembersAge).isNotEmpty();
        fellowMembersAge.forEach(_fellowMembersAge -> {
            assertThat(_fellowMembersAge).isNotNull().isNotEmpty().isNotBlank().isNotEqualTo("undefined");
        });
    }

    @Test
    public void shouldContainOnlySpecificHobbits(){
        displayOnlyThoseWithRaceHobbit();
        displayOnlyThoseWithRaceMan();
    }

    private void displayOnlyThoseWithRaceHobbit(){
        String [] hobbits = {"Frodo", "Samwise", "Meriadoc", "Peregrin"};

        List <WebElement> _hobbits = driver.findElements(By.cssSelector("ul.list-of-fellows li"));

        assertThat(_hobbits)
                .filteredOn(webElement -> webElement.findElement(By.cssSelector("h2:nth-child(2)"))
                        .getText().contains("Hobbit"))
                .extracting(webElement -> webElement.findElement(By.cssSelector("h1")).getText())
                .contains(hobbits).doesNotContain("Sauron", "Default Value");

    }

    private void displayOnlyThoseWithRaceMan(){
        String [] hobbits = {"Aragorn", "Boromir"};

        List <WebElement> _hobbits = driver.findElements(By.cssSelector("ul.list-of-fellows li"));

        assertThat(_hobbits)
                .filteredOn(webElement -> webElement.findElement(By.cssSelector("h2:nth-child(2)"))
                .getText().contains("Man")).extracting(webElement -> webElement.findElement(By.cssSelector("h1"))
                        .getText()).contains(hobbits).doesNotContain("Default Value1", "Default Value2");
    }


    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }
}


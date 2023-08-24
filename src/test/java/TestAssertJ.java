import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAssertJ {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/";
    private int expectedNumber = 9;
    private CheckInfo checkInfo;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        checkInfo = new CheckInfo(driver);
    }

    @Test
    public void shouldContainInitialValue(){
        String expectedValue = "25";
        String actualValue = checkInfo.getPointsValue();
        assertThat(actualValue).as("Checking point value").isEqualTo(expectedValue);
    }

    @Test
    public void shouldShowAllMembers(){
        List <String> allMembers = checkInfo.getAllMembers();

        assertThat(allMembers)
                .hasSize(expectedNumber)
                .contains("Frodo", "Samwise", "Gandalf", "Gimli", "Aragorn", "Boromir", "Meriadoc", "Peregrin");
    }

    @Test
    public void checkingAttributes() {
        assertThat(checkInfo.isHeaderDisplayed()).isTrue();
        assertThat(checkInfo.isListOfFellowsDisplayed()).isTrue();
        assertThat(checkInfo.isPointsLeftDisplayed()).isTrue();
    }

    @Test
    public void shouldContainAge() {
        List<String> fellowMembersAge = checkInfo.getAllFellowMembersAge();

        assertThat(fellowMembersAge)
                .isNotEmpty()
                .allSatisfy(age -> assertThat(age)
                        .isNotNull()
                        .isNotEmpty()
                        .isNotBlank()
                        .isNotEqualTo("undefined"));
    }

    @Test
    public void shouldContainOnlySpecificHobbits() {
        List<WebElement> hobbits = checkInfo.getAllHobbits();

        assertThat(hobbits)
                .filteredOn(webElement -> webElement.findElement(By.cssSelector("h2:nth-child(2)"))
                        .getText().contains("Hobbit"))
                .extracting(webElement -> webElement.findElement(By.cssSelector("h1")).getText())
                .contains("Frodo", "Samwise", "Meriadoc", "Peregrin")
                .doesNotContain("Sauron", "Default Value");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }
}




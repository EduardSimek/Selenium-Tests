
package test;

import Base.TestBase;
import helpers.ExcelReader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class PrimeTest{

    private static final String TEST_DATA_PATH = "src/test/java/resources/data.xlsx";
    private static final String SHEET = "prime";
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/primenumber.php";
    public static int WAIT_TIMEOUT = 5;
    private PrimePage primePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        primePage = new PrimePage(driver);
    }

    @Test
    public void test() throws IOException {
        ExcelReader primeExcelReader = new ExcelReader(TEST_DATA_PATH);
        Sheet sheet = primeExcelReader.getSheetByName(SHEET);
        for (Row cells : sheet) {
            if (cells.getRowNum() == 0){
                continue;
            }

            int number = (int) cells.getCell(0).getNumericCellValue();
            boolean expectedPrime = cells.getCell(1).getBooleanCellValue();

            primePage.enterNumberAndClick(String.valueOf(number));
            primePage.checkResult(expectedPrime);
        }
    }

    @After
    public void tearDown(){
        //driver.quit();
    }

}

class PrimePage{
    private WebDriver driver;
    private WebElement numberInput, button;

    public PrimePage(WebDriver driver){
        this.driver = driver;
        numberInput = driver.findElement(By.xpath("//input[@type='number']"));
        button = driver.findElement(By.xpath("//button[@class='btn btn-default btn-block btn-danger']"));
    }

    public void enterNumberAndClick(String number){
        numberInput.clear();
        numberInput.sendKeys(number);
        button.click();
    }

    public void checkResult(boolean expectedPrime){
        String xpathExpression = expectedPrime ? "//div[text()='Optimus approves']" : "//div[text()='Optimus is sad']";
        new WebDriverWait(driver, PrimeTest.WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
    }
}

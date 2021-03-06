import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class test {
    WebDriver driver;
    By firstElement=By.xpath("//*[@class='goods-tile__heading ng-star-inserted']");

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "browserdriver/chromedriver.exe");
    }

    @BeforeMethod
    public void openBrowser(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

    }
    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement searchElement = driver.findElement(By.xpath("//input[@name='search']"));
        searchElement.clear();
        searchElement.sendKeys("Монитор");

        WebElement searchButton = driver.findElement(By.cssSelector(".button.search-form__submit"));
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstElement));

        WebElement firstProductElement = driver.findElement(firstElement);

        String firstElementTitle = firstProductElement.getText();

        Assert.assertTrue(firstElementTitle.contains("Mонитор"),"Not contain");
    }
    @AfterMethod
    public void BrowserQuit(){
        driver.quit();
    }
}

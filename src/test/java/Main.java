import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Main {

    WebDriver driver;

    @BeforeTest
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get("https://google.com");
        driver.manage().window().maximize();

        WebElement input = driver.findElement(By.xpath("//input[@name='q']"));
         input.sendKeys("pornhub"+ Keys.ENTER);
        WebElement firstLink = driver.findElement(By.xpath("//a[contains(@href,'http')]/h3"));
        firstLink.click();
        WebElement logo = driver.findElement(By.xpath("//img[@title='Pornhub']"));

        Assert.assertTrue(logo.isDisplayed());
       try {
            Thread.sleep(10000);
       } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void quitdriver() {
        driver.quit();
    }
}
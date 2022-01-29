import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Main {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    //First test
    //URLs
    private String googleURL = "https://google.com";

    //Links
    private String firstLinkOnTheMainPageXpath = "//a[contains(@href,'http')]/h3";

    //Fields
    private String searchFieldXpath = "//input[@name='q']";

    //Logos
    private String logoXpath = "//img[@title='Pornhub']";

    //Second test
    //Buttons


    /**
     * Methods
     */
    public void setup(String URL){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    /**
     * Test 1 - Assert that logo is displayed when we click to the first link on the main page
     */
    @Test
    public void test() {
        //Go to the main page
        //Set window to the maximize
        setup(googleURL);

        //Set search field
        WebElement input = driver.findElement(By.xpath(searchFieldXpath));
        input.sendKeys("pornhub" + Keys.ENTER);

        //Click the first link
        WebElement firstLink = driver.findElement(By.xpath(firstLinkOnTheMainPageXpath));
        firstLink.click();

        //Assert that logo is displayed
        WebElement logo = driver.findElement(By.xpath(logoXpath));
        Assert.assertTrue(logo.isDisplayed());
    }

    //hello
    @Test
    public void test2() {
        driver.get("https://rozetka.com.ua/");

        WebElement input = driver.findElement(By.xpath("//input[@name='search']"));
        input.sendKeys("монитор" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='goods-tile__title']")));

        WebElement firstLink1 = driver.findElement(By.xpath("//span[@class='goods-tile__title']"));
        firstLink1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//rz-product-navbar")));

        WebElement buyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Купить']")));
        actions.moveToElement(buyButton).perform();
        buyButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal__content']")));

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='cart-receipt-submit-order']")));
        checkoutButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class,'checkout-heading')]")));

    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
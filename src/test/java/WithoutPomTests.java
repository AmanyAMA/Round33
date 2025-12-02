import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class WithoutPomTests {

    RemoteWebDriver driver = new ChromeDriver();

    @Test
    public void Basic1(){
        driver.get("https://duckduckgo.com/");
        String pageTitle= driver.getTitle();
        System.out.println(pageTitle);
        Assert.assertEquals(pageTitle,"Google");
        driver.quit();
    }


    @Test
    public void Basic2(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.get("https://duckduckgo.com/");
        WebElement isImageVisible = driver.findElement
                (By.xpath("//div[@class='header_headerContent__LYxh6']/following::a[@title=\"Learn about DuckDuckGo\"]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(isImageVisible));
        Assert.assertTrue(isImageVisible.isDisplayed());
        driver.quit();
    }

    @Test
    public void Basic3(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.get("https://duckduckgo.com/");
        WebElement SearchBox = driver.findElement(By.id("searchbox_input"));
        SearchBox.sendKeys("Selenium WebDriver");
        WebElement SearchButton = driver.findElement(By.xpath("//button[@aria-label=\"Search\"]"));
        SearchButton.click();
        WebElement FirstResult = driver.findElement(By.xpath("//article[@id='r1-0']/div[3]/h2"));
        FirstResult.click();
        driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.selenium.dev/documentation/webdriver/");
        driver.quit();
    }

    @Test
    public void Basic4()
    {
        RemoteWebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.get("https://duckduckgo.com/");
        WebElement SearchBox = driver.findElement(By.id("searchbox_input"));
        SearchBox.sendKeys("TestNG");
        WebElement SearchButton = driver.findElement(By.xpath("//button[@aria-label=\"Search\"]"));
        SearchButton.click();
        WebElement FourthResult = driver.findElement(By.xpath("//article[@id='r1-3']/div[3]/h2"));
        FourthResult.click();
        String FourthResultBrowserTitle =driver.getTitle();
        Assert.assertEquals(FourthResultBrowserTitle,"");
        driver.quit();
    }
    

}

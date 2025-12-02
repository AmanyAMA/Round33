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


    @Test
    public void Basic1(){
        RemoteWebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        String pageTitle= driver.getTitle();
        System.out.println(pageTitle);
        Assert.assertEquals(pageTitle,"Google");
        driver.quit();
    }


    @Test
    public void Basic2(){
        RemoteWebDriver driver = new ChromeDriver();
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
        RemoteWebDriver driver = new ChromeDriver();
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

        WebElement FourthResult = driver.findElement(By.xpath("//article[@id='r1-3']/div[3]/h2/a/span"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(FourthResult));

        //solution using getAttribute
        String FourthResultBrowserTitle =FourthResult.getText();

        //solution using getTitle
        /*FourthResult.click();
        String FourthResultBrowserTitle =driver.getTitle();*/
        Assert.assertEquals(FourthResultBrowserTitle,"TestNG Tutorial");
        driver.quit();
    }

    @Test
    public void Basic5()
    {

        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.get("https://duckduckgo.com/");
        WebElement SearchBox = driver.findElement(By.id("searchbox_input"));
        SearchBox.sendKeys("Cucumber IO");
        WebElement SearchButton = driver.findElement(By.xpath("//button[@aria-label=\"Search\"]"));
        SearchButton.click();

        WebElement SecondResult = driver.findElement(By.xpath("//article[@id='r1-1']/div[3]/h2/a"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(SecondResult));

        //solution using getAttribute
        String SecondResURL =SecondResult.getAttribute("href");

        //solution using getTitle
        /*SecondResult.click();
        String SecondResURL =driver.getTitle();*/

        //validation
        Assert.assertNotNull(SecondResURL);
        Assert.assertTrue(SecondResURL.contains("https://www.linkedin.com"));
        driver.quit();
    }
    

}

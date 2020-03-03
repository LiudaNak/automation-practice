package lesson09.custom_waitings;

import lesson09.a_own_expected_condotion.CustomExpectedConditions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestSuit {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest(){
  WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(CustomExpectedConditions.listNthElementHasText(By.xpath("//*[@id=\"index\"]/div[2]"), 3, "Dress"));
    }

    @Test
    public void secondTest(){
       WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(lesson09.custom_waitings.CustomExpectedConditions.pageIsLoaded("http://automationpractice.com/index.php", "exp"));
    }

    @Test
    public void thirdTest(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(lesson09.custom_waitings.CustomExpectedConditions.stalenessOfElement(driver.findElement(By.id("header"))));
    }
}

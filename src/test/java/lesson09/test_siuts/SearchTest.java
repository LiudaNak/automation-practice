package lesson09.test_siuts;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void tearDown() {
                driver.quit();
    }
    //SearchTest с одним тестом: открыть сайт, ввести в поиск "Printed Summer Dress" ,
    //нажать поиск, проверить, что появилось три результата и первый совпадает с запросом.

    @Test
    public void searchTech(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).clear();
        driver.findElement(By.id("search_query_top")).sendKeys("Printed Summer Dress");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submit_search"))).click();

        Assert.assertTrue(driver.findElements(By.name("product_list grid row")).size()==3 &&
                driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")).getText().equals("Printed Summer Dress"));
    }
}

package lesson09.test_siuts;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        //driver.get("http://automationpractice.com/index.php");

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
    //SearchTest с одним тестом: открыть сайт, ввести в поиск "Printed Summer Dress" ,
    //нажать поиск, проверить, что появилось три результата и первый совпадает с запросом.

    @Test
    public static void serchTech(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.id("search_query_top")).clear();
        driver.findElement(By.id("search_query_top")).sendKeys("Printed Summer Dress");
        driver.findElement(By.name("submit_search")).click();


        Assert.assertTrue(driver.findElements(By.name("product_list grid row")).size()==3 &&
                driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")).getText().equals("Printed Summer Dress"));
    }
}

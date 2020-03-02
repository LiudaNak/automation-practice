package lesson09.stream_api_usung;
/*1. Напишите тест, похожий на рассмотренный на занятии 5 (ввод поискового запроса и проверка первой подсказки),
только проверьте, что все подсказки выпадающего списка содержат вводимый текст.
        1.1. Искать список всех подсказок через findElements;
        1.2. Проверку всех подсказок сделать с помощью Stream API.
        2. Запустить сьют через мавен и через IDE.
        3. Закоммитить изменения, залить их на репозиторий GitHub и прислать ссылку.*/



import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StreamApiUsing {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }

    @Test
    public void verifyAllTipsContainsSomeText() {
        driver.findElement(By.id("search_query_top")).clear();
        driver.findElement(By.id("search_query_top")).sendKeys("Dress");

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//*[@id=\"index\"]/div[2]"))));
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"index\"]/div[2]"));

        int elementContainText = (int) list.stream().filter(s -> s.getText().contains("Dress")).count();

        Assert.assertTrue(list.size()==elementContainText);

    }
}

package lesson11.homework.shopping;

/*1. Создать тестовый класс со следующим тестом:
        поиск товара, добавление его в корзину, оформление заказа, покупка, проверка, что в истории заказов появился ИМЕННО ваш заказ.
        2. Запустить тестовый класс через мавен и через IDE.
        3. Закоммитить изменения, залить их на репозиторий GitHub и прислать ссылку.*/

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MyShopping {
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
    @Before
    public void goToStartPage() {
        driver.get("http://automationpractice.com/index.php");
        waitFor(ExpectedConditions.elementToBeClickable(By.className("login")));
        driver.findElement(By.className("login")).click();
        waitFor(ExpectedConditions.elementToBeClickable(By.id("email")));
        driver.findElement(By.id("email")).sendKeys("ms.liuda+1@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Portal123");
        driver.findElement(By.id("SubmitLogin")).click();
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void makeShopping() {
        driver.findElement(By.id("search_query_top")).clear();
        driver.findElement(By.id("search_query_top")).sendKeys("Blouse");
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#my-account > div.ac_results > ul > li"))).click();
        waitFor(ExpectedConditions.elementToBeClickable(By.cssSelector("#add_to_cart > button > span"))).click();
        waitFor(ExpectedConditions.elementToBeClickable(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span"))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium > span"))).click();
    }

    private WebElement waitFor(ExpectedCondition<WebElement> condition) {
        return new WebDriverWait(driver, 10).until(condition);
    }
}



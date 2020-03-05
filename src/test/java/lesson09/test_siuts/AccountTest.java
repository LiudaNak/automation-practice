package lesson09.test_siuts;

/*1. Зарегистрируйтесь на http://automationpractice.com
        2. Создать два тестовых класса:
        2.1. AccountTest со следующими тестами: 1-5 - возможность открытия страничек "ORDER HISTORY AND DETAILS",
        "MY CREDIT SLIPS", "MY ADDRESSES", "MY PERSONAL INFORMATION" и "MY WISHLISTS" (очевидно, что логин должен быть в @Before).
        2.2. SearchTest с одним тестом: открыть сайт, ввести в поиск "Printed Summer Dress" ,
        нажать поиск, проверить, что появилось три результата и первый совпадает с запросом.
        3. Собрать два теста в один сьют @RunWith(Suite.class).
        4. Запустить сьют через мавен и через IDE.
        5. Закоммитить изменения, залить их на репозиторий GitHub и прислать ссылку.*/

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class AccountTest {
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

    @Before
    public void goToStartPage(){
        driver.get("http://automationpractice.com/index.php");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("login")));
        driver.findElement(By.className("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        driver.findElement(By.id("email")).sendKeys("ms.liuda+1@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Portal123");
        driver.findElement(By.id("SubmitLogin")).click();
    }
    @After
    public void logout(){
        //driver.get("http://automationpractice.com/index.php");
        wait.until(ExpectedConditions.elementToBeClickable(By.className("logout"))).click();
    }


    @Test
    public void openOrderHistoryAndDetails(){
        WebElement element = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(1) > a > span"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
element.click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=history"));
    }

    @Test
    public void openMyAddresses(){
        WebElement element = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(3) > a > span"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=addresses"));
    }

    @Test
    public void openMyCreditSlips(){
        WebElement element = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(2) > a > span"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=order-slip"));
    }

    @Test
    public void openMyPersonalInformation(){
        WebElement element = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(1) > ul > li:nth-child(4) > a > span"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=identity"));
    }

    @Test
    public void openMyWhilst(){
        WebElement element = driver.findElement(By.cssSelector("#center_column > div > div:nth-child(2) > ul > li > a > span"));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist"));
    }
}
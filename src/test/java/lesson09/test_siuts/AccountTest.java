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
       // driver.get("http://automationpractice.com/index.php");

    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

    @Before
    public void goToStartPage(){
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.className("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        driver.findElement(By.id("email")).sendKeys("ms.liuda+1@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Portal123");
        driver.findElement(By.id("SubmitLogin")).click();
    }

    @Test
    public void openOrderHistoryAndDetails(){
driver.findElement(By.partialLinkText("Order history and details")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=history"));
    }

    @Test
    public void openMyAddresses(){
        driver.findElement(By.partialLinkText("My addresses")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=addresses"));
    }

    @Test
    public void openMyCreditSlips(){
        driver.findElement(By.partialLinkText("My credit slips")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=order-slip"));
    }

    @Test
    public void openMyPersonalInformation(){
        driver.findElement(By.partialLinkText("My personal information")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=identity"));
    }

    @Test
    public void openMyWishlist(){
        driver.findElement(By.partialLinkText("My wishlists")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist"));
    }
}

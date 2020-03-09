package lesson11.homework.pageObjectsAndPageFactory;
/*1. Создать два класса LoginPage и AccountPage, используя для элементов аннотации PageFactory.
        2. LoginPage с методом logIn(String username, String password),
        который состоит из enterUsername(String username), enterPassword(String password) и clickSignInBtn().
         Метод logIn должен возвращать новый экземпляр страницы AccountPage.
         Предусмотреть возможность логина, через цепочный вызов методов enterUsername, enterPassword и clickSignInBtn.
        3. AccountPage с методом signOut(), который возвращает новый экземпляр страницы LoginPage.
        4. Сделать тестовый класс с тремя тестами: 1 - на логин через метод logIn, 2 - на логин с использованием цепочки методов,
        3 - на логаут (в тестах проверять, что нужная страница открылась, assertThat в помощь).
        При запуске тестов делайте игнор для 1 или 2 теста.
        5. Запустить тестовый класс через мавен и через IDE.
        6. Закоммитить изменения, залить их на репозиторий GitHub и прислать ссылку.

        Обратите внимание на следующее (чтоб работал цепочный вызов!!!!!!!!!!!!!):
        1) метод logIn должен ВОЗВРАЩАТЬ НОВЫЙ ЭКЗЕМПЛЯР страницы AccountPage;
        2) AccountPage с методом signOut(), который ВОЗВРАЩАЕТ НОВЫЙ ЭКЗЕМПЛЯР страницы LoginPage.*/

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestClass {
    static WebDriver driver;
    AccountPage accountPage = PageFactory.initElements(driver, AccountPage.class);
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

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

      /*  @After
        public void logOutIfNeeded(){
        if (driver.findElement(By.className("logout")).isDisplayed()){
            accountPage.signOut();
            }
        }
*/
@Test
    public void login(){
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    loginPage.logIn("ms.liuda+1@gmail.com", "Portal123");
    Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));
}

@Test
    public void loginWithChainMethods(){
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    loginPage.enterUsername("ms.liuda+1@gmail.com").enterPassword("Portal123").clickSignInBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));
}

@Test
    public void logOut(){
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    if (driver.findElement(By.className("login")).isDisplayed()) {
        loginPage.logIn("ms.liuda+1@gmail.com", "Portal123");
    }

    new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(accountPage.getLogOut()));
    accountPage.signOut();
    Assert.assertTrue(driver.getCurrentUrl().contains("authentication"));
}

}
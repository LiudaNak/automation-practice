package lesson09.custom_waitings;

import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

//Создайте три кастомные ожидания:
// 2- pageIsLoaded(String expUrl, String expTitle), которое, ждет, чтоб титул страницы и ее url СОДЕРЖИТ указанные тексты;

public class PageIsLoaded {
    public static ExpectedCondition<Boolean> pageIsLoaded (String expUrl, String expTitle){
        return new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                    driver = new ChromeDriver();

return driver.getTitle().equals(expTitle) && driver.getCurrentUrl().contains(expUrl) ? true : false;

        }

           /* @Override
            public String toString() {
                return String.format("Page Title does not contain expected  value %s\n and URL does not contain expected %s", expTitle, expUrl);
            }*/
        };
    }
}

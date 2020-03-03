package lesson09.custom_waitings;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

//Создайте три кастомные ожидания:
// 2- pageIsLoaded(String expUrl, String expTitle), которое, ждет, чтоб титул страницы и ее url СОДЕРЖИТ указанные тексты;

public class PageIsLoaded {
    public static ExpectedCondition<Boolean> pageIsLoaded(String expUrl, String expTitle) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";
            private String currentTitle = "";
@NullableDecl
            public Boolean apply(@NullableDecl WebDriver driver) {
                System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
                driver = new ChromeDriver();

                this.currentUrl = driver.getCurrentUrl();
                this.currentTitle = driver.getTitle();
                return (this.currentUrl != null && this.currentUrl.contains(expUrl)) && (this.currentTitle != null && this.currentTitle.contains(expTitle));
            }

            @Override
            public String toString() {
                return String.format("url should contain %s. Current url: %s\n Title should contain %s. Current Title: %s",
                        expUrl, this.currentUrl, expTitle, this.currentTitle);
            }
        };
    }
}

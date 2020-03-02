package lesson09.custom_waitings;

/*1. Создайте три кастомные ожидания: listNthElementHasText(By locator, int elNo, String expText),
которое по локатору, проверяет, что элемент с указанным номером содержит указанный текст (
не забудьте обработать IndexOutOfBoundsException!!!);
        2. Сделайте тест сьют с тремя тестами по одному на каждое ожидание.
        3. Запустить тестовый класс через мавен и через IDE.
        4. Закоммитить изменения, залить их на репозиторий GitHub и прислать ссылку.*/

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ListNthElementHasText {
    public static  ExpectedCondition<WebElement> listNthElementHasText(By locator, int elNo, String expText){
        return new ExpectedCondition<WebElement>() {
            private String elementText = "";

            @NullableDecl

            public WebElement apply(@NullableDecl WebDriver driver) {
                try {
                    System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
                    driver = new ChromeDriver();

                   //elementText = driver.findElements(locator).get(elNo).getText();
                   WebElement element = driver.findElements(locator).get(elNo);
                    elementText = element.getText();

                    return elementText.contains(expText) ? element : null;

                } catch (IndexOutOfBoundsException e){
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("%dth element \nof list \nhas text: %s\n while actual text was: %s\n",
                        elNo, expText, elementText);
            }
        };
    }
}

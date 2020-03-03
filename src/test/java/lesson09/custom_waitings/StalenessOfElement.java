package lesson09.custom_waitings;
//stalenessOfElement(WebElement elToBeDisappeared), которое проверяет, что ЭЛЕМЕНТ исчез со страницы.

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class StalenessOfElement {

    public static ExpectedCondition<Boolean> stalenessOfElement(WebElement elToBeDisappeared) {

 return new ExpectedCondition<Boolean>() {
@NullableDecl
                public Boolean apply(@NullableDecl WebDriver driver) {

                    try {
                        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                        driver = new ChromeDriver();

                        return !driver.findElement((By) elToBeDisappeared).isDisplayed();

                    } catch (NoSuchElementException var3) {
                        return true;
                    } catch (StaleElementReferenceException var4) {
                        return true;
                    }
                }
@Override
                public String toString() {
                    return "element " + elToBeDisappeared + "is not longer visible";
                }
            };
        }
    }

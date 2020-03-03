package lesson09.custom_waitings;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {

    public static  ExpectedCondition<WebElement> listNthElementHasText(By locator, int elNo, String expText){
        return new ExpectedCondition<WebElement>() {
            private String elementText = "";

            @NullableDecl

            public WebElement apply(@NullableDecl WebDriver driver) {
                try {

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


    public static ExpectedCondition<Boolean> pageIsLoaded(String expUrl, String expTitle) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";
            private String currentTitle = "";
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver driver) {

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



    public static ExpectedCondition<Boolean> stalenessOfElement(WebElement elToBeDisappeared) {

        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver driver) {

                try {

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

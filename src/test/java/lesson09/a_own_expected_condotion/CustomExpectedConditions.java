package lesson09.a_own_expected_condotion;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
    public static ExpectedCondition<WebElement> listNthElementHasText(final By locator, final int no, final String expTextPart) {

        return new ExpectedCondition<WebElement>() {
            private String nthElementText = "";

            @NullableDecl
            public WebElement apply(@NullableDecl WebDriver driver) {
                try {
                    nthElementText = driver.findElements(locator).get(no).getText();
                    WebElement element = driver.findElements(locator).get(no);
                            nthElementText = element.getText();

                    return nthElementText.contains(expTextPart) ? element : null;

                } catch (IndexOutOfBoundsException e){
                    return null;
                }

            }

            @Override
            public String toString() {
                return String.format("%dth element \nof list \nto have text: %s\n while actual text was: %s\n",
                no, expTextPart, nthElementText);
            }
        };
    }

}

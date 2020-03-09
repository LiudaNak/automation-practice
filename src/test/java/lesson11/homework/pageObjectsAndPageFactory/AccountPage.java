package lesson11.homework.pageObjectsAndPageFactory;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


//AccountPage с методом signOut(), который возвращает новый экземпляр страницы LoginPage.
public class AccountPage {

    @FindBy (className = "logout")
    private WebElement logOut;

    public WebElement getLogOut() {
        return logOut;
    }
    public void setLogOut(WebElement logOut) {
        this.logOut = logOut;
    }

    public LoginPage signOut(){
        logOut.click();
        return new LoginPage();
    }
}

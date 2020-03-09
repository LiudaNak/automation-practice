package lesson11.homework.pageObjectsAndPageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "passwd")
    private WebElement pass;

    @FindBy(id = "SubmitLogin")
    private WebElement signIn;

    public AccountPage logIn(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignInBtn();
        return new AccountPage();
    }

      /*  public void enterUsername(String username){
        email.sendKeys(username);
        }*/

    public LoginPage enterUsername(String username) {
        email.sendKeys(username);
        return this;
    }

       /* public void enterPassword(String password){
        pass.sendKeys(password);
        }*/

    public LoginPage enterPassword(String password) {
        pass.sendKeys(password);
        return this;
    }
   /*public void clickSignInBtn(){
        signIn.click();
        }*/


    public LoginPage clickSignInBtn() {
        signIn.click();
        return this;
    }

}
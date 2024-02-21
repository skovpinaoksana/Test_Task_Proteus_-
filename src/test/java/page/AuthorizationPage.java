package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationPage {
    private final SelenideElement
            FieldEmail =$(byId("loginEmail")),
            FieldPass = $(byId("loginPassword")),
            LoginButton = $(byId("authButton")),
            ErrorMessage =$(byId("invalidEmailPassword"));

    public void openPage() {
        open("file:///Users/oksana/Downloads/qa-test.html");
    }
    public AuthorizationPage EmailSet (String value) {
        FieldEmail.click();
        FieldEmail.setValue(value);
        return this;
    }
    public AuthorizationPage Password (String value) {
        FieldPass.click();
        FieldPass.setValue(value);
        return this;
    }
    public AuthorizationPage LoginButtonClick() {
        LoginButton.click();
        return this;
    }

    public AuthorizationPage CheckingErrorMessage(){
        ErrorMessage.shouldHave(text("Неверный E-Mail или пароль"));
        return this;
    }

    public AuthorizationPage LogIn(String user, String pass){
        openPage();
        EmailSet(user);
        Password(pass);
        LoginButtonClick();
        return this;
    }

}

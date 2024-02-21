package tests;

import data.AuthorizationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;

public class AuthorizationTests extends BaseTest{
    public static AuthorizationPage authorizationPage = new AuthorizationPage();
    public static AuthorizationData authorizationData = new AuthorizationData();

    @Test
    @DisplayName("Авторизация пользователя c валидными данными")
    @Tag("BLOCKER")
    void Authorization() {
        authorizationPage
                .LogIn(authorizationData.USER, authorizationData.PASS);
    }

    @Test
    @DisplayName("Авторизация пользователя с невалидным паролем")
    void AuthorizationInvalidPass() {
        authorizationPage
                .LogIn(authorizationData.USER, authorizationData.InvalidPass)
                .CheckingErrorMessage();
    }

    @Test
    @DisplayName("Авторизация пользователя с невалидным Email")
    void AuthorizationInvalidEmail() {
        authorizationPage
                .LogIn(authorizationData.InvalidUSER, authorizationData.PASS)
                .CheckingErrorMessage();
    }
}
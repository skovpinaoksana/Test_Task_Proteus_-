package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.QuestionnairePage;
import page.AuthorizationPage;
import data.AuthorizationData;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;


public class QuestionnaireTests extends BaseTest{
    public static QuestionnairePage questionnairePage = new QuestionnairePage();
    public static AuthorizationPage authorizationPage = new AuthorizationPage();
    public static AuthorizationData authorizationData = new AuthorizationData();

    @BeforeAll
    static void beforeAll() {
        authorizationPage.LogIn(authorizationData.USER, authorizationData.PASS);
    }

    @DisplayName("Заполнение анкеты данными")
    @ParameterizedTest
    @CsvSource({
            "mari@gmail.com, Mari, female, false, false, 21",
            "masha@mail.ru, Маша, female, false, true, 22",
            "ivan@yandex.ru, Иван, male, true, false, 23",
            "mike@ya.ru, Misha, male, true, true, 21"
    })
    void FillQuestionnaire(String email, String name, String gender, Boolean checkbox11, Boolean checkbox12,
                           String dataSelect) {
        questionnairePage
                //заполненяем поле email в анкете
                .FieldEmailSet(email)
                //заполненяем поле Имя в анкете
                .FieldNameSet(name);

        //указываем пол в анкете
        if (gender.equals("female"))
            questionnairePage.GenderFemaleChoose();
        else
            questionnairePage.GenderMaleChoose();

        //устанавливаем чек-бокс варианта
        questionnairePage.CheckboxOptionOneOneSetState(checkbox11);
        questionnairePage.CheckboxOptionOneTwoSetState(checkbox12);

        //устанавливаем радио-баттон варианта
        if (dataSelect.equals("21"))
            questionnairePage.ChooseDataSelect21();
        if (dataSelect.equals("22"))
            questionnairePage.ChooseDataSelect22();
        if (dataSelect.equals("23"))
            questionnairePage.ChooseDataSelect23();

        questionnairePage
            //кликаем кнопку добавить
            .DataSend()

            //кликаем кнопку Ок в уведомлении "Данные добавлены"
            .DataAddedMessage();

        //находим элемент строки таблицы по email
        ElementsCollection userString = $$(byXpath(".//*[@id=\"dataTable\"]//*[text()[contains(.,'" + email + "')]]/.."));

        //проверяем поле email в строке таблицы
        userString.findBy(text(email)).should(exist);

        //проверяем поле Имя в строке таблицы
        userString.findBy(text(name)).should(exist);

        //проверяем поле пол в строке таблицы
        if (gender.equals("female"))
            userString.findBy(text("Женский")).should(exist);
        else
            userString.findBy(text("Мужской")).should(exist);

        //проверяем чек-бокс варианта в строке таблицы
        if (checkbox11 == true)
            userString.findBy(text("1.1")).should(exist);
        if (checkbox12 == true)
            userString.findBy(text("1.2")).should(exist);

        //проверяем радио-баттон варианта в строке таблицы
        if (dataSelect.equals("21"))
            userString.findBy(text("2.1")).should(exist);
        if (dataSelect.equals("22"))
            userString.findBy(text("2.2")).should(exist);
        if (dataSelect.equals("23"))
            userString.findBy(text("2.3")).should(exist);
    }

    @DisplayName("Заполнение анкеты. Неверный формат E-Mail")
    @Test
    void InvalidEmail (){
        questionnairePage
            .FieldEmailSet("")
            .DataSend();
        $(byXpath(".//*[@id=\"emailFormatError\"][text()[contains(.,'Неверный формат E-Mail')]]")).should(exist);
    }

    @DisplayName("Заполнение анкеты. Поле имя не может быть пустым")
    @Test
    void EmptyNameField (){
        questionnairePage
                .FieldEmailSet("mari@gmail.com")
                .FieldNameSet("")
                .DataSend();
        $(byXpath(".//*[@id=\"blankNameError\"][text()[contains(.,'Поле имя не может быть пустым')]]")).should(exist);
    }
}

package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class QuestionnairePage {
    private final SelenideElement
            FieldEmail =$(byId("dataEmail")),
            FieldName = $(byId("dataName")),
            Gender = $(byId("dataGender")),
            GenderFemale = $(byText("Женский")),
            GenderMale = $(byText("Мужской")),
            CheckboxOptionOneOne = $(byId("dataCheck11")),
            CheckboxOptionOneTwo = $(byId("dataCheck12")),
            DataSelect21 = $(byId("dataSelect21")),
            DataSelect22 = $(byId("dataSelect22")),
            DataSelect23 = $(byId("dataSelect23")),
            ButtonAdd = $(byId("dataSend")),
            DataAddedMessagePass =($(byText("Данные добавлены."))),
            ButtonOk = $(byXpath("//button[contains(text(), 'Ok')]"));

    public QuestionnairePage FieldEmailSet(String email) {
        FieldEmail.setValue(email);
        return this;
    }

    public QuestionnairePage FieldNameSet(String name) {
        FieldName.setValue(name);
        return this;
    }

    public QuestionnairePage GenderFemaleChoose() {
        Gender.click();
        GenderFemale.click();
        return this;
    }

    public QuestionnairePage GenderMaleChoose() {
        Gender.click();
        GenderMale.click();
        return this;
    }

    public QuestionnairePage CheckboxOptionOneOneSetState(Boolean state) {
        CheckboxOptionOneOne.setSelected(state);
        return this;
    }
    public QuestionnairePage CheckboxOptionOneTwoSetState(Boolean state) {
        CheckboxOptionOneTwo.setSelected(state);
        return this;
    }

    public QuestionnairePage ChooseDataSelect21() {
        DataSelect21.click();
        return this;
    }
    public QuestionnairePage ChooseDataSelect22() {
        DataSelect22.click();
        return this;
    }
    public QuestionnairePage ChooseDataSelect23() {
        DataSelect23.click();
        return this;
    }
    public QuestionnairePage DataSend() {
        ButtonAdd.click();
        return this;
    }

    public QuestionnairePage DataAddedMessage(){
        DataAddedMessagePass.shouldBe(text("Данные добавлены."));
        ButtonOk.click();
        return this;
    }

}

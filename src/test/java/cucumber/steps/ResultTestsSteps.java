package cucumber.steps;

import com.codeborne.selenide.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class ResultTestsSteps {
    String xpathGetResults = "//a/span[text()='Результаты анализов']";
    String xpathTitlePageGetResults = "//div/h2";
    String xpathOrderNumberInput = "//div//input[@name='orderNumber']";
    String xpathBirthdayInput = "//div//input[@name='birthday']";
    String xpathLastNameInput = "//div//input[@name='lastName']";
    String xpathButtonFindResults = "//button[text()='Найти результаты']";

    @When("Открываем страницу результатов анализов")
    public void openResultsPage() {
        $x(xpathGetResults).click();
    }

    @And("Нажимаем кнопку {string}")
    public void clickFindResultsButton(String buttonText) {
        $x(xpathButtonFindResults).click();
    }

    @Then("Отображается сообщение об ошибке {string}")
    public void errorMessageShouldAppear(String errorMessage) {
        SelenideElement warningMessage = Selenide.$x("//div[text()='Поля ']");
        warningMessage.should(Condition.appear);

        List<SelenideElement> spans = warningMessage.$$("span");
        String fieldsErrors = spans.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.joining(""));
        String expectedText = "Поля " + fieldsErrors + " обязательны для заполнения";
        $x("//div[text()='Поля ']").shouldHave(Condition.text(expectedText));
    }

    @And("Поле {string} подсвечивается красным")
    public void fieldShouldBeHighlightedInRed(String fieldName) {
        String xpathField = switch (fieldName) {
            case "Номер заказа" -> xpathOrderNumberInput;
            case "Дата рождения" -> xpathBirthdayInput;
            case "Фамилия" -> xpathLastNameInput;
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
        $x(xpathField).shouldHave(Condition.cssValue("border-color", "rgb(255, 0, 0)"));
    }

    @Then("Заголовок страницы должен содержать {string}")
    public void pageTitleShouldContain(String expectedTitle) {
        $x(xpathTitlePageGetResults).shouldHave(Condition.text(expectedTitle));
    }

    @When("Вводим {string} в поле {string}")
    public void entersValueInField(String value, String fieldName) {
        String xpathField = switch (fieldName) {
            case "Номер заказа" -> xpathOrderNumberInput;
            case "Дата рождения" -> xpathBirthdayInput;
            case "Фамилия" -> xpathLastNameInput;
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
        $x(xpathField).click();
        $x(xpathField).sendKeys(value);
    }

    @Then("Поле {string} должно содержать {string}")
    public void fieldShouldContainValue(String fieldName, String expectedValue) {
        String xpathField = switch (fieldName) {
            case "Номер заказа" -> xpathOrderNumberInput;
            case "Дата рождения" -> xpathBirthdayInput;
            case "Фамилия" -> xpathLastNameInput;
            default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
        };
        $x(xpathField).shouldHave(Condition.value(expectedValue));
    }
}

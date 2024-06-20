package cucumber.steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.*;

public class ChangeCitySteps {
    String xpathCityName = "//div[@id='headerCityNameDynamic']";
    String cityChangeDialog = "//div[@id='city']//a/span[contains(text(),'Выбрать')]";
    String searchInput =  "//form[@id='change-city-form']//input";
    String searchResultItemTemplate = "//div[@id='eac-container-select-basket-city-input']//b[text()='%s']";
    @When("Открываем диалог выбора города")
    public void openCitySelectionDialog() {
        $x(xpathCityName).should(Condition.visible);
        $x(xpathCityName).click();
        $x(cityChangeDialog).click();
    }

    @And("Ищем город {string}")
    public void searchCity(String cityName) {
        $x(searchInput).setValue(cityName);
    }

    @And("Выбираем город {string}")
    public void selectCity(String cityName) {
        $x(String.format(searchResultItemTemplate, cityName)).click();
    }

    @Then("На главной странице должен отображаться выбранный город {string}")
    public void cityShouldBeDisplayedOnPage(String cityName) {
        $x(xpathCityName).shouldHave(Condition.text(cityName));
    }
}

package cucumber.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {
    final String xpathSearchInput = "//input[@name='q']";
    @When("Вводим код анализа в поле поиска {string}")
    public void enterAnalysisCodeInSearchField(String analysisCode) {
        Selenide.$x(xpathSearchInput).setValue(analysisCode).pressEnter();
    }
}

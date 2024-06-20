package cucumber.steps;

import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;

public class BaseSteps {
    @Given("Открываем сайт {string}")
    public void openPage(String url) {
        open(url);
//        webdriver().shouldHave(currentFrameUrl("https://www.invitro.ru/radiology/login.html"));
    }
}

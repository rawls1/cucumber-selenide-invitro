package cucumber.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.*;

public class MainMenuSteps {
    @When("Выбираю разрешенный раздел {string}")
    public void selectSection(String section) {
        switch (section) {
            case "Пациентам":
                break;
            case "Врачам":
                break;
            case "Франчайзинг":
                break;
            case "Корпоративным клиентам":
                break;
            case "Прессе":
                break;
            default:
                throw new IllegalArgumentException("Недопустимый раздел: " + section);
        }
    }

    @And("Открываю страницу раздела {string}")
    public void sectionPageShouldOpen(String section) {
        $x("//div[@id='buttonOpenPopupTargetSTATICSTRINGFORID']").click();
        $$x("//div[@id='popupTargetSTATICSTRINGFORID']//span").findBy(Condition.partialText(section));
    }

}

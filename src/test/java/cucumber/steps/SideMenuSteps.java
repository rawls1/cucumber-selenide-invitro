package cucumber.steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.*;

public class SideMenuSteps {
    final String ServicePageTitle = "//div[@id='titlePage']/h1";
    @When("Раскрываем группу меню {string}")
    public void expandMenuGroup(String groupName) {
        actions().moveToElement($x("//div//a[text()='" + groupName + "']")).moveByOffset(133, 0).click().perform();
    }

    @And("Нажимаем на элемент меню {string} в группе {string}")
    public void openMenuGroupItem(String menuItem, String menuGroup) {
        String xpath = String.format("//div//a[text()='%s']/following-sibling::div//a[contains(text(), '%s')]",
                menuGroup,
                menuItem);
        $x(xpath).click();
    }

    @Then("Заголовок на странице должен содержать заголовок {string}")
    public void TitleOnPageShouldContain(String expectedTitle) {
        $x(ServicePageTitle)
                .shouldHave(Condition.partialText(expectedTitle));
    }

    @When("Нажимаем на группу меню {string}")
    public void openMenuGroup(String menuGroup) {
        String xpath = String.format("//div//a[text()='%s']", menuGroup);
        $x(xpath).click();
    }
}

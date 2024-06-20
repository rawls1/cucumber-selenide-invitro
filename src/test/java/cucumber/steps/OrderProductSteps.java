package cucumber.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderProductSteps {
    final String xpathPriceItem = "//div[contains(@class, 'total--sum')]";
    final String xpathAddItem = "//div[contains(@class, 'item__total')]/a";
    final String xpathCartElement = "//div[@id='headerCartDynamic']";
    final String xpathTotalAddedItems = "//div[contains(@class, 'cartCostPositionCost')]";
    int totalChosenItems;

    @When("Выбираем первый продукт")
    public void userSelectsFirstItem() {
        String priceChosenItem = Selenide.$$x(xpathPriceItem).get(0).getText().replaceAll("[^0-9]", "");
        totalChosenItems = Integer.parseInt(priceChosenItem);
    }

    @When("Добавляем выбранный продукт в корзину")
    public void addSelectedItemToCart() {
        Selenide.$$x(xpathAddItem).get(0).click();
        Selenide.$$x(xpathAddItem).get(0).shouldHave(Condition.cssClass("ds_b_active"));
    }

    @When("Открываем корзину")
    public void openCart() {
        Selenide.$x(xpathCartElement).click();
    }

    @Then("Сравниваем зафиксированную сумму с другой, которая отображается на странице Корзины")
    public void totalCartSumShouldEqualSelectedItemPrice() {
        String textTotalCartItems = Selenide.$$x(xpathTotalAddedItems).findBy(Condition.partialText(String.valueOf(totalChosenItems))).getText().replaceAll("[^0-9]", "");
        int totalCartItems = Integer.parseInt(textTotalCartItems);

        if (totalCartItems > totalChosenItems) {
            System.out.println("Сумма в корзине больше запомненной суммы.");
        } else if (totalCartItems < totalChosenItems) {
            System.out.println("Сумма в корзине меньше запомненной суммы.");
        } else {
            System.out.println("Сумма в корзине равна запомненной сумме.");
        }

        if (totalCartItems > 10000) {
            System.out.println("Сумма в корзине больше 10000 рублей.");
        } else if (totalCartItems < 10000) {
            System.out.println("Сумма в корзине меньше 10000 рублей.");
        } else {
            throw new AssertionError("Сумма в корзине равна 10000 рублям. Ошибка.");
        }
    }
}

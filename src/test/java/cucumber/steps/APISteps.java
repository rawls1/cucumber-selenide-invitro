package cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class APISteps {
    private Response response;
    @When("Отправляем GET запрос с параметром CODE {string}")
    public void sendGetRequestWithCode(String cityCode) {
        response = RestAssured.given()
                .queryParam("CODE", cityCode)
                .when()
                .get("https://www.invitro.ru/local/ajax/current-city.php")
                .then()
                .extract().response();
    }

    @Then("Код ответа должен быть {int}")
    public void verifyResponseCode(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);
    }

    @And("Тело ответа поле {string} должно содержать {string}")
    public void verifyResponseBody(String fieldName, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldName);
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}

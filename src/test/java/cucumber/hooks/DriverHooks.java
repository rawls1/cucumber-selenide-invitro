package cucumber.hooks;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class DriverHooks {
    @BeforeAll
    public static void setUpDriverBeforeScenario() {
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
//        Configuration.pageLoadStrategy = "eager";
    }
    @AfterAll
    public static void tearDownDriverAfterScenario() {
        closeWebDriver();
    }
}

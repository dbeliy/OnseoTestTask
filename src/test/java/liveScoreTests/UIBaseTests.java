package liveScoreTests;

import com.codeborne.selenide.*;
import org.aeonbits.owner.ConfigFactory;
import org.example.config.LiveScoreConfig;
import org.testng.annotations.AfterMethod;

import static com.codeborne.selenide.Selenide.*;

abstract public class UIBaseTests {

    private final String baseUrl = ConfigFactory.create(LiveScoreConfig.class).baseUrl();

    public void setUpDriver() {
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
    }

    public void openHomePage() {
        openHomePage("en/");
    }

    public void openHomePage(String language) {
        setUpDriver();
        open(baseUrl + language);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    protected void dismissCookieMessageIfVisible() {
        SelenideElement closeCookieMessageButton = $("#simpleCookieBarCloseButton");

        if (closeCookieMessageButton.is(Condition.visible)) {
            closeCookieMessageButton.click();
        }
    }
}

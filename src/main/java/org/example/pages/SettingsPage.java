package org.example.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SettingsPage {

    @FindBy(css = "#TZ_SELECT-label")
    private SelenideElement timezoneDropdown;

    @FindBy(css = "[class='Ri selectItem']")
    private ElementsCollection listTimezonesInDropdown;

    @FindBy(css = "[data-testid='settings-form_apply-button']")
    private SelenideElement settingsFormApplyButton;

    @Step("Click on apply button")
    public void clickSettingsFormApplyButton() {
        settingsFormApplyButton.shouldBe(Condition.visible.because("'Apply' button not visible")).click();
    }

    @Step("Get set timezone in dropdown")
    public String getSetTimezone() {
        return timezoneDropdown.shouldBe(visible.because("Timezone dropdown not visible")).getText();
    }

    @Step("Select timezone")
    public SettingsPage selectNewTimezone(String timezone) {
        timezoneDropdown.shouldBe(visible).click();
        SelenideElement selenideElement = listTimezonesInDropdown
                .shouldHave(CollectionCondition.sizeGreaterThan(0).because("List of timezones not found"))
                .find(text(timezone));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", selenideElement);

        selenideElement.click();
        return this;
    }
}

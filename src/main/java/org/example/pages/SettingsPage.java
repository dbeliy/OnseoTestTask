package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
        listTimezonesInDropdown
                .shouldHave(CollectionCondition.sizeGreaterThan(0).because( "List of timezones not found"))
                .find(text(timezone))
                .shouldBe(visible.because("Timezone not found: " + timezone))
                .click();
        return this;
    }
}

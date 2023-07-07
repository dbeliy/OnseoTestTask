package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class MenuSidebarPage {

    @FindBy(css = "#burger-menu__settings")
    private SelenideElement settingsButton;

    @Step("Click on 'Settings' button")
    public void clickSettingsButton() {
        settingsButton.shouldBe(Condition.visible.because("'Settings' button not visible")).click();
    }
}

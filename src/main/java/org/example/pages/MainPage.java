package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    @FindBy(css = "[data-testid*='match-calendar-link']")
    private ElementsCollection matchCalendarRows;

    @FindBy(css = "[class='En In']")
    private ElementsCollection matchRows;

    @FindBy(css = "#burger-menu-open")
    private SelenideElement siteMenuButton;

    public ElementsCollection getMatchCalendarRows() {
        matchCalendarRows.shouldHave(sizeGreaterThan(0).because("Match calendar rows not found"));
        return matchCalendarRows;
    }

    public ElementsCollection getMatchRows() {
        matchRows.shouldHave(sizeGreaterThan(0).because("Match rows not found"));
        return matchRows;
    }

    @Step("Open first game")
    public MainPage openFirstMatch() {
        getMatchRows().get(0).click();
        return this;
    }

    @Step("Open last available calendar row")
    public MainPage openLastAvailableCalendarRow() {
        SelenideElement element = getMatchCalendarRows().last();
        element.click();
        $("[data-testid='4__match-calendar-link-active']").shouldBe(Condition.exist);
        return this;
    }

    @Step("Open site menu")
    public void openSiteMenu() {
        siteMenuButton.shouldBe(visible.because("Site menu button not visible")).click();
    }
}

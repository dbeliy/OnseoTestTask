package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

public class MatchInformationSectionPage {

    @FindBy(css = "#score-or-time")
    private SelenideElement startMatchTime;

    @FindBy(css = "#SEV__status")
    private SelenideElement startMatchData;

    @FindBy(css = "#content-center")
    private SelenideElement matchSection;

    public String getMatchTime() {
        return startMatchTime.shouldBe(visible.because("Start match time not visible")).getText();
    }

    public String getMatchData() {
        return startMatchData.shouldBe(visible.because("Start data not visible")).getText();
    }

    @Step("Get full match data time")
    public String getFullMatchData() {
        return getMatchTime() + " " + getMatchData();
    }
    @Step("Check match information section opened")
    public void checkMatchSectionOpened() {
        matchSection.shouldBe(visible.because("Match section not visible"));
    }
}

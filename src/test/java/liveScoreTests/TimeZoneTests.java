package liveScoreTests;

import io.qameta.allure.Step;
import org.example.pages.MainPage;
import org.example.pages.MatchInformationSectionPage;
import org.example.pages.SettingsPage;
import org.example.pages.MenuSidebarPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.page;

public class TimeZoneTests extends UIBaseTests {

    private MainPage mainPage;
    private MatchInformationSectionPage matchPage;
    private MenuSidebarPage menuSidebarPage;
    private SettingsPage settingsPage;


    @BeforeMethod
    public void preconditions() {
        openHomePage();
        dismissCookieMessageIfVisible();
        mainPage = page(MainPage.class);
        matchPage = page(MatchInformationSectionPage.class);
        menuSidebarPage = page(MenuSidebarPage.class);
        settingsPage = page(SettingsPage.class);
    }

    @Test
    public void changeTimeZoneAndCheckTimeOfEventCorrectTest() {
        mainPage.openLastAvailableCalendarRow()
                .openFirstMatch();

        matchPage.checkMatchSectionOpened();
        LocalDateTime currentMatchTime = formatMatchTime(matchPage.getFullMatchData());

        mainPage.openSiteMenu();
        menuSidebarPage.clickSettingsButton();
        String setTimezone = settingsPage.getSetTimezone();

        String newTimezone = "UTC -10:00";
        settingsPage.selectNewTimezone(newTimezone)
                .clickSettingsFormApplyButton();
        matchPage.checkMatchSectionOpened();

        LocalDateTime updatedMatchTimeOnPage = formatMatchTime(matchPage.getFullMatchData());
        LocalDateTime expectedMatchTimeAfterChangeTimezone = expectedMatchTimeAfterChangeTimezone(currentMatchTime, setTimezone, newTimezone);
        Assert.assertEquals(updatedMatchTimeOnPage, expectedMatchTimeAfterChangeTimezone);
    }

    private LocalDateTime expectedMatchTimeAfterChangeTimezone(LocalDateTime currentMatchTime, String currentTimezone, String newTimezone) {
        ZoneId sourceTimeZone = ZoneId.of(currentTimezone.replace(" ", ""));
        ZoneId targetTimeZone = ZoneId.of(newTimezone.replace(" ", ""));

        ZonedDateTime sourceZonedDateTime = ZonedDateTime.of(currentMatchTime, sourceTimeZone);
        ZonedDateTime targetZonedDateTime = sourceZonedDateTime.withZoneSameInstant(targetTimeZone);

        return targetZonedDateTime.toLocalDateTime();
    }

    private LocalDateTime formatMatchTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy");
        return LocalDateTime.parse(dateTime + " " + Year.now().getValue(), formatter);
    }
}

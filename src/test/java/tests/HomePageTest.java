package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    @Test(description = "Open homepage and select language", groups ={"sanity", "regression"})
    void selectLanguage() {
        Allure.step("Open homepage");
        page.navigate(baseUrl);

        Allure.step("Select english language");
        homePage.englishLanguage.click();

        Allure.step("Validate welcome message");
        assertThat(homePage.welcomeMessage).isVisible();
    }

    @DataProvider(name = "search parameters")
    public static Object[][] searchParameters() {
        return new Object[][] {
                {"playwright", "Playwright"},
                {"selenium", "Selenium"},
                {"cypress", "Cypress"}
        };
    }

    @Test(description = "Use homepage search", dataProvider = "search parameters", groups ={"regression"})
    void searchWiki(String input, String expectedResult) {
        Allure.step("Open homepage");
        page.navigate(baseUrl);

        Allure.step("Set search language");
        homePage.searchLanguage.selectOption ("English");

        Allure.step("Enter search term");
        homePage.searchInput.fill(input);
        homePage.searchButton.click();

        Allure.step("Validate Article page is displayed");
        assertThat(articlePage.articleHeading).hasText(expectedResult);
        assertThat(articlePage.readSection).isVisible();
        assertThat(articlePage.editSection).isVisible();
        assertThat(articlePage.historySection).isVisible();
    }
}
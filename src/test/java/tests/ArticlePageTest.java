package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ArticlePageTest extends BaseTest {

    @Test(description = "Open Article edit section and validate results", groups ={"sanity", "regression"})
    void openEditSection() {
        Allure.step("Open homepage");
        page.navigate(baseUrl);

        Allure.step("Set search language");
        homePage.searchLanguage.selectOption ("English");

        Allure.step("Enter search term");
        homePage.searchInput.fill("Playwright");
        homePage.searchButton.click();

        Allure.step("Open edit section and check publish button is displayed");
        articlePage.editSection.click();
        assertThat(articlePage.publishButton).isVisible();
    }
}
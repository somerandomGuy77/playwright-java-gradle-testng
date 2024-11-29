package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    public final Locator englishLanguage;
    public final Locator searchInput;
    public final Locator searchButton;
    public final Locator welcomeMessage;
    public final Locator searchLanguage;

    public HomePage(Page page) {
        englishLanguage = page.locator("//*[@id='js-link-box-en']");
        searchInput = page.locator("//*[@id='searchInput']");
        searchButton = page.locator("//*[@id='search-form']//button");
        welcomeMessage = page.locator("//*[@id='Welcome_to_Wikipedia']");
        searchLanguage = page.locator("//*[@id='searchLanguage']");
    }
}
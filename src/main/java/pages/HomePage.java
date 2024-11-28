package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    public final Locator englishLanguage;
    public final Locator searchInput;
    public final Locator searchButton;
    public final Locator welcomeMessage;

    public HomePage(Page page) {
        englishLanguage = page.locator("//*[@id='js-link-box-en']");
        searchInput = page.locator("//*[@id='searchInput']");
        searchButton = page.locator("//*[@id='search-form']//button");
        welcomeMessage = page.locator("//*[@id='Welcome_to_Wikipedia']");
    }
}

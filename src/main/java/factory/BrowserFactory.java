package factory;

import com.microsoft.playwright.*;

public class BrowserFactory {

    private final Playwright playwright;

    public BrowserFactory(Playwright playwright) {
        this.playwright = playwright;
    }

    public Browser launchBrowser(String browserType, Boolean isHeadless) {
        return switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
            default -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
        };
    }
}

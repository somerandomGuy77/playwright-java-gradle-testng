package pages;

import com.microsoft.playwright.*;

public class ArticlePage {

    public final Locator articleHeading;
    public final Locator readSection;
    public final Locator editSection;
    public final Locator historySection;

    public ArticlePage(Page page) {

        articleHeading = page.locator("//*[@id='firstHeading']/span");
        readSection = page.locator("//*[@id='ca-view']");
        editSection = page.locator("//*[@id='ca-edit']");
        historySection = page.locator("//*[@id='ca-history']");
    }
}
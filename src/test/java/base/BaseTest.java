package base;

import factory.BrowserFactory;
import io.qameta.allure.Allure;
import org.testng.annotations.*;
import com.microsoft.playwright.*;
import pages.ArticlePage;
import pages.HomePage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String baseUrl;
    protected String browserName;
    protected Boolean isHeadless;

    protected HomePage homePage;
    protected ArticlePage articlePage;

    @BeforeMethod(alwaysRun = true)
    public void beforeEachTest() {

        // Config values from build.gradle or cmd arguments
        browserName = System.getProperty("browser");
        baseUrl = System.getProperty("url");
        isHeadless = Boolean.valueOf(System.getProperty("headless"));

        // Create playwright and browse instances
        playwright = Playwright.create();
        BrowserFactory browserFactory = new BrowserFactory(playwright);
        browser = browserFactory.launchBrowser(browserName, isHeadless);

        // Start recording video
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("build/video"))
                .setRecordVideoSize(640, 480));

        // Start tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();

        homePage = new HomePage(page);
        articlePage = new ArticlePage(page);

    }

    @AfterMethod(alwaysRun = true)
    public void afterEachTest() throws IOException {

        // Take screenshot and add to report
        InputStream screenshotStream = new ByteArrayInputStream(page.screenshot());
        Allure.addAttachment("Example Screenshot", "image/png", screenshotStream, ".png");

        // Stop tracing and add to report
        var traceFilePath = "build/trace/trace-" + UUID.randomUUID() + ".zip";
        var traceFile = Paths.get(traceFilePath);
        context.tracing().stop(new Tracing.StopOptions().setPath(traceFile));
        Allure.addAttachment("Playwright Trace", "application/zip", Files.newInputStream(traceFile), "zip");

        // Get video path while context is active
        var videoPath = context.pages().get(0).video().path().toString();
        var videoFile = Paths.get(videoPath);

        // Video files generated after context is closed
        page.close();
        context.close();
        browser.close();
        playwright.close();

        // Add video file to report
        InputStream videoStream = Files.newInputStream(videoFile);
        Allure.addAttachment("Test Video", "video/webm", videoStream, ".webm");

    }
}

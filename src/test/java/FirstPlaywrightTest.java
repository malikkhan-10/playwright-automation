import java.nio.file.Paths;
import com.microsoft.playwright.*;

public class FirstPlaywrightTest {
    public static void main(String[] args) {
        // 1. Start Playwright
        try (Playwright playwright = Playwright.create()) {
            
            // 2. Launch the browser (Chromium)
            // .setHeadless(false) allows you to actually see the browser pop up
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
            
            // 3. Open a new page/tab
            Page page = browser.newPage();
            
            // 4. Navigate to a website
            page.navigate("https://www.google.com");
            
            // 5. Print the title to the console
            System.out.println("The page title is: " + page.title());
            
            // 6. Take a screenshot (saved in your project folder)
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
            
            // 7. Close the browser
            browser.close();
            
            System.out.println("Test completed successfully!");
        }
    }
}
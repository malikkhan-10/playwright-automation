import com.microsoft.playwright.*;

public class FirstTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Launch Chromium in "Headed" mode so you can see it
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(50));
            
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            
            System.out.println("Page Title: " + page.title());
            
            browser.close();
        }
    }
}
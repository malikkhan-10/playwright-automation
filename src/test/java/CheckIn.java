import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;

public class CheckIn {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Launch browser in headed mode so you can watch it fill the form
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(800)); // Slowed down so you can see the typing
            
            Page page = browser.newPage();

            // 1. Navigate to the registration page
            page.navigate("https://sauce-demo.myshopify.com/");

          
     	   Locator productImg = page.getByAltText("Grey jacket");
            page.getByAltText("Grey jacket").click();
            
            page.locator("#add").click();
            
            // Wait a few seconds to see the result before closing
            page.waitForTimeout(3000);
            
            assertThat(page).hasURL(Pattern.compile(".*/collections"));
            System.out.println("Confirmation: Test Passed!");

            browser.close();
        }
    }
}




	

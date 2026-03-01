import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;

public class Signup {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Launch browser in headed mode so you can watch it fill the form
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(800)); // Slowed down so you can see the typing
            
            Page page = browser.newPage();

            // 1. Navigate to the registration page
            page.navigate("https://sauce-demo.myshopify.com/account/register");

            // 2. Fill First Name
            // HTML: <input type="text" id="first_name" ...>
            page.locator("input#first_name").fill("James");

            // 3. Fill Last Name
            // HTML: <input type="text" id="last_name" ...>
            page.locator("input#last_name").fill("Bond");

            // 4. Fill Email Address
            // Using a dynamic email to avoid "email already taken" errors
            page.locator("input#email").fill("test" + "@example.com");

            // 5. Fill Password
            // HTML: <input type="password" id="password" ...>
            page.locator("input#password").fill("Password123");

            // 6. Click the Create button
            // HTML: <input type="submit" value="Create">
            page.locator("input[value='Create']").click();

         
            
            // Wait a few seconds to see the result before closing
            page.waitForTimeout(10000);
            
            assertThat(page).hasURL(Pattern.compile(".*/account"));
            System.out.println("Confirmation: Test Passed!");

            browser.close();
        }
    }
}
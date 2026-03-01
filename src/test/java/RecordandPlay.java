import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class RecordandPlay {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = browser.newPage();
      
   // --- 1. NAVIGATION PHASE ---
   // Open the base URL of the Shopify demo store
   page.navigate("https://sauce-demo.myshopify.com/");

   // Select the specific product "Grey jacket" by its link role
   // Note: Using the full name including currency can be brittle if prices change
   page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Grey jacket Grey jacket £")).click();


   // --- 2. CART & BASKET ACTIONS ---
   // Add the selected item to the shopping cart
   page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).click();
  
   // Wait a few seconds to see the result before closing
   page.waitForTimeout(4000);
   
   // Click the 'Check Out' link to view the cart summary
   page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Check Out")).click();

   // Provide a custom note for the order
   // Note: Click/Dbclick is usually optional as .fill() focuses the element automatically
   page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Add a note to your order...")).fill("Thank you my friend");

   // Proceed from the cart summary to the official checkout/shipping page
   page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Check Out")).click();


   // --- 3. SHIPPING & CHECKOUT FORM ---
   // Enter company information (optional field)
   page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Company (optional)")).fill("new company");

   // Enter the primary street address
   page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Address")).fill("B-F91");

   // Enter specific apartment or suite number
   page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Apartment, suite, etc. (")).fill("901");

   // Enter the city for delivery
   page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("City")).fill("Barcelona");


   // --- 4. FINAL SUBMISSION ---
   // Submit the order by clicking the Pay Now button
   page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pay now")).click();
   System.out.println("Confirmation: Test Passed!");
    }
  }
}
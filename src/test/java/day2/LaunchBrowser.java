package day2;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {

        // Auto-close everything using try-with-resources
        try (Playwright playwright = Playwright.create()) {

            // Launch browser maximized
           // Browser browser = playwright.chromium().launch(
            Browser browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setArgs(Arrays.asList("--start-maximized"))
            );

            // Remove viewport restrictions so it follows the maximized window
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setViewportSize(null)
            );

            // Open new page
            Page page = context.newPage();

            // Navigate to login page
            page.navigate("https://qa.app.waas.sdsaz.us/auth/login");

            // Enter username
            //page.locator("#txt_username").fill("admin@waas.com"); or
            page.getByTestId("txt_username").type("admin@waas.com");

            // Enter password
            page.locator("#txt_password").fill("login12*");

            // Click login button
            page.locator("#btn_login").click();

            // Wait for backend API calls, DOM load, and network idle
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // Assert the page title after successful login
            assertThat(page).hasTitle("Dashboard");

            // Browser closes automatically when try-with-resources ends
        }
    }
}

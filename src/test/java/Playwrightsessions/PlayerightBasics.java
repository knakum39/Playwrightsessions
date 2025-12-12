package Playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayerightBasics {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
      //  Browser browser = playwright.chromium().launch();
        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.google.com");

        String title = page.title();
        System.out.println("The page title is:" + title);

        String url = page.url();
        System.out.println("The URL is" + url);

        browser.close();
        playwright.close();
    }
}

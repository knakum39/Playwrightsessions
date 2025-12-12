package Playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandle {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    Page page = browser.newPage();
    page.navigate("https://www.londonfreelance.org/courses/frames/index.html");

    //using css selector
    //String header = page.frameLocator("frame[name='main']").locator("h2").textContent()
        // System.out.println(header);

       // String header = page.frame("main").locator("h2").textContent();
        //System.out.println(header);

        //using x-path
        String header = page.frameLocator("//frame[@name='main']").locator("h2").textContent();
        System.out.println(header);
    }
}

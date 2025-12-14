package Playwrightsessions;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class XpathUnion {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

       BrowserContext context =  browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page page = context.newPage();
        page.navigate("https://academy.naveenautomationlabs.com/");
        Locator login = page.locator(("//a[text()='Login'] | //a[text()='LOGIN']"));
        System.out.println(login.textContent());


        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }
}

package Playwrightsessions;

import com.microsoft.playwright.*;

public class BrowserContextConcept {
    public static void main(String[] args) {


        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext bctx1 = browser.newContext();
        Page p1 = bctx1.newPage();
        p1.navigate("https://www.orangehrm.com/en/30-day-free-trial");
        p1.fill("#Form_getForm_subdomain","Krishna");
        System.out.println( p1.title());

        BrowserContext bctx2 = browser.newContext();
        Page p2 = bctx2.newPage();
        p2.navigate("http://google.com/");
        System.out.println(p2.title());

        BrowserContext bctx3 = browser.newContext();
        Page p3 = bctx3.newPage();
        p3.navigate("https://www.indiabix.com/");
        System.out.println(p3.title());

        p1.close();
        bctx1.close();

        p2.close();
        bctx2.close();

        p3.close();
        bctx3.close();

    }
}

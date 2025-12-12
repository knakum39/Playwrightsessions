package Playwrightsessions;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class TextSelector {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

//text locators
        /*BrowserContext btctx = browser.newContext();
        Page page = btctx.newPage();*/


        BrowserContext context = playwright.chromium().launchPersistentContext(
                Paths.get("my-profile"),
                new BrowserType.LaunchPersistentContextOptions()
                        .setHeadless(false)
        );
        Page page = context.newPage();

        //page.navigate("https://www.orangehrm.com/en/30-day-free-trial");

        // handle cookie banner
       // page.locator("text=Allow all").click();

        //Method-1: when we have unique text element
       // page.locator("text=Privacy Policy").first().click();
        //page.locator("text=Privacy Policy").last().click();

        /*Locator links = page.locator("text=Privacy Policy");
        System.out.println(links.count());*/

        //Method-2: iterate through loop, know element and perform action
       /* Locator links = page.locator("text=Privacy Policy");

        for(int i=0; i<links.count();i++){
            String text =(links.nth(i).textContent());
            if(text.contains("DPF Privacy Policy")){
                links.nth(i).click();
                break;
            }
        }
*/
        page.navigate("https://demo.opencart.com/index.php?route=account/login");

        String header = page.locator("h1:has-text('Log in to your OpenCart account')").textContent();
        System.out.println(header);

    }
}

package Playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CreateContractBeta {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://alpha.app.waas.sdsaz.us/auth/login");
        //page.locator("//input[@id='txt_username']").fill("admin@waas.com");
        page.locator("#txt_username").fill("admin@waas.com");
        page.locator("//input[@id='txt_password']").fill("login12*");
        page.locator("//button[@id='btn_login']").click();
        page.locator("//a[@id='menuContracts']").click();
        page.locator("//a[@id='submenuContracts']").first().click();
        page.locator("//i[class='fa fa-plus-circle fs-4 ng-star-inserted']").click();

    }
}

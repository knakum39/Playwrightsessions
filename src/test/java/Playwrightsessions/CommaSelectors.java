package Playwrightsessions;

import com.microsoft.playwright.*;

public class CommaSelectors {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
       Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
       Page page = browser.newPage();
       page.navigate("https://academy.naveenautomationlabs.com/");
       //page.locator("a:has-text('Login')," + "a:has-text('LOGIN')," + "a:has-text('Sign In')").click();
      // page.locator("a:has-text('Courses')," + "a:has-text('Demo Site')");

              Locator imp_ele = page.locator("a:has-text('Courses'), a:has-text('Demo Site'), a:has-text('EXPLORE COURSES')");

        System.out.println(imp_ele.count());

        if(imp_ele.count()==3){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }
    }

}

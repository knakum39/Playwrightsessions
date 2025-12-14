package Playwrightsessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;

import static Playwrightsessions.CreateContractQA.clickNext;
import static Playwrightsessions.CreateContractQA.waitForSpinner;

public class CreateContractAlpha {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));

            BrowserContext context = browser.newContext();

            // Start tracing before creating / navigating a page.
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));

            Page page = context.newPage();

            // ============================
            // LOGIN
            // ============================
            page.navigate("https://alpha.app.waas.sdsaz.us/auth/login");
            page.locator("#txt_username").fill("admin@waas.com");
            page.locator("#txt_password").fill("login12*");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();


            // ============================
            // NAVIGATE → CONTRACTS → NEW CONTRACT
            // ============================
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Contracts")).click();
            page.locator("#submenuContracts").first().click();
            page.locator("#btn_add_entity").click();


            // ============================
            // SELECT DEALERSHIP / ENTITY
            // ============================
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("dropdown trigger")).click();
            page.getByRole(AriaRole.SEARCHBOX).fill("KN");
            page.getByText("KN_test_KARS Inc |").click();
            clickNext(page);


            // ============================
            // VEHICLE DETAILS ENTRY
            // ============================
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter VIN Number"))
                    .fill("1N6AA1ED1LN500024");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Sale Odometer"))
                    .fill("1,0000");


            // --- VEHICLE PRICE FIELD (PrimeNG) ---
            Locator vehiclePrice = page.locator("#txtCurrency");
            vehiclePrice.click();
            vehiclePrice.press("Control+A");
            vehiclePrice.press("Backspace");

            // Delayed typing ensures Angular change events fire
            vehiclePrice.type("1250", new Locator.TypeOptions().setDelay(100));
            page.locator("body").click(); // blur → validation
            waitForSpinner(page);

            clickNext(page);


            // ============================
            // LENDER SEARCH
            // ============================
            // Click magnifying glass search icon
            page.locator("i.fas.fa-solid.fa-magnifying-glass").click();

            // Click “Navigate to Lender Search”
            page.locator("#customlink_navigateToEntity_lenderNumber_1").click();

            // Proceed to lender selection step
            clickNext(page);


            // ============================
            // SELECT COVERAGE (PrimeNG CHECKBOX)
            // ============================
            // First checkbox → Calculate Contract
            page.locator("div.p-checkbox-box").first().click();
            waitForSpinner(page);

            // Second checkbox → Coverage selection
            Locator coverage = page.locator("div.p-checkbox-box").nth(1);
            coverage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            coverage.click();
            waitForSpinner(page);

            clickNext(page);


            // ============================
            // CUSTOMER INFORMATION
            // ============================

           // page.getByLabel("Business").click();
            page.locator(".p-radiobutton-box:not(.p-highlight)").click();

            //page.locator("div:nth-child(2) .p-radiobutton-box").click();

            page.locator("#txt_first_name").fill("Contact_test1");
            page.locator("#txt_last_name").fill("Company_test1");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter a location"))
                    .fill("987");
            page.getByText("U.S. Route 1").click();


            page.locator("#txt_first_name").click();
            page.locator("#txt_first_name").fill("couyerFN");
            page.locator("#txt_last_name").click();
            page.locator("#txt_last_name").fill("cobuyerLn");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next ")).click();clickNext(page);

            // Stop tracing and export it into a zip archive.
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));

          /*  Locator visibleNextButtons = page.locator("button:has-text('Next') >> visible=true");

// Customer Next
            visibleNextButtons.nth(0).click();
            waitForSpinner(page);

            // Co-Buyer Next (use last — it's always the NEXT in flow)
            visibleNextButtons.nth(1).click();
            waitForSpinner(page);*/



/*
           if(page.locator("#btn_next_customer_information").isVisible()){
            page.locator("#btn_next_customer_information").click();}

// Click CoBuyer Next only if it exists & is visible
            if (page.locator("#btn_next_coBuyer_information").isVisible()) {
                page.locator("#btn_next_coBuyer_information").click();
            }
*/



            //clickNext(page); // Additional step


            // ============================
            // CREATE CONTRACT
            // ============================
            waitForSpinner(page);  // ensures previous step is finished

            page.waitForSelector("#btn_create_contract, button:has-text('Create Contract')");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Contract ")).click();
            page.getByText("×Contract created").click();


   /*         // ============================
            // OPEN CONTRACT
            // ============================
            // Contract row
            Locator contractRow = page.getByRole(AriaRole.CELL,
                    new Page.GetByRoleOptions().setName("PNCVSC2512500025"));

            contractRow.click();
            contractRow.dblclick();

            // Copy contract number
            page.locator("body").press("ControlOrMeta+c");


            // ============================
            // CLOSE + SEARCH CONTRACT
            // ============================
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close ")).click();

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search"))
                    .fill("PNCVSC2512500024");

            Page newPage = page.waitForPopup(() ->
                    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("PNCVSC2512500024")).click()
            );

            newPage.navigate("https://alpha.app.waas.sdsaz.us/orders/details/186134");
*/
        }
    }
}

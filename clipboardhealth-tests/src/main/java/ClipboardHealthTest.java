import Common.TestBaseClass;
import PageObjects.DashboardPage;
import PageObjects.HamburgerMenuPage;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ClipboardHealthTest extends TestBaseClass {

    @Test(description = "Navigate to second highest price product on Amazon search", groups = "nightly")
    public void testNavigateToSecondHighestPriceItemOnAmazon() throws Exception {
        DashboardPage dashboardPage = new DashboardPage(driver);
        HamburgerMenuPage menuPage = dashboardPage.getNavigationZone().goToHamburgerMenu();
        menuPage.selectMenuAndWaitToSelectSubMenu("TV, Appliances, Electronics")
                .selectSubMenuAndSeeResults("Televisions")
                .refineBasedOn("Brands","Samsung")
                .sortBasedOn("Price: High to Low")
                .selectResultBasedOnItemLocation(2);

        sleep(5000);
    }
}

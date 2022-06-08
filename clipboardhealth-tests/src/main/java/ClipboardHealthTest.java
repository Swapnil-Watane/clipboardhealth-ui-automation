import Common.TestBaseClass;
import PageObjects.DashboardPage;
import PageObjects.HamburgerMenuPage;
import org.testng.annotations.Test;

public class ClipboardHealthTest extends TestBaseClass {

    @Test(description = "Navigate to second highest price product on Amazon search", groups = "nightly")
    public void testNavigateToSecondHighestPriceItemOnAmazon() throws Exception {
        DashboardPage dashboardPage = new DashboardPage(driver);
        HamburgerMenuPage menuPage = dashboardPage.getNavigationZone().goToHamburgerMenu();
        menuPage.hamburgerMenuSelect("TV, Appliances, Electronics");
    }
}

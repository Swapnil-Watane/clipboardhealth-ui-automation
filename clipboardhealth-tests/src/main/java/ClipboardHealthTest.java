import Common.TestBaseClass;
import PageObjects.DashboardPage;
import org.testng.annotations.Test;

public class ClipboardHealthTest extends TestBaseClass {

    @Test(description = "Navigate to second highest price product on Amazon search", groups = "nightly")
    public void testNavigateToSecondHighestPriceItemOnAmazon() throws Exception {
        DashboardPage dashboardPage = new DashboardPage(driver);
    }
}

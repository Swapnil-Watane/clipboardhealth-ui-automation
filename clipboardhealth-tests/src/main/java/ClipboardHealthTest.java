import Common.TestBaseClass;
import PageObjects.DashboardPage;
import PageObjects.HamburgerMenuPage;
import PageObjects.ProductPage;
import beans.SearchBean;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ClipboardHealthTest extends TestBaseClass {

    @Test(description = "Navigate to second highest price product on Amazon search", groups = "regression")
    public void testNavigateToSecondHighestPriceItemOnAmazon() throws Exception {
        SearchBean searchBean = new SearchBean();
        searchBean.sethMenu("TV, Appliances, Electronics").sethSubMenu("Televisions")
                .setbrowseCategory("Brands").setbrowseCategoryFilter("Samsung")
                .setstoring("Price: High to Low").setitemLocation(2);

        SoftAssert softly = new SoftAssert();
        DashboardPage dashboardPage = new DashboardPage(driver);
        HamburgerMenuPage menuPage = dashboardPage.getNavigationZone().goToHamburgerMenu();
        ProductPage productPage = menuPage.selectMenuAndWaitToSelectSubMenu(searchBean.gethMenu())
                .selectSubMenuAndSeeResults(searchBean.gethSubMenu())
                .refineBasedOn(searchBean.getbrowseCategory(), searchBean.getbrowseCategoryFilter())
                .sortBasedOn(searchBean.getstoring())
                .selectResultBasedOnItemLocation(searchBean.getitemLocation());

        softly.assertTrue(productPage.isAboutThisItemSectionPresent());
        System.out.println(productPage.getAboutThisItemSection());
        Reporter.log(productPage.getAboutThisItemSection());
    }
}

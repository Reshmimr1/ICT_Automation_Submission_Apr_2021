import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class AjioTest {
    public WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void openBrowser1(String browser) throws Exception{
        driver = Browser.openBrowser(browser);
    }

    @Test(priority = 1)
    public void searchProduct() throws IOException {
        Reports.createTest("Verify Jacket");
        SearchProduct search1 = new SearchProduct(driver);
        search1.prdJacket("Jacket");
        search1.verifyPrdJacket("Jacket");
    }

    @Test(dataProvider = "SearchText",priority = 2)
    public void productUnavailable(String searchText) throws IOException {
        Reports.createTest("Verify Smart Phone");
        ProductUnavailable search2 = new ProductUnavailable(driver);
        search2.smartPhone("Smart Phone");
        search2.verifySmartPhone("Smart Phone");
    }

    @Test(priority = 3)
    public void AddToBag() throws IOException {
        Reports.createTest("Verify Cart");
        AddToBag search3 = new AddToBag(driver);
        search3.buyJacket("Jacket");
        search3.verifyAddJacket("Jacket");
    }

    @Test(priority = 4)
    public void VerifyMenu() throws IOException {
        Reports.createTest("Verify Menu");
        VerifyMenu search4 = new VerifyMenu(driver);
        search4.menuItems("Men,Women,Kid,Home");
        search4.verifyMenuItems("Men,Women,Kid,Home");
    }

    @Test(priority = 5)
    public void PriceLowest() throws IOException {
        Reports.createTest("Sort By Price-Lowest");
        PriceLowest search5 = new PriceLowest(driver);
        search5.prdShoe("Shoe");
        search5.verifyPrdShoe("Shoe");
    }

    @Test(priority = 6)
    public void PriceHighest() throws IOException {
        Reports.createTest("Sort By Price-Highest");
        PriceHighest search6 = new PriceHighest(driver);
        search6.shoeHighPrice("Shoe");
        search6.verifyShoe("Shoe");
    }

    @Test(priority = 7)
    public void MenMenu() throws IOException {
        Reports.createTest("Verify Menu");
        MenMenu search7 = new MenMenu(driver);
        search7.menCategory("Menu");
    }
    @Test(priority = 8)
    public void ProceedShipping() throws IOException {
        Reports.createTest("Verify Shipping");
        ProceedShipping search8 = new ProceedShipping(driver);
        search8.buyJacket("Jacket");
        search8.verifyShipping("Jacket");
    }

    @Test(priority = 9)
    public void VerifyFooter() throws IOException {
        Reports.createTest("Verify footer");
        VerifyFooter search9 = new VerifyFooter(driver);
        search9.ajioFooter("footerTexts");
        search9.verifyAjioFooter("footerTexts");
    }

    @Test(priority = 10)
    public void ReturnPolicy() throws IOException {
        Reports.createTest("Verify Return");
        ReturnPolicy search10 = new ReturnPolicy(driver);
        search10.returnJacket("ReturnPolicy");
        search10.verifyReturnJacket("ReturnPolicy");
    }
    @Test(priority = 11)
    public void CustomerCare() throws IOException {
        Reports.createTest("Verify FAQ");
        CustomerCare search11 = new CustomerCare(driver);
        search11.custFAQSearch("FAQ");
        search11.verifyCustCare("FAQ");
    }

    @Test(priority = 12)
    public void FilterGender() throws IOException {
        Reports.createTest("Verify filter");
        FilterGender search12 = new FilterGender(driver);
        search12.filterJacket("Total");
        search12.menJacket("Men");
        search12.womenJacket("Women");
        search12.verifyFilterJacket("Total,Men,Women");
    }

    @Test(dataProvider = "SearchText",priority = 13)
    public void BrandPuma(String searchKey) throws IOException {
        Reports.createTest("Verify PUMA");
        BrandPuma search13 = new BrandPuma(driver);
        search13.prdPuma("PUMA");
        search13.verifyPrdPuma("PUMA");
    }

    @Test(dataProvider = "SearchText",priority = 14)
    public void FilterAdidas(String searchKey) throws IOException {
        Reports.createTest("Verify ADIDAS");
        FilterAdidas search14 = new FilterAdidas(driver);
        search14.prdAdidas("ADIDAS");
        search14.verifyAdidas("ADIDAS");
    }
   
    @Test(priority = 15)
    public void VerifyURL() throws IOException {
        Reports.createTest("Verify URL");
        VerifyURL search15 = new VerifyURL(driver);
        search15.initialLink("Men,Women,Kid");
        search15.menLink("Men");
        search15.womenLink("Women");
        search15.kidLink("Kid");
    }

    @DataProvider(name = "SearchText")
    public Object[][] getDataProviderData(Method m) {
        if (m.getName().equalsIgnoreCase("BrandPuma"))
            return new Object[][]{
                    {"PUMA"}
            };
        else if (m.getName().equalsIgnoreCase("FilterAdidas"))
        {
            return new Object[][]{
                    {"ADIDAS"}
            }; }
        else
        {
            return new Object[][]{
                    {"Smart Phone"}
            }; }
    }

    @AfterMethod
    public void closeBrowser(){
        Browser.closeBrowser(driver);
    }
}



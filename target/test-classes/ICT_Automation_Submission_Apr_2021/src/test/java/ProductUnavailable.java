import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;

public class ProductUnavailable extends BasePage {

    private WebDriver driver;

    public ProductUnavailable(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By searchPhone = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchPhoneClick = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/button/span");
    private By searchLength = By.xpath("//*[@id=\"products\"]/div[2]/div/div[1]");

    public void smartPhone(String searchText) {
        driver.findElement(searchPhone).sendKeys(searchText);
        click(searchPhoneClick);
        String pageTitle=driver.getTitle();
        System.out.println(pageTitle);
        Reports.extentTest.log(Status.INFO,"Select phone "+searchText);
    }

    public void verifySmartPhone(String searchText) throws IOException {
        String actualText = driver.findElement(searchLength).getText();

        try {
            Assert.assertTrue(actualText.contains("0 Items"));
            Reports.extentTest.log(Status.PASS, "Blank page verified as "+actualText , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception f) {
            System.out.println(f);
            Reports.extentTest.log(Status.FAIL,"Actual length is "+actualText ,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }

    }
}


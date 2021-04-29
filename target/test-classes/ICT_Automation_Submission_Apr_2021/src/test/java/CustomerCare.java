import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;

public class CustomerCare extends BasePage {
    private WebDriver driver;

    public CustomerCare(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By custSearch=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[1]/ul/li[2]/a");
    private By custFAQ = By.xpath("//*[@id=\"selfcare\"]/div[2]/p");
    private By shipFAQ=By.xpath("//*[@id=\"left-tabs-example-tab-0\"]/label");
    private By cancelFAQ=By.xpath("//*[@id=\"left-tabs-example-tab-1\"]/label");

    public void custFAQSearch(String custCheck){
        click(custSearch);
        String faqCustCare = driver.findElement(custFAQ).getText();
        Assert.assertTrue(faqCustCare.contains("Frequently"));
        System.out.println(faqCustCare);
        Reports.extentTest.log(Status.INFO,"Verify FAQs "+custCheck);
    }
    public void verifyCustCare(String custCheck) throws IOException {
        String shipFaq = driver.findElement(shipFAQ).getText();
        String cancelFaq = driver.findElement(cancelFAQ).getText();
        try{
            Assert.assertTrue(shipFaq.contains("Shipping FAQs"));
            Assert.assertTrue(cancelFaq.contains("Cancellation FAQs"));
            System.out.println(shipFaq+", "+cancelFaq);
            Reports.extentTest.log(Status.PASS,"Text1 verified as "+shipFaq+" ,Text2 verified as "+cancelFaq, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Text1 verified as "+shipFaq+" ,Text2 verified as "+cancelFaq,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}





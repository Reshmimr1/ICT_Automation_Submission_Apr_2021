import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;


public class VerifyFooter extends BasePage {
    private WebDriver driver;

    public VerifyFooter(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By whoWeAre = By.xpath("//*[@id=\"appContainer\"]/div[3]/footer/div[2]/div/div[1]/section[1]/div[2]/a[1]");
    private By joinTeam = By.xpath("//*[@id=\"appContainer\"]/div[3]/footer/div[2]/div/div[1]/section[1]/div[2]/a[2]");
    private By termsConditions = By.xpath("//*[@id=\"appContainer\"]/div[3]/footer/div[2]/div/div[1]/section[1]/div[2]/a[3]");
    private By footerContent = By.xpath("//*[@id=\"appContainer\"]/div[3]/footer/div[2]/div/div[1]/section[1]");

    public void ajioFooter(String footerText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String footerAjio = driver.findElement(footerContent).getText();
        System.out.println(footerAjio);
        Reports.extentTest.log(Status.INFO,"Verifying "+footerText);
    }

    public void verifyAjioFooter(String footerName) throws IOException {
        String actualText1= driver.findElement(whoWeAre).getText();
        String actualText2= driver.findElement(joinTeam).getText();
        String actualText3= driver.findElement(termsConditions).getText();
        try{
            Assert.assertTrue(actualText1.contains("Who We Are"));
            Assert.assertTrue(actualText2.contains("Join Our Team"));
            Assert.assertTrue(actualText3.contains("Terms & Conditions"));
            Reports.extentTest.log(Status.PASS,"Text1 verified as "+actualText1+" ,Text2 verified as "+actualText2+" ,Text3 verified as "+actualText3, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Text1 not matching, actual text1 is "+actualText1+" ,Text2 verified as "+actualText2+" ,Text3 verified as "+actualText3,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

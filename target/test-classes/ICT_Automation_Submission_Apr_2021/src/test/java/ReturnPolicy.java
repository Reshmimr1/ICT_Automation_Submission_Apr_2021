import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReturnPolicy extends BasePage {
    private WebDriver driver;

    public ReturnPolicy(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By search = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchJacket = By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[2]/a");
    private By selectJacket = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[2]");
    private By returnPolicy = By.xpath("//*[@id=\"product-details-tab-tabpane-2\"]/div/span[1]");

    public void returnJacket(String jacketName) {
        click(search);
        click(searchJacket);
        click(selectJacket);
        for(String childWindow : driver.getWindowHandles()){
            driver.switchTo().window(childWindow);
        }
        String returnText = driver.findElement(returnPolicy).getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)",returnText);
        Reports.extentTest.log(Status.INFO,"Verifying "+jacketName);
    }
    public void verifyReturnJacket(String jacketName) throws IOException {
        String actualText = driver.findElement(returnPolicy).getText();
        System.out.println(actualText);
        try{
            Assert.assertTrue(actualText.contains("return and exchange"));
            Reports.extentTest.log(Status.PASS,"Return content verified as "+actualText, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Return text not matching, actual text is "+actualText,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

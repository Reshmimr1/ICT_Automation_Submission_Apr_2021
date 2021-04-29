import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;

public class VerifyURL extends BasePage {
    private WebDriver driver;

    public VerifyURL(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By searchMen=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[1]/a");
    private By searchWomen=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[2]/a");
    private By searchKids=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[3]/a");


    public void initialLink(String initialLink){
        String initialURL = driver.getCurrentUrl();
        System.out.println(initialURL);
        Reports.extentTest.log(Status.INFO,"Current URL is "+initialURL);
    }
    public void menLink(String menLink) throws IOException{
        click(searchMen);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchMen));
        String menURL = driver.getCurrentUrl();
        System.out.println(menURL);
        Reports.extentTest.log(Status.INFO,"Select URL "+menLink);
        try{
            Assert.assertTrue(menURL.contains("men"));
            Reports.extentTest.log(Status.PASS,"URL verified as "+menURL, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"URL is incorrect, actual URL is "+menURL,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
    public void womenLink(String womenLink) throws IOException{
        click(searchWomen);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchWomen));
        String womenURL = driver.getCurrentUrl();
        System.out.println(womenURL);
        Reports.extentTest.log(Status.INFO,"Select URL "+womenLink);
        try{
            Assert.assertTrue(womenURL.contains("women"));
            Reports.extentTest.log(Status.PASS," \n URL verified as "+womenURL, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"URL is incorrect, actual URL is "+womenURL,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
    public void kidLink(String kidLink) throws IOException{
        click(searchKids);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchKids));
        String kidURL = driver.getCurrentUrl();
        System.out.println(kidURL);
        Reports.extentTest.log(Status.INFO,"Select URL "+kidLink);
        try{
            Assert.assertTrue(kidURL.contains("kids"));
            Reports.extentTest.log(Status.PASS," \n URL verified as "+kidURL, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"URL is incorrect, actual URL is "+kidURL,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

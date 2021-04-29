import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;

public class FilterGender extends BasePage {
    private WebDriver driver;

    public FilterGender(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By search = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchJacket = By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[2]/a");
    private By searchCount = By.xpath("//*[@id=\"products\"]/div[2]/div/div[1]");
    private By searchMen = By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label");
    private By menFilter = By.xpath("//*[@id=\"breadcrumb-container\"]/div/div/div/span");
    private By menJackets = By.xpath("//*[@id=\"products\"]/div[3]/div/div[1]");
    private By closeMen = By.xpath("//*[@id=\"breadcrumb-container\"]/div/div/div/a/span");
    private By searchWomen = By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[2]/div/div/label");
    private By womenFilter = By.xpath("//*[@id=\"breadcrumb-container\"]/div/div/div/span");
    private By countJackets = By.xpath("//*[@id=\"products\"]/div[3]/div/div[1]");


    public void filterJacket(String jacketCount) throws IOException {
        click(search);
        click(searchJacket);
        String totalCount = driver.findElement(searchCount).getText();
        System.out.println(totalCount);
        Reports.extentTest.log(Status.INFO,"Total jacket "+totalCount, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }
    public void menJacket(String jacketCount) throws IOException{
        click(searchMen);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchMen));
        String men=driver.findElement(menFilter).getText();
        System.out.println(men);
        String countMen = driver.findElement(menJackets).getText();
        System.out.println(countMen);
        Reports.extentTest.log(Status.INFO,"Men jacket "+countMen, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        click(closeMen);
        }
        public void womenJacket(String jacketCount) throws IOException{
        click(searchWomen);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchWomen));
        String women=driver.findElement(womenFilter).getText();
        System.out.println(women);
        String countWomen = driver.findElement(countJackets).getText();
        System.out.println(countWomen);
        Reports.extentTest.log(Status.INFO,"Women jacket "+countWomen, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    public void verifyFilterJacket(String jacketCount) throws IOException {
        String beforeCount= driver.findElement(searchCount).getText();
        String afterCount = driver.findElement(countJackets).getText();
        try{
            Assert.assertFalse(beforeCount.equals(afterCount));
            Reports.extentTest.log(Status.PASS,"Filter applied successfully.After filter count is  "+afterCount, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Filter not success, actual count is "+afterCount,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

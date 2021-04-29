import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;

public class BrandPuma extends BasePage{
    private WebDriver driver;

    public BrandPuma(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By searchPuma = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchPumaClick = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/button/span");
    private By prdBrand = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[1]");

    public void prdPuma(String searchKey){
        driver.findElement(searchPuma).sendKeys(searchKey);
        click(searchPumaClick);
        String brandName = driver.findElement(prdBrand).getText();
        System.out.println(brandName);
        Reports.extentTest.log(Status.INFO,"Select brand "+searchKey);
    }

    public void verifyPrdPuma(String searchKey) throws IOException {
        String actualTitle= driver.getTitle();
        try{
            Assert.assertTrue(actualTitle.contains("Puma"));
            Reports.extentTest.log(Status.PASS,"Title verified as "+actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Title not matching, actual title is "+actualTitle,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}


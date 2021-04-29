import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;

public class SearchProduct extends BasePage{
    private WebDriver driver;

    public SearchProduct(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By search=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchJacket=By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[2]/a");
    private By prdText=By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[2]");

    public void prdJacket(String jacketName){
        click(search);
        click(searchJacket);
        String expectedText = driver.findElement(prdText).getText();
        Assert.assertTrue(expectedText.contains("Jacket"));
        System.out.println(expectedText);
        Reports.extentTest.log(Status.INFO,"Select product "+jacketName);
    }
    public void verifyPrdJacket(String jacketName) throws IOException {
        String actualTitle= driver.getTitle();

        try{
            Assert.assertTrue(actualTitle.contains("Jacket"));
            Reports.extentTest.log(Status.PASS,"Title verified as "+actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Title not matching, actual title is "+actualTitle,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}



import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class AddToBag extends BasePage {
    private WebDriver driver;

    public AddToBag (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By search=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchJacket=By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[2]/a");
    private By selectJacket=By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[1]/img");
    private By sizeJacket=By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[2]/div");
    private By addJacket=By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[9]/div[1]/div[1]/div");
    private By gotoBag=By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[9]/div[1]/div[1]/a/div/span[2]");
    private By myBag=By.xpath("//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[1]/div[1]/div[1]");

    public void buyJacket(String jacketName){
        click(search);
        click(searchJacket);
        click(selectJacket);
        for(String childWindow : driver.getWindowHandles()){
            driver.switchTo().window(childWindow);
        }
        click(sizeJacket);
        click(addJacket);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(gotoBag));
        click(gotoBag);
        String temp=driver.findElement(myBag).getText();
        System.out.println(temp);
        Reports.extentTest.log(Status.INFO,"Add to Cart "+jacketName);
    }
    public void verifyAddJacket(String jacketName) throws IOException {
        String actualText= driver.findElement(myBag).getText();
        try{
            Assert.assertTrue(actualText.contains("My Bag"));
            Reports.extentTest.log(Status.PASS,"Title verified as "+actualText, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Title not matching, actual title is "+actualText,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    @Override
    public void testCleanup() {
        this.testCleanup();
    }
}

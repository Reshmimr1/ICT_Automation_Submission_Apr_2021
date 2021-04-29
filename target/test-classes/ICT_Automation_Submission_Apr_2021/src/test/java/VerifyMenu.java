import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.io.IOException;

public class VerifyMenu extends BasePage {
    private WebDriver driver;

    public VerifyMenu(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    private By temp=By.xpath("//ul[contains(@class,'level-first')]");

    private By menuMen=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[1]/a");
    private By menuWomen=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[2]/a");
    private By menuKid=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[3]/a");
    private By menuHome=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[5]/a");

    public void menuItems(String menuContent) {
        String menText = driver.findElement(menuMen).getText();
        System.out.println("Page contains "+menText);
        String womenText = driver.findElement(menuWomen).getText();
        System.out.println("Page contains "+womenText);
        String kidText = driver.findElement(menuKid).getText();
        System.out.println("Page contains "+kidText);
        String homeText = driver.findElement(menuHome).getText();
        System.out.println("Page contains "+homeText);
        Reports.extentTest.log(Status.INFO, "Verifying " + menuContent);
    }

    public void verifyMenuItems(String menuContent) throws IOException {
        String actualMenu = driver.findElement(temp).getText();
        try{
            Assert.assertTrue(actualMenu.contains("MEN\nWOMEN\nKIDS\nINDIE\nHOME AND KITCHEN\nNEW ARRIVALS"));
            Reports.extentTest.log(Status.PASS,"Title verified as "+actualMenu, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Title not matching, actual title is "+actualMenu,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class MenMenu extends BasePage {
    private WebDriver driver;

    public MenMenu(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By searchMen = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[1]/a");
    private By searchCategory = By.xpath("//li[contains(@class,'inactive')]/a[1]");
    private By searchJeans = By.xpath("//*[@id=\"third\"]/div[2]/span[2]/a");
    private By searchShirt = By.xpath("//*[@id=\"third\"]/div[2]/span[3]/a");

    public void menCategory(String menu) throws IOException {
        Actions actions = new Actions(driver);
        WebElement Men = driver.findElement(searchMen);
        actions.moveToElement(Men).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(searchCategory));

        WebElement categories = driver.findElement(searchCategory);
        actions.moveToElement(categories).build().perform();

        WebElement jeans = driver.findElement(searchJeans);
        actions.moveToElement(jeans).build().perform();
        String toolTipText1 = jeans.getText();

        WebElement shirts = driver.findElement(searchShirt);
        actions.moveToElement(shirts).build().perform();
        String toolTipText2 = shirts.getText();
        Reports.extentTest.log(Status.INFO, "Verifying " + menu);
        try {
            Assert.assertEquals(toolTipText1, "Jeans");
            Assert.assertEquals(toolTipText2, "Shirts");
            Reports.extentTest.log(Status.PASS, "Text1 verified as " + toolTipText1 + " ,Text2 verified as " + toolTipText2, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL, "Text1 not matching, actual text1 is " + toolTipText1 + " ,Text2 is " + toolTipText2, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}



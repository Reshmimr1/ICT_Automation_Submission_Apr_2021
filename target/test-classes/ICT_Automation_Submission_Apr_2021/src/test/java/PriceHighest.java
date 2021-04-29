import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PriceHighest extends BasePage {
    private WebDriver driver;

    public PriceHighest(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By search=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchShoe=By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[1]/a");
    private By relevant = By.xpath("//*[@id=\"products\"]/div[2]/div/div[3]/div/select");
    private By listPrice = By.xpath("//span[contains(@class,'price ')]");
    private By firstPrdPrice = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[3]/span");
    private By firstPrdName = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[2]");

    public void shoeHighPrice(String shoeName){
        click(search);
        click(searchShoe);
        Select dropdown = new Select(driver.findElement(relevant));
        dropdown.selectByVisibleText("Price (highest first)");

        List<WebElement> prdLst = new LinkedList<>(driver.findElements(listPrice));
        LinkedList<String> prdItem = new LinkedList<>();
        for(int j=0;j<prdLst.size();j++){
            prdItem.add(prdLst.get(j).getText());
        }
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        boolean isSorted = Ordering.natural().isOrdered(prdItem);
        System.out.println(isSorted);
        System.out.println(prdItem.getFirst());
        Reports.extentTest.log(Status.INFO,"Select shoe "+shoeName);
    }
    public void verifyShoe(String shoeName) throws IOException {
        String prdName = driver.findElement(firstPrdName).getText();
        System.out.println(prdName);
        String prdPrice = driver.findElement(firstPrdPrice).getText();
        System.out.println(prdPrice);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 150)",prdPrice);
        try{
            Assert.assertTrue(prdPrice.contains("26,249"));
            Reports.extentTest.log(Status.PASS,"Highest Price verified as "+prdPrice, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Price not matching, actual price is "+prdPrice,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
}



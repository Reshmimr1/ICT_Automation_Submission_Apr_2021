import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PriceLowest extends BasePage {
    private WebDriver driver;


    public PriceLowest(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private By search=By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchShoe=By.xpath("//*[@id=\"react-autowhatever-1\"]/div/ul/li[1]/a");
    private By relevant = By.xpath("//*[@id=\"products\"]/div[2]/div/div[3]/div/select");
    private By listPrice = By.xpath("//span[contains(@class,'price ')]");
    private By firstPrdPrice = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[3]/span");
    private By firstPrdName = By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[1]/a/div/div[2]/div[2]");
    public void prdShoe(String shoeName) {
        click(search);
        click(searchShoe);
        Select dropdown = new Select(driver.findElement(relevant));
        dropdown.selectByVisibleText("Price (lowest first)");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> li = new LinkedList<>(driver.findElements(listPrice));
        LinkedList<String> pn = new LinkedList<String>();
        for (int i = 0; i < li.size(); i++) {
            pn.add((li.get(i).getText()));
        }


        System.out.println(pn.get(0));
        boolean isSorted = Ordering.natural().isOrdered(pn);
        System.out.println(isSorted);
        Reports.extentTest.log(Status.INFO,"Lowest Price "+shoeName);
    }

    public void verifyPrdShoe(String shoeName) throws IOException {
        String prdName = driver.findElement(firstPrdName).getText();
        System.out.println(prdName);
        String prdPrice = driver.findElement(firstPrdPrice).getText();
        System.out.println(prdPrice);
        try{
            Assert.assertTrue(prdPrice.contains("104"));
            Reports.extentTest.log(Status.PASS,"Lowest Price verified as "+prdPrice, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Price not matching, actual price is "+prdPrice,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
}

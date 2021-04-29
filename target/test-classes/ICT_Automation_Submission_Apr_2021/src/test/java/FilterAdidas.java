import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FilterAdidas extends BasePage {
    private WebDriver driver;

    public FilterAdidas(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    private By searchAdidas = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/div/input");
    private By searchAdidasClick = By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/button/span");
    private By searchPrice = By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div/span[2]");
    private By searchMin = By.xpath("//*[@id=\"minPrice\"]");
    private By searchMax = By.xpath("//*[@id=\"maxPrice\"]");
    private By searchFilter = By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div[2]/form/div/div/div[2]/button");
    private By lstPrice = By.xpath("//span[@class='price  ']");
    private By verifyFilter = By.xpath("//*[@id=\"breadcrumb-container\"]/div/div/div/span");

    public void prdAdidas(String searchKey) {
        driver.navigate().refresh();
        driver.findElement(searchAdidas).sendKeys(searchKey);
        click(searchAdidasClick);
        click(searchPrice);
        driver.findElement(searchMin).sendKeys("100");
        driver.findElement(searchMax).sendKeys("500");
        click(searchFilter);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter));

        List<WebElement> prdLst =driver.findElements(lstPrice);
            try {
                for (WebElement e : prdLst) {
                    System.out.println(e.getText());
                }
            }catch (Exception e){
                System.out.println(e);
            }

//        List<WebElement> prdLst =new LinkedList<>(driver.findElements(lstPrice));
//        LinkedList<String> prdItems = new LinkedList<String>();
//        for(int j=0;j<prdLst.size();j++){
//            prdItems.add(prdLst.get(j).getText());
//        }
//
//        System.out.println(prdItems.getFirst());
//        System.out.println(prdItems.getLast());
        Reports.extentTest.log(Status.INFO,"Select Brand "+searchKey);
    }

    public void verifyAdidas(String brandName) throws IOException {
        String actualText= driver.findElement(verifyFilter).getText();
        try{
            Assert.assertTrue(actualText.contains("Rs.100 - Rs.500"));
            System.out.println(actualText);
            Reports.extentTest.log(Status.PASS,"Text verified as "+actualText, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

        catch (Exception e){
            System.out.println(e);
            Reports.extentTest.log(Status.FAIL,"Text not matching, actual text is "+actualText,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
}

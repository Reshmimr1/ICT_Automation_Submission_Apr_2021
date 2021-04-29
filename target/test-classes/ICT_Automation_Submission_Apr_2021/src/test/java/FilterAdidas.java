import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    private By listPrice = By.xpath("//span[contains(@class,'price ')]");
    private By verifyFilter = By.xpath("//*[@id=\"breadcrumb-container\"]/div/div/div/span");
    public void prdAdidas(String searchKey) {
        driver.findElement(searchAdidas).sendKeys(searchKey);
        click(searchAdidasClick);
        click(searchPrice);
        driver.findElement(searchMin).sendKeys("100");
        driver.findElement(searchMax).sendKeys("500");
        click(searchFilter);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchFilter));
        List<WebElement> li = new LinkedList<>(driver.findElements(listPrice));
        LinkedList<String> pn = new LinkedList<String>();
        for (int i = 0; i < li.size(); i++) {
            pn.add((li.get(i).getText()));
        }
        Collections.sort(pn);
        System.out.println(pn.getFirst());
        System.out.println(pn.getLast());
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

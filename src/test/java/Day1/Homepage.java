package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.*;

public class Homepage {
    WebDriver driver;
    private By search = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    private By searchclk = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    private By brand = By.xpath("//*[@id=\"p_89/Apple\"]/span/a/div/label/i");

    public Homepage(WebDriver driver){
        this.driver = driver;
    }


    public void search(){


        driver.findElement(search).sendKeys("Apple iPhone 12");
        driver.findElement(searchclk).click();
        driver.findElement(brand).click();
        Select lprice = new Select(driver.findElement(By.xpath("//*[@id=\"s-result-sort-select\"]")));
        lprice.selectByVisibleText("Price: Low to High");
        driver.findElement(By.xpath("//*[contains(@alt,'iPhone 12')]")).click();
        driver.findElement(By.id("add-to-cart-button")).click();

    }

    public void amclose(){
        driver.quit();


    }

}















//        List<WebElement> price = driver.findElements(By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[4]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/a/span[1]/span[2]/span[2]"));
//        List<WebElement> product = driver.findElements(By.xpath("//*[@id=\"search\"]/div[1]/div/div[1]/div/span[3]/div[2]/div[2]/div/span/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
//        String product_name;
//        String product_price;
//        int intprodprice;
//        HashMap<Integer, String> prdhash = new HashMap<Integer,String>();
//        for(int i=0;i<product.size();i++) {
//            product_name = product.get(i).getText();
//            product_price = price.get(i).getText();
//            product_price = product_price.replaceAll("[^0-9]", "");
//            intprodprice = Integer.parseInt(product_price);
//            prdhash.put(intprodprice,product_name);
//        }
//        Set<Integer> allkeys = prdhash.keySet();
//        ArrayList<Integer> prd_price = new ArrayList<Integer>(allkeys);
//        Collections.sort(prd_price);
//        int low_price = prd_price.get(0);
//        System.out.println("Low Product Price is: " + low_price + " Product name is: " + prdhash.get(low_price));
//
//    }


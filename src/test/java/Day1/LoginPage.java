package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {


    WebDriver driver;
    private By username = By.id("ap_email");
    private By cont = By.xpath("//*[@id=\"continue\"]");
    private By password = By.id("ap_password");
    private By signin =  By.xpath("//*[@id=\"signInSubmit\"]");

    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public void setUsername(String name ) {

        driver.findElement(username).sendKeys(name);
    }
    public void setCont(){
        driver.findElement(cont).click();
    }
    public void setPassword(String pwd){
        driver.findElement(password).sendKeys(pwd);
    }
    public void signin(){
        driver.findElement(signin).click();
    }
    public  void loginto(String name,String pwd){
        this.setUsername(name);
        this.setCont();
        this.setPassword(pwd);
        this.signin();
    }

//    By username = By.id("ap_email");
//        driver.findElement(username).sendKeys("reshmi.mr1@gmail.com");
////    By cont = By.xpath("//*[@id=\"continue\"]");
//            driver.findElement(cont).click();
////    By password = By.id("ap_password");
//            driver.findElement(password).sendKeys("Home123@");
////    By signin = By.xpath("//*[@id=\"signInSubmit\"]");
//            driver.findElement(signin).click();
}

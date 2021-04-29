import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser{
    public static WebDriver openBrowser(String browser) throws Exception{
        if(browser.equalsIgnoreCase("Chrome")){
            String baseDirectory=System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver",baseDirectory+"/src/main/resources/chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriver driver=new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.navigate().to("https://www.ajio.com/");
            return driver;
//        }
//        else if(browser.equalsIgnoreCase("Firefox")){
//            String baseDirectory=System.getProperty("user.dir");
//            System.setProperty("webdriver.gecko.driver",baseDirectory+"/src/main/resources/geckodriver.exe");
//            FirefoxOptions options=new FirefoxOptions();
//            options.addArguments("--disable-notifications");
//            WebDriver driver=new FirefoxDriver(options);
//            driver.manage().window().maximize();
//            driver.navigate().to("https://www.ajio.com/");
//            return driver;
        }
        else {
                throw new Exception("Invalid Browser");
            }
    }
    public static void closeBrowser(WebDriver driver){
        driver.close();
    }

}
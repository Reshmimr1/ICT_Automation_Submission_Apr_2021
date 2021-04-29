package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class amazon extends BaseClass{


        WebDriver driver = openBrowser("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcustomer-preferences%2Fselect-language%2Fref%3Dnav_signin%3FpreferencesReturnUrl%3D%252Fyour-account&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");



    @BeforeTest
    public void amUsername() {

        LoginPage obj = new LoginPage(driver);
        obj.loginto("reshmi.mr1@gmail.com","Rele012@");
        }

        @Test
        public void search(){

            Homepage obj2=new Homepage(driver);
            obj2.search();


        }

        @AfterTest
        public void amclose(){

            Homepage obj2=new Homepage(driver);

            obj2.amclose();

        }

    }

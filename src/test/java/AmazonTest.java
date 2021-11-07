import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AmazonTest {

    static AppiumDriver<MobileElement> driver;
    SkipSignUpPage skipSignUpPageElement;
    private static Logger logger = Logger.getLogger(AmazonTest.class);
    HomePage homePage;
    private String searchItem = "Oneplus mobile phones";
     SoftAssert softAssert;


    @Test(description = "verify App launch")
    public void AmazonAppLauncher() throws MalformedURLException, InterruptedException {

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9.0");
        cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        cap.setCapability("appActivity", "com.amazon.windowshop.home.HomeLauncherActivity");

        driver = new AppiumDriver<MobileElement>(url, cap);
        SessionId sessionId = driver.getSessionId();
        System.out.println(sessionId);

        Thread.sleep(10000);

        skipSignUpPageElement = new SkipSignUpPage();
        softAssert = new SoftAssert();
        logger.info("calling SkipSignUp Method");
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

    }

    @Test(priority = 0,description = "Verify the text shown on landing page", dependsOnMethods = "AmazonAppLauncher")
    public void LandingPageVerification() {
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        System.out.println("Inside landingPageVerification Test method");
        skipSignUpPageElement.verifyElementsDisplayed(driver);
    }

    @Test(priority = 1,description = "Test Skip Sign Up Flow")
    public void SkipSignUPTest() {

        System.out.println("Inside skipSignUPTest method");
        skipSignUpPageElement.clickSkipSignUp(driver);
        logger.info("test user");
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }


    @Test(dependsOnMethods ="SkipSignUPTest")
    public void searchItem() throws InterruptedException {

        System.out.println("Inside searchItem method");
        homePage = new HomePage();
        homePage.verifyHomePage(driver);
        Thread.sleep(1000);
        System.out.println("search the item");
        homePage.searchItem(driver,searchItem);
        homePage.readSearchResults(driver);
        driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);

    }


//    @Test(dependsOnMethods ="searchItem" )
//    public void viewDetails(){
//        System.out.println("inside view details page");
//        homePage.viewDetailsPage(driver);
//       driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
//    }

//    @AfterClass(description = "kill the session")
//    public void tearDown() {
//        driver.quit();
//    }

}

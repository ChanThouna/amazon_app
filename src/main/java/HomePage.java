import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage {

    AppiumDriver localAppiumDriver;
    private String searchPlaceholder = "What are you looking for?";
    static WebElement firstResult = null;
    static String price = null;


    @FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo']")
    private WebElement amazonLogo;

    @FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon']")
    private WebElement hamburger;

    @FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']")
    private MobileElement searchTxtField;

    @FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image']")
    private WebElement cart;

    @FindBy(xpath = "//*[contains(@text, 'RESULTS')]")
    private WebElement resultText;

    @FindBy(xpath = "//*[contains(@text, '$')]")
    private WebElement searchResults;

    @FindBy(xpath = "//*[contains(@text, '$')]/preceding-sibling::*[1]")
    private WebElement searchResultNames;
    SoftAssert softAssert = new SoftAssert();

    public void verifyHomePage(AppiumDriver driver) {
        localAppiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(localAppiumDriver, Duration.ofSeconds(30)), this);
        System.out.println("Verify if the Home page is displayed");
        softAssert.assertTrue(amazonLogo.isDisplayed(), "Amazon Logo missing");
        softAssert.assertTrue(hamburger.isDisplayed(), "Hamburger is displayed");
        softAssert.assertTrue(searchTxtField.isDisplayed(), "SearchTxtField is displayed");
        softAssert.assertTrue(cart.isDisplayed(), "Cart is displayed");
        softAssert.assertAll();
    }

    public void searchItem(AppiumDriver driver, String item) throws InterruptedException {
        localAppiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(localAppiumDriver, Duration.ofSeconds(15)), this);
        localAppiumDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);

        WebElement element;
        boolean isElementPresent;

        element = localAppiumDriver.findElement(By.xpath("//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        isElementPresent = searchTxtField.isDisplayed();

        if (isElementPresent == true) {
     //       localAppiumDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
            searchTxtField.click();
            searchTxtField.sendKeys(item);
            localAppiumDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
            System.out.println("entered the search item detail in the search text field");

        } else
            System.out.println("Search text field not found");
    }


    public void readSearchResults(AppiumDriver driver) throws InterruptedException {
        localAppiumDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(localAppiumDriver, Duration.ofSeconds(15)), this);
    //    localAppiumDriver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
        List<WebElement> search = localAppiumDriver.findElements(By.xpath("//*[contains(@text, '$')]"));
//        Assert.assertTrue(search.size() > 0, "Search result not found");
        for (WebElement result : search) {
            System.out.println(result.getAttribute("text"));
        }

        firstResult = localAppiumDriver.findElement(By.xpath("//android.view.View[@resource-id='search']/android.view.View[4]/android.view.View[2]"));
        String[] text = firstResult.getAttribute("text").split("\\s+");
        price = text[0];
        firstResult.click();
        System.out.println(" The price shown in Home page is "+price);
    }


//    public void viewDetailsPage(AppiumDriver driver) {
//        localAppiumDriver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(localAppiumDriver, Duration.ofSeconds(15)), this);
//        System.out.println(" the price shown in search result page " + price);
//        MobileActions actions = new MobileActions();
//        actions.scrollUp(localAppiumDriver);
//        String[] price2 = null;
//        for (int i = 1; i <= 3; i++) {
//            WebElement element =  localAppiumDriver.findElement(By.xpath("//*[@resource-id='newPitchPriceWrapper_feature_div']/android.view.View[" + 1 + "]"));
//            price2[i - 1] = element.getAttribute("text");
//       }
//        String checkoutPagePrice = price2[0] + price2[1] + "." + price2[2];
//        Assert.assertTrue(price.equals(checkoutPagePrice), "Difference in prices");
//    }

}

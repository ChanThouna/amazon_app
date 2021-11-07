import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class SkipSignUpPage {

    private static String skipSignUpText = "Skip sign in";
    private static String viewWishListText = "View your wish list";
    private static String alreadyACustomerText = "Already a customer? Sign in";
    private static String trackYourPurchasesText ="Track your purchases";
    private static String signInToAccountText ="Sign in to your account";
    AppiumDriver localAppiumDriver;
    @FindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
    private WebElement skipSignUp;
    @FindBy(id = "com.amazon.mShop.android.shopping:id/view_your_wish_list")
    private WebElement viewWishList;
    @FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
    private WebElement alreadyACustomer;
    @FindBy(id="com.amazon.mShop.android.shopping:id/track_your_packages")
    private WebElement trackYourPackages;
    @FindBy(id ="com.amazon.mShop.android.shopping:id/signin_to_yourAccount")
    private WebElement signInToAccount;
    @FindBy(xpath ="//*[@resource-id='com.amazon.mShop.android.shopping:id/sso_splash_logo']")
    private WebElement amazonLogo;
   SoftAssert softAssert = new SoftAssert();

    public void clickSkipSignUp(AppiumDriver driver) {
        localAppiumDriver = driver;
        PageFactory.initElements(localAppiumDriver, this);
        String text = skipSignUp.getText();
        softAssert.assertTrue(text.equals(skipSignUpText), "Difference in Expected text for Skip Sign up");
        skipSignUp.click();
        softAssert.assertAll();
    }

    public void verifyElementsDisplayed(AppiumDriver driver) {
        localAppiumDriver = driver;
        PageFactory.initElements(localAppiumDriver, this);
        String wishlist = viewWishList.getText();
        softAssert.assertTrue(wishlist.equals(viewWishListText), "Difference in Expected text for viewWishListText");
        String existingCustomer = alreadyACustomer.getText();
        softAssert.assertTrue(existingCustomer.equals(alreadyACustomerText), "Difference in expected text for existingCustomer");
        String trackPackages = trackYourPackages.getText();
        softAssert.assertTrue(trackPackages.equals(trackYourPurchasesText), "Difference in expected text for trackYourPackagesText");
        String signInToYourAccount = signInToAccount.getText();
        softAssert.assertTrue(signInToYourAccount.equals(signInToAccountText), "Difference in expected text for signInToAccount ");
        softAssert.assertTrue(amazonLogo.isDisplayed(),"Amazon Logo missing");
        softAssert.assertAll();
    }



}

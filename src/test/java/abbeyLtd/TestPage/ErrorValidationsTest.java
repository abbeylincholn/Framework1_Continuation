package abbeyLtd.TestPage;

import abbeyLtd.PageObjects.CartPage;
import abbeyLtd.PageObjects.ProductCatalogue;
import abbeyLtd.TestComponent.BaseTest;
import abbeyLtd.TestComponent.Retry; // Keep this!
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    /**
     * Main Java we will use to store all reusable utilities, page object files later in this section.
     * Implements test strategy for framework on how test are divided based on modules. e.g negative and positive scenarios, base on page objects
     * Testng.xml is a runner file
     */
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws InterruptedException, IOException {

        landingPage.loginApplication("abbeylincon@gmail.comm", "Abbe0!");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("abbeylincoln@gmail.com", "Abbey100!");
        List<WebElement> productsList = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.gotoCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}

package abbeyLtd.stepDefinitions;

import abbeyLtd.PageObjects.*;
import abbeyLtd.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public OrderConfirmationPage confirmationPage;
    public CheckoutPage checkoutPage;

    @Given("I am on Ecommance website")
    public void I_am_on_Ecommance_website() throws IOException {
       landingPage = launchApp();
    }

    @Given("^logged in with username (.+) and password (.+)$")  // regular expression regex
    public void logged_in_with_username_and_password(String username, String password) throws IOException {
         productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String productName) throws IOException, InterruptedException {
        List<WebElement> productsList = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^I proceed to checkout (.+) and submit the order$")
    public void I_proceed_to_checkout_and_submit_the_order(String productName) throws IOException, InterruptedException {
        CartPage cartPage = productCatalogue.gotoCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("Nigeria");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message should be displayed on ConfirmationPage") // string is a placeholder (THANKYOU FOR THE ORDER." message should be displayed on ConfirmationPage)
    public void message_should_be_displayed_on_ConfirmationPage(String message) {
        String confirmMessage = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
        driver.close();
    }
    @Then ("{string} message is displayed")
    public void loginErrorValidation(String message) throws InterruptedException, IOException {
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        driver.close();
    }


}



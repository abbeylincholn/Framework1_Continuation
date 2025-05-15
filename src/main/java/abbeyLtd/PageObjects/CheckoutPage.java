package abbeyLtd.PageObjects;

import abbeyLtd.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    // Encapsulation is it mainly hiding the implementation details of your class from another class.
    // keeping fields private and actions public is a form of encapsulationand.

    WebDriver driver;



    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country;

    @FindBy(css = ".ta-item:last-child")
    private WebElement selectCountry;

    @FindBy(css = ".btnn.action__submit")
    private WebElement submitBtn;

    private By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public OrderConfirmationPage submitOrder() {
        submitBtn.click();
        return new OrderConfirmationPage(driver);
    }
}

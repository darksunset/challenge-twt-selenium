package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;

public class CreditCardPayment extends AbstractComponent {
    @FindBy(xpath = "//div[@class='stripe-form']//button[./span[.='Test success']]")
    private WebElement testSuccessButton;
    @FindBy(xpath = "//div[@class='stripe-form']//button[./span[.='Test failure']]")
    private WebElement testFailureButton;
    @FindBy(xpath = "//div[@class='stripe-form']//div[contains(text(),'Test card number')]")
    private WebElement testCardNumber;
    @FindBy(xpath = "//div[@class='stripe-form']//div[contains(text(),'Test card expiry')]")
    private WebElement testExpNumber;
    @FindBy(xpath = "//div[@class='stripe-form']//div[contains(text(),'Test card CVC')]")
    private WebElement testCvc;
    @FindBy( css = "iframe[name*='privateStripeFrame']")
    private WebElement creditCardIframe;
    @FindBy(css = "input[data-elements-stable-field-name='cardNumber']")
    private WebElement creditCardInput;
    @FindBy(css = "input[data-elements-stable-field-name='cardExpiry']")
    private WebElement expirationInput;
    @FindBy(css = "input[data-elements-stable-field-name='cardCvc']")
    private WebElement cvcInput;
    @FindBy(css = "#checkoutPaymentForm .form-submit-button button")
    private WebElement placeOrderButton;
    public CreditCardPayment(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until(ExpectedConditions.elementToBeClickable(this.testSuccessButton)).isDisplayed();
    }

    public void fillCreditCardWithTestData(){

        String[] ccNumberLabel = this.wait.until(ExpectedConditions.visibilityOf(this.testCardNumber)).getText().split(": ");
        String cardNumber = ccNumberLabel[1].replace(" ", "");

        int nextYear = (LocalDate.now().getYear() % 100) + 1;
        String[] ccExpirationLabel = this.wait.until(ExpectedConditions.visibilityOf(this.testExpNumber)).getText().split(": ");
        String expDate = ccExpirationLabel[1].substring(0, ccExpirationLabel[1].length() - 2).concat(Integer.toString(nextYear));

        String[] cvcLabel = this.wait.until(ExpectedConditions.visibilityOf(this.testCvc)).getText().split(": ");
        String cvcNumber = cvcLabel[1];

        this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(creditCardIframe));
        this.wait.until(ExpectedConditions.visibilityOf(creditCardInput)).sendKeys(cardNumber);
        this.wait.until(ExpectedConditions.visibilityOf(expirationInput)).sendKeys(expDate);
        this.wait.until(ExpectedConditions.visibilityOf(cvcInput)).sendKeys(cvcNumber);
    }

    public void clickTestSuccess(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.testSuccessButton)).click();
    }

    public  void clickTestFailure() {
        this.wait.until(ExpectedConditions.elementToBeClickable((this.testFailureButton))).click();
    }
}

package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentMethod extends AbstractComponent {
    @FindBy(css = "#checkoutPaymentForm .form-submit-button button")
    private WebElement placeOrderButton;
    @FindBy(xpath = "//div[contains(@class,'payment-method-list')][.//img[@alt='Stripe']]//a")
    private WebElement creditCardOption;
    @FindBy(xpath = "//div[contains(@class,'payment-method-list')]//a")
    private List<WebElement> methodList;

    public PaymentMethod(final WebDriver driver) {
        super(driver);
    }

    public void clickCreditCardOption(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.creditCardOption)).click();
    }

    public void clickPlaceOrder(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.placeOrderButton)).click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.methodList.size() == 3);
    }
}

package org.evershop.pages.order;

import org.evershop.pages.common.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
    private WebDriver driver;
    private TopBar topBar;
    private ShoppingCartList shoppingCartList;
    private ShipmentAddress shipmentAddress;
    private PaymentMethod paymentMethod;
    private CreditCardPayment creditCardPayment;
    private CheckoutSuccess checkoutSuccess;
    public ShoppingCartPage(final WebDriver driver){
        this.driver = driver;
        this.topBar = PageFactory.initElements(driver, TopBar.class);
        this.shoppingCartList = PageFactory.initElements(driver, ShoppingCartList.class);
        this.shipmentAddress = PageFactory.initElements(driver, ShipmentAddress.class);
        this.paymentMethod = PageFactory.initElements(driver, PaymentMethod.class);
        this.creditCardPayment = PageFactory.initElements(driver, CreditCardPayment.class);
        this.checkoutSuccess = PageFactory.initElements(driver, CheckoutSuccess.class);
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public ShoppingCartList getShoppingCartList() {
        return shoppingCartList;
    }

    public ShipmentAddress getShipmentAddress() {
        return shipmentAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public CreditCardPayment getCreditCardPayment() {
        return creditCardPayment;
    }

    public CheckoutSuccess getCheckoutSuccess() {
        return checkoutSuccess;
    }

    public void backToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }
}

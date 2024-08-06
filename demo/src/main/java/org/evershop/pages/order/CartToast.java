package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartToast extends AbstractComponent {
    @FindBy(css = ".toast-mini-cart .item-info")
    private WebElement itemInfo;
    @FindBy(css = ".toast-mini-cart a[href*='cart']")
    private WebElement viewCartButton;
    @FindBy(css = ".toast-mini-cart a[class*='continue']")
    private WebElement continueShoppingLink;
    @FindBy(css = ".toast-mini-cart .close a")
    private WebElement closeIcon;

    public CartToast(final WebDriver driver) {
        super(driver);
    }

    public void clickViewCart(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.viewCartButton)).click();
    }

    public void clickContinueShopping(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.continueShoppingLink)).click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until(ExpectedConditions.visibilityOf(this.continueShoppingLink)).isDisplayed();
    }
}

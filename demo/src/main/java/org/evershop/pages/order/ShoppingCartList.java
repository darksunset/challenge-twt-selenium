package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShoppingCartList extends AbstractComponent {
    @FindBy(css = "a[href*='checkout']")
    private WebElement checkoutButton;
    @FindBy(css = ".items-table tbody> tr")
    private List<WebElement> productRow;
    public ShoppingCartList(final WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton(){
        this.checkoutButton.click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> !this.productRow.isEmpty()) && this.wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).isDisplayed();
    }
}

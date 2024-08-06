package org.evershop.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopBar extends AbstractComponent{
    @FindBy(css=".header [class*='mini-cart-wrapper'] + div a")
    private WebElement userIcon;
    @FindBy(css = ".header .logo")
    private WebElement logoIcon;
    @FindBy(css = ".header .mini-cart-icon")
    private WebElement cartIcon;

    public TopBar(final WebDriver driver) {
        super(driver);
    }

    public void clickUserIcon(){
        this.wait.until(ExpectedConditions.elementToBeClickable(userIcon)).click();
    }
    public void clickLogoIcon(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.logoIcon)).click();
    }

    public void clickCartIcon(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.cartIcon)).click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.userIcon.isDisplayed());
    }
}

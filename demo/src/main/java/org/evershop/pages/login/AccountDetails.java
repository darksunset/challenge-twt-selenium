package org.evershop.pages.login;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountDetails extends AbstractComponent {
    @FindBy(css = ".account-details-name div:nth-child(2)")
    private WebElement fullNameLabel;
    @FindBy(css = ".account-details-email div:nth-child(2)")
    private WebElement emailLabel;
    @FindBy(xpath = "//a[.='Logout']")
    private WebElement logoutLink;
    public AccountDetails(final WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.logoutLink)).click();
    }

    public String getFullName() {
        return this.wait.until(ExpectedConditions.visibilityOf(this.fullNameLabel)).getText();
    }

    public String getEmail(){
        return this.wait.until(ExpectedConditions.visibilityOf(this.emailLabel)).getText();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> !this.fullNameLabel.getText().isEmpty());
    }
}

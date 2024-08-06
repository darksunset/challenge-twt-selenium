package org.evershop.pages.login;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends AbstractComponent {
    @FindBy(css="#loginForm input[name='email']")
    private WebElement emailInput;
    @FindBy(css = "#loginForm input[name='password']")
    private WebElement passwordInput;
    @FindBy(css = "#loginForm button[type='submit']")
    private WebElement signInButton;
    @FindBy(css = "a[href*='register']")
    private WebElement createAccountLink;
    public Login(final WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.signInButton.isEnabled());
    }

    public void login(String username, String password){
        this.emailInput.sendKeys(username);
        this.passwordInput.sendKeys((password));
        this.signInButton.click();
    }

    public void clickCreateAnAccount(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.createAccountLink)).click();
    }
}

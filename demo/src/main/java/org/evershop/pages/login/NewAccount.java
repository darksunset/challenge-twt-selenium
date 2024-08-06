package org.evershop.pages.login;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewAccount extends AbstractComponent {
    @FindBy(css = "#registerForm [name='full_name']")
    private WebElement fullNameInput;
    @FindBy(css = "#registerForm [name='email']")
    private WebElement emailInput;
    @FindBy(css = "#registerForm [name='password']")
    private WebElement passwordInput;
    @FindBy(css = "#registerForm [type='submit']")
    private WebElement signUpButton;

    public NewAccount(final WebDriver driver) {
        super(driver);
    }

    public void fillForm(String fullName, String email, String password){
        typeFullName(fullName);
        typeEmail(email);
        typePassword(password);
    }

    public void typeFullName(String fullName){
        this.wait.until(ExpectedConditions.visibilityOf(this.fullNameInput)).clear();
        this.fullNameInput.sendKeys(fullName);
    }

    public void typeEmail(String email){
        this.wait.until(ExpectedConditions.visibilityOf(this.emailInput)).clear();
        this.emailInput.sendKeys(email);
    }

    public void typePassword(String password) {
        this.wait.until(ExpectedConditions.visibilityOf(this.passwordInput)).clear();
        this.passwordInput.sendKeys(password);
    }

    public void clickSignUp(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.signUpButton)).click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until(ExpectedConditions.elementToBeClickable(this.signUpButton)).isDisplayed();
    }
}

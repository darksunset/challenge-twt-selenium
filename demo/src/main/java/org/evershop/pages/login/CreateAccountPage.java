package org.evershop.pages.login;

import org.evershop.pages.common.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
    private WebDriver driver;
    private TopBar topBar;
    private NewAccount newAccount;

    public CreateAccountPage(final WebDriver driver){
        this.driver = driver;
        this.topBar = PageFactory.initElements(driver, TopBar.class);
        this.newAccount = PageFactory.initElements(driver, NewAccount.class);
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public NewAccount getNewAccount() {
        return newAccount;
    }
}

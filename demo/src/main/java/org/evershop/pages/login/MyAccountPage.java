package org.evershop.pages.login;

import org.evershop.pages.common.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    private WebDriver driver;
    private TopBar topBar;
    private AccountDetails accountDetails;

    public MyAccountPage(final WebDriver driver){
        this.driver = driver;
        this.topBar = PageFactory.initElements(driver, TopBar.class);
        this.accountDetails = PageFactory.initElements(driver, AccountDetails.class);
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }
}

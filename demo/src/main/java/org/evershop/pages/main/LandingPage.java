package org.evershop.pages.main;

import org.evershop.pages.common.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private WebDriver driver;
    private TopBar topBar;
    private HomeBanner homeBanner;
    public LandingPage(final WebDriver driver){
        this.driver = driver;
        this.topBar = PageFactory.initElements(driver, TopBar.class);
        this.homeBanner = PageFactory.initElements(driver, HomeBanner.class);
    }

    public void goTo(){
        this.driver.get("https://demo.evershop.io/");
    }

    public HomeBanner getHomeBanner() {
        return homeBanner;
    }

    public TopBar getTopBar() {
        return topBar;
    }

}

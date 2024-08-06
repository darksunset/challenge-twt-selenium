package org.evershop.pages.main;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeBanner extends AbstractComponent {
    @FindBy(css=".content a[href='/kids']")
    private WebElement shopKidsButton;
    @FindBy(css=".content a[href='/women']")
    private WebElement shopWomenButton;
    @FindBy(css=".content a[href='/men']")
    private WebElement shopMenButton;

    public HomeBanner(final WebDriver driver) {
        super(driver);
    }

    public void clickShopKids(){
        this.wait.until(ExpectedConditions.elementToBeClickable(shopKidsButton)).click();
    }
    public void clickShopWomen(){
        this.wait.until(ExpectedConditions.elementToBeClickable(shopWomenButton)).click();
    }
    public void clickShopMen(){
        this.wait.until(ExpectedConditions.elementToBeClickable(shopMenButton)).click();
    }

    public void clickShopByCategory(String categoryName){
        switch(categoryName.toLowerCase()){
            case "kid": case "kids": clickShopKids(); break;
            case "women": clickShopWomen();break;
            case "men": clickShopMen(); break;
        }
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.shopMenButton.isDisplayed());
    }
}

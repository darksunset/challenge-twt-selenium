package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ShipmentAddress extends AbstractComponent {
    @FindBy(css = "input[name*='full_name']")
    private WebElement fullNameInput;
    @FindBy(css = "input[name*='telephone']")
    private WebElement phoneInput;
    @FindBy(css = "input[name*='address_1']")
    private WebElement addressInput;
    @FindBy(css = "input[name*='city']")
    private WebElement cityInput;
    @FindBy(css = "select[name*='country']")
    private WebElement countrySelect;
    @FindBy(css = "select[name*='province']")
    private WebElement provinceSelect;
    @FindBy(css = "input[name*='postcode']")
    private WebElement postCodeInput;
    @FindBy(css = ".shipping-methods label")
    private List<WebElement> shipmentMethodList;
    @FindBy(css = ".checkout-payment  button[type='submit']")
    private WebElement continuePaymentButton;
    public ShipmentAddress(final WebDriver driver) {
        super(driver);
    }

    public void typeFullName(String fullName) {
        this.wait.until(ExpectedConditions.visibilityOf(this.fullNameInput)).clear();
        this.fullNameInput.sendKeys(fullName);
    }

    public void typeTelephone(String phoneNumber){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.phoneInput)).clear();
        this.phoneInput.sendKeys(phoneNumber);
    }

    public void typeAddress(String address1){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.addressInput)).clear();
        this.addressInput.sendKeys(address1);
    }

    public void typeCity(String city){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.cityInput)).clear();
        this.cityInput.sendKeys(city);
    }

    public void selectCountry(String country){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.countrySelect));
        Select select = new Select(this.countrySelect);
        select.selectByVisibleText(country);
    }

    public void selectProvince(String province){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.provinceSelect));
        Select select = new Select(this.provinceSelect);
        select.selectByVisibleText(province);
    }

    public void typePostCode(String postCode){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.postCodeInput)).clear();
        this.postCodeInput.sendKeys(postCode);
    }

    public void selectShipmentMethod(String shipmentMethod){
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.shipmentMethodList));
        List<WebElement> element = this.shipmentMethodList.stream()
                .filter(webElement -> webElement.getText().contains(shipmentMethod)).collect(Collectors.toList());
        element.get(0).click();
    }

    public Float getShipmentMethodAmount(String shipmentMethod) {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.shipmentMethodList));
        List<WebElement> element = this.shipmentMethodList.stream()
                .filter(webElement -> webElement.getText().contains(shipmentMethod)).collect(Collectors.toList());
        String[] labelArr= element.get(0).getText().split(" - ");
        return Float.valueOf(labelArr[1].substring(1));
    }
    public void clickContinueToPaymentButton(){
        this.continuePaymentButton.click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until(ExpectedConditions.elementToBeClickable(continuePaymentButton)).isDisplayed();
    }
}

package org.evershop.pages.product;
import org.evershop.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDetail extends AbstractComponent {
    @FindBy(css = "h1.product-single-name")
    private WebElement productName;
    @FindBy(css = "h4.product-single-price span")
    private WebElement productPrice;
    @FindBy(css = "#productForm input[name='qty']")
    private WebElement quantityInput;
    @FindBy(css = "#productForm button[type='button']")
    private WebElement addToCartButton;
    @FindBy(xpath = "(//ul[contains(@class,'variant-option-list')])[1]/li")
    private List<WebElement> sizeListOption;
    @FindBy(xpath = "(//ul[contains(@class,'variant-option-list')])[2]/li")
    private List<WebElement> colorListOption;
    public ProductDetail(final WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(int quantity, String size, String color) {
        typeQuantity(quantity);
        selectSize((size));
        selectColor(color);
        clickAddToCart();
    }
    public void typeQuantity(int quantity){
        this.quantityInput.clear();
        this.quantityInput.sendKeys(String.valueOf(quantity));
    }
    public void selectSize(String size){
        List<WebElement> element = this.sizeListOption.stream()
                .filter(webElement -> size.compareToIgnoreCase(webElement.getText())==0).collect(Collectors.toList());
        element.get(0).click();
        this.wait.until(ExpectedConditions.attributeContains(element.get(0), "class","selected"));
    }

    public void selectColor(String color){
        List<WebElement> element = this.colorListOption.stream()
                .filter(webElement -> color.compareToIgnoreCase(webElement.getText())==0).collect(Collectors.toList());
        element.get(0).click();
        this.wait.until(ExpectedConditions.attributeContains(element.get(0), "class","selected"));
    }

    public void clickAddToCart(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.addToCartButton)).click();
    }

    public Float getPrice(){
        String priceLabel = this.wait.until(ExpectedConditions.visibilityOf(this.productPrice))
                .getText().substring(1);
        return Float.valueOf(priceLabel);
    }
    @Override
    public boolean isDisplayed() {

        return this.wait.until((d)-> this.productName.isDisplayed())
                && this.wait.until((d)-> this.sizeListOption.size() > 0)
                && this.wait.until((d)-> this.colorListOption.size() > 0);
    }
}

package org.evershop.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ProductList extends AbstractComponent{
    @FindBy(css = ".listing-tem")
    protected List<WebElement> productItemList;
    @FindBy(css = ".product-list-name > a span")
    protected List<WebElement> itemNameList;
    @FindBy(css = ".product-price-listing span")
    protected  List<WebElement> priceList;
    public ProductList(final WebDriver driver) {
        super(driver);
    }

    public void clickProductByName(String productName){
        //List<WebElement> element = this.productItemList.stream().filter(webElement -> productName.compareToIgnoreCase(webElement.findElement(By.cssSelector(".product-list-name > a span")).getText())>0).collect(Collectors.toList());
        List<WebElement> element = this.itemNameList.stream().filter(webElement -> productName.compareToIgnoreCase(webElement.getText())>=0).collect(Collectors.toList());
        element.get(0).click();
    }

    @Override
    public boolean isDisplayed() {
        return this.wait.until((d) -> this.productItemList.size() > 1);
    }
}

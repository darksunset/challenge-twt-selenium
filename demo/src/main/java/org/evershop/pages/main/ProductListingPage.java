package org.evershop.pages.main;

import org.evershop.pages.common.ProductList;
import org.evershop.pages.common.TopBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductListingPage {
    private WebDriver driver;
    private TopBar topbar;
    private ProductList productList;

    public ProductListingPage(final WebDriver driver){
        this.driver = driver;
        this.topbar = PageFactory.initElements(driver, TopBar.class);
        this.productList = PageFactory.initElements(driver, ProductList.class);
    }

    public TopBar getTopbar() {
        return topbar;
    }

    public ProductList getProductList() {
        return productList;
    }
}

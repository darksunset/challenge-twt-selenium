package org.evershop.pages.product;

import org.evershop.pages.common.TopBar;
import org.evershop.pages.order.CartToast;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {
    private WebDriver driver;
    private TopBar topBar;
    private ProductDetail productDetail;
    private CartToast cartToast;

    public ProductDetailPage(final WebDriver driver){
        this.driver = driver;
        this.topBar = PageFactory.initElements(driver, TopBar.class);
        this.productDetail = PageFactory.initElements(driver, ProductDetail.class);
        this.cartToast = PageFactory.initElements(driver, CartToast.class);
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public CartToast getCartToast() {
        return cartToast;
    }
}

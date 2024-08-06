package org.evershop.test.generateOrder;

import org.evershop.data.dto.*;
import org.evershop.pages.login.CreateAccountPage;
import org.evershop.pages.login.Login;
import org.evershop.pages.login.MyAccountPage;
import org.evershop.pages.main.LandingPage;
import org.evershop.pages.main.ProductListingPage;
import org.evershop.pages.order.ShoppingCartPage;
import org.evershop.pages.product.ProductDetailPage;
import org.evershop.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOrderTest extends BaseTest {
    private LandingPage landingPage;
    private Login loginPage;
    private CreateAccountPage createAccountPage;
    private MyAccountPage myAccountPage;
    private ProductListingPage productListingPage;
    private ProductDetailPage productDetailPage;
    private ShoppingCartPage shoppingCartPage;
    private LoginDTO newUser = LoginDTO.createUserDTO();

    @BeforeTest
    public void setupPages() {
        this.landingPage = new LandingPage(driver);
        this.loginPage = new Login(driver);
        this.createAccountPage = new CreateAccountPage(driver);
        this.myAccountPage = new MyAccountPage(driver);


        this.productListingPage = new ProductListingPage(driver);
        this.productDetailPage = new ProductDetailPage(driver);
        this.shoppingCartPage = new ShoppingCartPage(driver);

    }

    @DataProvider(name = "orderDataProvider")
    protected Object[][] generateOrder() {
        // Order Data
        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        orderProductDTOList.add(OrderProductDTO.createOrderProduct(1, ProductDTO.createProductDTO("Coated glitter chuck taylor all star", "Purple", "M", "Kid")));
        orderProductDTOList.add(OrderProductDTO.createOrderProduct(1, ProductDTO.createProductDTO("Nike court vision low", "White", "X", "Women")));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setPaymentMethod("Credit Card");
        orderDTO.setShippingAddress(AddressDTO.createAddressDTO());
        orderDTO.setBillingAddress(orderDTO.getShippingAddress());
        orderDTO.setShippingMethod("Standard");
        orderDTO.setProductList(orderProductDTOList);

        return new Object[][]{
                {orderDTO}
        };
    }
    @Test
    public void createUser() {
        // Create User
        landingPage.goTo();
        landingPage.getTopBar().clickUserIcon();
        Assert.assertTrue(loginPage.isDisplayed());
        loginPage.clickCreateAnAccount();
        createAccountPage.getNewAccount().fillForm(newUser.getFullName(), newUser.getEmail(), newUser.getPassword());
        createAccountPage.getNewAccount().clickSignUp();
        Assert.assertTrue(landingPage.getHomeBanner().isDisplayed());

        landingPage.getTopBar().clickUserIcon();
        Assert.assertTrue(myAccountPage.getAccountDetails().isDisplayed());
        Assert.assertEquals(myAccountPage.getAccountDetails().getFullName(), newUser.getFullName());
        Assert.assertEquals(myAccountPage.getAccountDetails().getEmail(), newUser.getEmail());
        myAccountPage.getAccountDetails().clickLogout();
    }

    @Test(dataProvider = "orderDataProvider", dependsOnMethods = { "createUser" })
    public void createOrder(OrderDTO orderDTO) {
        landingPage.goTo();
        landingPage.getTopBar().clickUserIcon();

        loginPage.login(newUser.getEmail(), newUser.getPassword());
        Assert.assertTrue(landingPage.getHomeBanner().isDisplayed());


        for (OrderProductDTO orderProductDTO : orderDTO.getProductList()) {
            landingPage.getHomeBanner().clickShopByCategory(orderProductDTO.getProduct().getCategory());
            productListingPage.getProductList().clickProductByName(orderProductDTO.getProduct().getProductName());
            productDetailPage.getProductDetail()
                    .addProductToCart(orderProductDTO.getQuantity(),
                            orderProductDTO.getProduct().getSize(),
                            orderProductDTO.getProduct().getColor());
            // Gather price from product list
            Float productUnitPrice = productDetailPage.getProductDetail().getPrice();
            orderProductDTO.getProduct().setUnitPrice(productUnitPrice);

            productDetailPage.getCartToast().clickContinueShopping();
            productDetailPage.getTopBar().clickLogoIcon();
        }
        landingPage.getTopBar().clickCartIcon();
        Assert.assertTrue(shoppingCartPage.getShoppingCartList().isDisplayed());


        shoppingCartPage.getShoppingCartList().clickCheckoutButton();
        // The unit price displayed in the checkout does not match the ones in the price list (before payment)
        shoppingCartPage.getShipmentAddress().typeCity(orderDTO.getShippingAddress().getCity());
        shoppingCartPage.getShipmentAddress().selectCountry(orderDTO.getShippingAddress().getCountry());
        shoppingCartPage.getShipmentAddress().selectProvince(orderDTO.getShippingAddress().getState());
        shoppingCartPage.getShipmentAddress().typePostCode(orderDTO.getShippingAddress().getPostCode());
        shoppingCartPage.getShipmentAddress().typeFullName(orderDTO.getShippingAddress().getFullName());
        shoppingCartPage.getShipmentAddress().typeTelephone(orderDTO.getShippingAddress().getPhone());
        shoppingCartPage.getShipmentAddress().typeAddress(orderDTO.getShippingAddress().getAddress1());
        shoppingCartPage.getShipmentAddress().selectShipmentMethod(orderDTO.getShippingMethod());
        // Gather delivery charge and calculate tt
        orderDTO.setDeliveryCharge(shoppingCartPage.getShipmentAddress().getShipmentMethodAmount(orderDTO.getShippingMethod()));
        shoppingCartPage.getShipmentAddress().clickContinueToPaymentButton();

        Assert.assertTrue(shoppingCartPage.getPaymentMethod().isDisplayed());
        shoppingCartPage.getPaymentMethod().clickCreditCardOption();
        Assert.assertTrue(shoppingCartPage.getCreditCardPayment().isDisplayed());
        shoppingCartPage.getCreditCardPayment().fillCreditCardWithTestData();
        shoppingCartPage.backToDefaultContent();
        shoppingCartPage.getPaymentMethod().clickPlaceOrder();
        Assert.assertTrue(shoppingCartPage.getCheckoutSuccess().isDisplayed());
        OrderDTO orderGenerated = shoppingCartPage.getCheckoutSuccess().gatherOrder();
        orderDTO.calculateGrandTotal();
        // ASSERTIONS
        // Contact information assertion
        Assert.assertEquals(orderGenerated.getContactFullName(), newUser.getFullName());
        Assert.assertEquals(orderGenerated.getContactEmail(), newUser.getEmail());
        // Payment method
        Assert.assertEquals(orderGenerated.getPaymentMethod(), orderDTO.getPaymentMethod());
        // Shipping Address
        Assert.assertEquals(orderGenerated.getShippingAddress().getFullName(), orderDTO.getShippingAddress().getFullName());
        Assert.assertEquals(orderGenerated.getShippingAddress().getAddress1(), orderDTO.getShippingAddress().getAddress1());
        Assert.assertEquals(orderGenerated.getShippingAddress().getPostCode(), orderDTO.getShippingAddress().getPostCode());
        Assert.assertEquals(orderGenerated.getShippingAddress().getCity(), orderDTO.getShippingAddress().getCity());
        Assert.assertEquals(orderGenerated.getShippingAddress().getState(), orderDTO.getShippingAddress().getState());
        Assert.assertEquals(orderGenerated.getShippingAddress().getCountry(), orderDTO.getShippingAddress().getCountry());
        Assert.assertEquals(orderGenerated.getShippingAddress().getPhone(), orderDTO.getShippingAddress().getPhone());
        // Billing Address
        Assert.assertEquals(orderGenerated.getBillingAddress().getFullName(), orderDTO.getBillingAddress().getFullName());
        Assert.assertEquals(orderGenerated.getBillingAddress().getAddress1(), orderDTO.getBillingAddress().getAddress1());
        Assert.assertEquals(orderGenerated.getBillingAddress().getPostCode(), orderDTO.getBillingAddress().getPostCode());
        Assert.assertEquals(orderGenerated.getBillingAddress().getCity(), orderDTO.getBillingAddress().getCity());
        Assert.assertEquals(orderGenerated.getBillingAddress().getState(), orderDTO.getBillingAddress().getState());
        Assert.assertEquals(orderGenerated.getBillingAddress().getCountry(), orderDTO.getBillingAddress().getCountry());
        Assert.assertEquals(orderGenerated.getBillingAddress().getPhone(), orderDTO.getBillingAddress().getPhone());
        // Products
        Assert.assertEquals(orderGenerated.getProductList().size(), orderDTO.getProductList().size());

        for (OrderProductDTO orderProductDTO : orderDTO.getProductList()) {
            List<OrderProductDTO> productFiltered = orderGenerated.getProductList().stream()
                    .filter(order -> order.getProduct().getProductName()
                            .compareToIgnoreCase(orderProductDTO.getProduct().getProductName()) == 0)
                    .collect(Collectors.toList());
            Assert.assertEquals(productFiltered.size(), 1);
            Assert.assertEquals(productFiltered.get(0).getProduct().getColor(), orderProductDTO.getProduct().getColor());
            Assert.assertEquals(productFiltered.get(0).getProduct().getSize(), orderProductDTO.getProduct().getSize());
            Assert.assertEquals(productFiltered.get(0).getQuantity(), orderProductDTO.getQuantity());
            Assert.assertEquals(productFiltered.get(0).getSubTotal(), orderProductDTO.getSubTotal());
        }
        // Charges
        Assert.assertEquals(orderGenerated.getDeliveryCharge(), orderDTO.getDeliveryCharge());
        Assert.assertEquals(orderGenerated.getDiscount(), orderDTO.getDiscount());
        Assert.assertEquals(orderGenerated.getGrandTotal(), orderDTO.getGrandTotal());
    }

}

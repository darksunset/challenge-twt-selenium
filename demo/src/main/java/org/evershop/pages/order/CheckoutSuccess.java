package org.evershop.pages.order;

import org.evershop.pages.common.AbstractComponent;
import org.evershop.data.dto.AddressDTO;
import org.evershop.data.dto.OrderDTO;
import org.evershop.data.dto.OrderProductDTO;
import org.evershop.data.dto.ProductDTO;
import org.evershop.utils.Formatting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckoutSuccess extends AbstractComponent {
    @FindBy(css = ".checkout-success-customer-info a.primary")
    private WebElement continueShoppingButton;
    @FindBy(css = ".checkout-success-customer-info h3.thank-you span")
    private WebElement orderNumberLabel;
    @FindBy(xpath = "//div[./h3[contains(text(),'Contact information')]]/following-sibling::div[1]")
    private WebElement contactInformationFullName;
    @FindBy(xpath = "//div[./h3[contains(text(),'Contact information')]]/following-sibling::div[2]")
    private WebElement contactInformationEmail;
    @FindBy(xpath = "//div[./h3[contains(text(),'Payment Method')]]/following-sibling::div[1]")
    private WebElement paymentMethod;
    @FindBy(xpath = "//div[./div/h3[contains(text(),'Shipping Address')]]")
    private WebElement shippingAddressSection;
    @FindBy(xpath = "//div[./div/h3[contains(text(),'Billing Address')]]")
    private WebElement billingAddressSection;
    @FindBy(css = "table.items-table tr")
    private List<WebElement> itemsList;
    @FindBy(xpath = "//div[@class='summary-row'][./*[.='Sub total']]")
    private WebElement subtotalRow;
    @FindBy(xpath = "//div[@class='summary-row'][./*[.='Shipping']]")
    private WebElement shippingRow;
    @FindBy(xpath = "//div[@class='summary-row'][./*[.='Discount']]")
    private WebElement discountRow;
    @FindBy(css = ".grand-total-value")
    private WebElement grandTotalLabel;

    // Locators for addresses
    private String addressFullNameCss = ".address__summary .full-name";
    private String address1Css = ".address__summary .address-one";
    private String cityProvincePostcodeCountryCss = ".address__summary .city-province-postcode";
    private String postCodeCityCss = ".address__summary .city-province-postcode div:nth-child(1)";
    private String provinceCountry = ".address__summary .city-province-postcode div:nth-child(2)";
    private String addressPhone = ".address__summary .telephone";
    // Locators for product items in rows
    private String itemRowNameCss = ".product-column div:nth-child(1) span";
    private String itemRowSizeXpath = ".//div[contains(@class,'variant-options')]//li[./*[contains(text(),'Size')]]/span[2]";
    private String itemRowColorXpath = ".//div[contains(@class,'variant-options')]//li[./*[contains(text(),'Color')]]/span[2]";
    private String itemRowPriceXpath = ".//td[3]";
    private String itemRowQuantityCss = ".qty";
    // Locators for summary rows
    private String summaryRowAmmountCss = "span +div>div:nth-child(2)";
    private String summaryRowDescriptionCss = "span +div>div:nth-child(1)";
    private Locale locale = new Locale("en_US");

    public CheckoutSuccess(final WebDriver driver) {
        super(driver);
    }

    public OrderDTO gatherOrder() {
        OrderDTO order = new OrderDTO();
        String[] orderNumberLabel = waitForVisibilityOf(this.orderNumberLabel).getText().split("#");
        order.setOrderNumber(orderNumberLabel[1]);
        order.setContactFullName(waitForVisibilityOf(this.contactInformationFullName).getText());
        order.setContactEmail(waitForVisibilityOf(this.contactInformationEmail).getText());
        order.setPaymentMethod(waitForVisibilityOf(this.paymentMethod).getText());

        order.setShippingAddress(gatherAddress(this.shippingAddressSection));
        order.setBillingAddress(gatherAddress(this.billingAddressSection));

        order.setProductList(gatherProductItems());
        order.setDeliveryCharge(Formatting
                .formatStringToFloat(locale,
                        this.shippingRow.findElement(By.cssSelector(summaryRowAmmountCss)).getText().substring(1)));
        order.setShippingMethod(this.shippingRow.findElement(By.cssSelector(summaryRowDescriptionCss)).getText());
        order.setDiscount(Formatting
                .formatStringToFloat(locale,
                        this.discountRow.findElement(By.cssSelector(summaryRowAmmountCss)).getText().substring(1)));
        order.setGrandTotal(Formatting.formatStringToFloat(locale, this.grandTotalLabel.getText().substring(1)));

        return order;
    }

    private AddressDTO gatherAddress(WebElement addressSection){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setFullName(waitForVisibilityOf(addressSection).findElement(By.cssSelector(addressFullNameCss)).getText());
        addressDTO.setAddress1(addressSection.findElement(By.cssSelector(address1Css)).getText());
        addressDTO.setPhone(addressSection.findElement(By.cssSelector(addressPhone)).getText());
        String[] codeCityArr = addressSection.findElement(By.cssSelector(postCodeCityCss)).getText().split(", ");
        addressDTO.setPostCode(codeCityArr[0]);
        addressDTO.setCity(codeCityArr[1]);
        String[ ] provinceCountryArr = addressSection.findElement(By.cssSelector(provinceCountry)).getText().split(", ");
        addressDTO.setState(provinceCountryArr[0]);
        addressDTO.setCountry(provinceCountryArr[1]);
        addressDTO.setPhone(addressSection.findElement(By.cssSelector(addressPhone)).getText());
        return addressDTO;
    }

    private List<OrderProductDTO> gatherProductItems(){
        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.itemsList)).forEach((element) -> {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setQuantity(Integer.valueOf(element.findElement(By.cssSelector(itemRowQuantityCss)).getText()));;
            orderProductDTO
                    .setSubTotal(Formatting
                            .formatStringToFloat(locale,
                                    element.findElement(By.xpath(itemRowPriceXpath)).getText().substring(1)));
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(element.findElement(By.cssSelector(itemRowNameCss)).getText());
            productDTO.setColor(element.findElement(By.xpath(itemRowColorXpath)).getText());
            productDTO.setSize(element.findElement(By.xpath(itemRowSizeXpath)).getText());
            orderProductDTO.setProduct(productDTO);

            orderProductDTOList.add(orderProductDTO);
        });
        return orderProductDTOList;
    }


    @Override
    public boolean isDisplayed() {
        return this.wait.until(ExpectedConditions.elementToBeClickable(this.continueShoppingButton)).isDisplayed()
                && this.wait.until((d)-> this.itemsList.size() >= 1)
                && this.wait.until((d)-> !this.contactInformationEmail.getText().isEmpty())
                && this.wait.until((d)-> !this.grandTotalLabel.getText().isEmpty());
    }
}

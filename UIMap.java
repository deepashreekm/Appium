package atg.test.selenium.controls.webui.csa;

import java.util.Calendar;

import org.openqa.selenium.By;

import atg.test.selenium.core.ByT;

public class UIMap {

	public static class AddressBookMap {
		public static final By addressBookPage = By.xpath("//main[@role='main']/div//div[2]");
		public static final By addAddress = By.xpath(".//a[contains(.,'Add')]");
		public static final By addresscontainer = By.xpath("//div[@class='AddAddress ContentItem']");
		public static final By saveButton= By.xpath(".//button[contains(.,'Save')]");
		public static final By message  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
	}
	
	public static class AddressItemMap{
		public static final ByT itemByName= ByT.xpath(".//a[contains(.,'%s')]");
		public static final ByT itemByIndex= ByT.xpath(".//td[%d]/a");
		public static final By street = By.xpath("./parent::th/following-sibling::td[2]");
		public static final By defaultItem = By.xpath("./following-sibling::span[contains(.,'Default')]");
		public static final By delete = By.xpath("./parent::th/following-sibling::td/button");
	}
	public static class WelcomePageMap{
		public static final By container =By.xpath(".");
		public static final By header = By.linkText("B2CStore");
		public static final By homeLink = By.linkText("home");

	}
	
	public static class BrowsePageMap {

		public static final By browsePage = By.xpath("//strong[contains(.,'Browse Page')]");

	}
	
	public static class ProductDetailsPageMap {
		public static final By productDetailsPage = By.xpath("//main[@role='main']/div[contains(@id,'ProductDetail')]");
		public static final By productQtySelect = By.dataName("quantity");
		public static final By productSizeSelect = By.dataName("size");
		public static final By productColorSelect = By.dataName("color");
		public static final By shoppingcart = By.linkText("Shopping Cart");
		public static final By addtocart = By.xpath(".//button[@id='addToCart']");
		
		public static final By productName = By.xpath(".//h2");
		public static final By productImage = By.xpath("//div[@id='listPrice']/following-sibling::img");
		public static final ByT prodImage = ByT.xpath(".//img[@alt='%s']");
		public static final By enlargedProductImage = By.xpath(".//img[@id='productImage' and ancestor::li[contains(@class,'Enlarged')]]");
		public static final By productPriceLabel = By.xpath("//span[@class='csa-current-price']");
		public static final By oldPriceLabel = By.xpath(".//div[@id='pickerPrice']//span");
		public static final By selectedskuLabel = By.xpath(".//div[contains(@class,'csa-sku-status-label')]");
		public static final By outofstockLabel = By.xpath(".//div[contains(@class,'csa-sku-status-label')]");
		public static final By productQtyLabel = By.className("qtyLabelText");
		public static final By qtyerrormsg  =  By.id("errorbox-1");
		public static final By errorMessage  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
		public static final By productSalePrice  = By.xpath("//span[@class='csa-previous-price']");
		public static final By productFeatureSelect = By.id("featureSelect");
		public static final By productFinishSelect = By.id("woodFinishSelect");
		public static final By inventoryStatus = By.id("buttonText");

		public static final By detailsTitle = By.xpath(".//p[@class='detailsTitle' and contains(.,'Details')] | .//p[@class='detailsTitle' and contains(.,'Detalles')]");
		public static final By relatedItemsTitle = By.xpath(".//li[@class='sliderTitle'][contains(.,'Related Items')]");
		public static final By recentlyViewedItemsTitle = By.xpath(".//div[@class='sliderTitle'][contains(.,'Recently Viewed Items')]");
		public static final By detailsSection = By.xpath(".//p[@class='detailsContent']");
		public static final By relatedItemsSection = By.xpath(".//li[contains(.,'Related Items')]/following-sibling::li/div[@class='itemsContainer ']");
		public static final By recentlyViewedItemsSection = By.xpath(".//div[contains(.,'Recently Viewed Items')]/following-sibling::div[@class='itemsContainer ']");
		public static final ByT actionButton = ByT.xpath(".//button[contains(.,'%s')]");
		public static final By sizeChart = By.xpath("//a[contains(.,'Size chart')]");
		public static final By canonicalLink = By.xpath("//head/link[@rel='canonical']");
		public static final By productAlertMessage = By.xpath(".//div[contains(@class,'alert alert-info')]");
		

	}
	public static class CheckoutPageMap {
		public static final By checkoutPage = By.xpath("//main[@role='main']");
		public static final By checkoutContainer = By.xpath("//div[contains(@id,'Checkout') and not(contains(@id,'Login'))]");
		public static final By cancelorder = By.partialLinkText("Cancel");
		public static final By placeorder = By.xpath(".//button[contains(.,'Place order')]");
		public static final By editCart = By.xpath(".//a[contains(.,'Edit cart')]");
		public static final By cartEmpty = By.xpath("//div[@id='CartEmptyMessage-25']/div[@class='alert alert-info csa-cart-empty']");
		public static final By errorMessage  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
		public static final ByT errorMessageText  = ByT.xpath("//*[@id='toast-container']//div[@class='toast-message' and contains(.,'%s')]");
		public static final By productsListPanel = By.xpath(".//div[contains(@id,'CheckoutCartSummary')]/table");
	}
	public static class ShippingMethodPanelMap {
		public static final By container = By.xpath("//div[contains(@id,'ShippingMethodSelector')]");
		public static final ByT shippingMethod = ByT.xpath(".//input[@value='%s']");
		public static final By continueBtn = By.xpath(".//button[contains(.,'Save and continue')]");
		public static final By deliveryBtn = By.id("deliveryBtn");
		public static final By deliveryLabel = By.xpath(".//strong[contains(.,'Delivery')]");
		public static final By deliveryLabelWithMethod = By.xpath("./div[@class='panel-heading']/div/strong");
		public static final ByT sign = ByT.xpath("//button[@data-name='delivery-btn-edit']/following-sibling::span[contains(@class,'%s')]");		
	}
	public static class OrderSummaryPanelMap {
		public static final By container = By.xpath("//table[contains(@data-bind,'orderSummary') or contains(.,'Order Summary')]|//div[@id='orderSummary']");
		public static final By subtotalPrice = By.xpath(".//tbody/tr[1]/td");
		public static final By subtotalLabel = By.xpath(".//tbody/tr[1]/th");
		public static final By discountPrice = By.xpath(".//tbody/tr[2]/td");
		public static final By discountLabel = By.xpath(".//tbody/tr[2]/th");
		public static final By shippingPrice = By.xpath(".//tbody/tr[3]/td");
		public static final By shippingLabel = By.xpath(".//tbody/tr[3]/th");
		public static final By taxPrice = By.xpath(".//tbody/tr[4]/td");
		public static final By taxLabel = By.xpath(".//tbody/tr[4]/th");
		public static final By totalPrice = By.xpath("./tbody/tr[6]/td[1]/strong[contains(.,'Total')] | //strong[contains(@class,'csa-total')]");
		public static final By totalLabel = By.xpath(".//tbody/tr[5]/th");
		public static final By promotionsLabel = By.xpath(".//tbody/tr[8]//label|./following-sibling::p");
		public static final By infoLabel = By.xpath(".//tbody/tr[9]//label|./following-sibling::p");
		public static final By couponHeader = By.xpath(".//tbody/tr[6]//th");
		public static final By headingLabel = By.xpath(".//thead//th");
		public static final By couponCodeField = By.dataName("couponCode");
		public static final ByT appliedCoupon = ByT.xpath("//tr[contains(.,'%s')]");
		public static final ByT appliedCouponindex = ByT.xpath("//span[@class='csa-claimed-coupon-code'][%d]");
		public static final By applyButton = By.xpath("//button[@name='claimCoupon']");
		public static final By removeButton = By.xpath("//button[@name='removeCoupon']");
		public static final By fieldErrorMessage = By.xpath(".//div[@class='ws-errorbox']/p");
	}	
	public static class OrderSummaryPanelCheckoutMap {
		public static final By container = By.xpath("//table[contains(.,'Order summary')]");
		public static final By subtotalPrice = By.xpath(".//tbody/tr[1]/td[1]");
		public static final By subtotalLabel = By.xpath(".//tbody/tr[1]/th[1]");
		public static final By discountPrice = By.xpath(".//tbody/tr[2]/td[1]");
		public static final By discountLabel = By.xpath(".//tbody/tr[2]/th[1]");
		public static final By shippingPrice = By.xpath(".//tbody/tr[3]/td[1]");
		public static final By shippingLabel = By.xpath(".//tbody/tr[3]/th[1]");
		public static final By taxPrice = By.xpath(".//tbody/tr[4]/td[1]");
		public static final By taxLabel = By.xpath(".//tbody/tr[4]/th[1]");
		public static final By totalPrice = By.xpath(".//tbody/tr[5]/td[1]");
		public static final By totalLabel = By.xpath(".//tbody/tr[5]/th[1]");
	}
	public static class OrderSummaryPanelOrderDetailsMap {
		public static final By container = By.xpath(".//table[@summary='Order summary includes subtotal, discounts, shipping, tax and total']");
		public static final By subtotalPrice = By.xpath(".//tbody/tr[1]/td[1]");
		public static final By subtotalLabel = By.xpath(".//tbody/tr[1]/th[1]");
		public static final By discountPrice = By.xpath(".//tbody/tr[2]/td[1]");
		public static final By discountLabel = By.xpath(".//tbody/tr[2]/th[1]");
		public static final By shippingPrice = By.xpath(".//tbody/tr[3]/td[1]");
		public static final By shippingLabel = By.xpath(".//tbody/tr[3]/th[1]");
		public static final By taxPrice = By.xpath(".//tbody/tr[4]/td[1]");
		public static final By taxLabel = By.xpath(".//tbody/tr[4]/th[1]");
		public static final By totalPrice = By.xpath(".//tbody/tr[5]/td[1]");
		public static final By totalLabel = By.xpath(".//tbody/tr[5]/th[1]");
	}
	public static class CartPageMap {
		public static final By cartPage = By.xpath("//strong[contains(.,'Cart Page')]");

	}
	
	public static class AccountPageMap {
		public static final By accountPage = By.xpath("//div[@class='AccountMenuNavigation ContentItem']/parent::div/parent::div");
		public static final By personalInformation = By.xpath(".//a[contains(.,'Personal')]");
		public static final By orderHistory = By.xpath(".//a[contains(.,'Order')]");
		public static final By addressBook = By.xpath(".//a[contains(.,'Address')]");
		public static final By paymentInformation = By.xpath(".//a[contains(.,'Payment')]");
		public static final By changePassword = By.xpath(".//a[contains(.,'Change')]");
		public static final By checkoutDefaults = By.xpath(".//a[contains(.,'Checkout')]");
		public static final By logout = By.xpath(".//a[contains(.,'Log Out')]");

	}
	public static class ChangePasswordFormMap{
		public static final By container=By.xpath(".//div[@class='AccountChangePassword ContentItem']");
		public static final By header = By.xpath(".//h2");
		public static final By oldPassword = By.dataName("oldPassword");
		public static final By newPassword = By.dataName("newPassword");
		public static final By confirmPassword = By.dataName("confirmPassword");
		public static final By saveButton = By.xpath(".//button[contains(.,'Save')]");
		public static final By cancelButton = By.xpath(".//button[contains(.,'Cancel')]");
	}
	public static class CheckoutDefaultsFormMap{
		public static final By container =  By.xpath(".//div[@class='AccountCheckoutDefaults ContentItem']");
		public static final By shippingMethod = By.dataName("shippingMethod");
		public static final By shippingMethodlabel = By.xpath(".//label[@for='shippingMethod']");
		public static final By shippingAddress = By.dataName("shippingAddress");
		public static final By shippingAddressLabel = By.xpath(".//label[@for='shippingAddress']");
		public static final By paymentMethod = By.dataName("paymentMethod");
		public static final By paymentMethodLabel = By.xpath(".//label[@for='paymentLabel']");
		public static final By saveButton = By.xpath(".//button[contains(.,'Save')]");
		public static final By cancelButton = By.xpath(".//button[contains(.,'Cancel')]");
		public static final By addAddress = By.linkText("Add address");
		public static final By addCreditCard = By.linkText("Add credit card");
		public static final By header = By.xpath(".//h2[contains(.,'Checkout Defaults')]");
	
	}
	public static class HeaderPanelMap {
		public static final By headerPanel = By.xpath("//div[contains(@id,'ContentSlotHeader')]");
		public static final By home = By.xpath("//a[@href='home']");
		public static final By browse = By.linkText("browse");
		public static final By product = By.linkText("product");
		public static final By cart = By.xpath("//span[contains(@class,'glyphicon-shopping-cart')]");
		public static final By carticon = By.xpath("//span[contains(@class,'glyphicon-shopping-cart')]");
		public static final ByT cartindicator = ByT.xpath("//a[@href='cart']/span[contains(.,'%s')]");
		//public static final By account = By.xpath("//button[span[contains(.,'Account')]]|//a[span[contains(.,'Account')]]");
		//public static final By account = By.id("AccountMenu-23");
		//public static final By account = By.className("btn csa-btn-default");
		public static final By account = By.xpath("//a[@href='login']/span[text()='Account']");
	//public static final By account = By.xpath(".//*[@id='AccountMenu-23']/a/span[2]");
		
		public static final By accountmenu = By.xpath("//div[@id='AccountMenu-23']//span[text()='Account']");
		public static final By checkout = By.xpath("//a[span[contains(.,'Checkout')]]");
		public static final By richCartCount = By.xpath("//a/span[@class='badge csa-commerce-item-count-badge']");
		public static final By siteLogo = By.xpath("//img[contains(@title,'CSA Store')]");
		//public static final By searchQueryField = By.dataName("search");
		//public static final By searchQueryField = By.dataName("searchText");
		public static final By searchQueryField = By.xpath("//input[@type='search']");
//				+ "div[contains(@class,'form-control) and @id=')])");
//		public static final By searchQueryField = By.name("Ntt");
		
		public static final By productsCount = By.xpath("//div[contains(@class,'results')]/div[1]");
		public static final By goButtion = By.xpath("//span/button");
		public static final By login = By.xpath("//a[contains(@href,'login')]");
		public static final By logout = By.xpath(".//a[contains(.,'Log Out')]|.//a[contains(.,'Abmelden')]");
		public static final By addressBook = By.xpath(".//a[contains(.,'Address Book')]");
		public static final By personalInfo = By.xpath(".//a[contains(.,'Personal Information')]");
		public static final By paymentInfo = By.xpath(".//a[contains(.,'Payment Information')]");
		public static final By yourOrders = By.xpath(".//a[contains(.,'Order History')]");
		public static final By sitePreference = By.xpath(".//div[@class='SitePreferences ContentItem']//a");
		public static final By languagePreference = By.xpath(".//div[@class='SitePreferences ContentItem']//a[2]");
		public static final ByT language = ByT.xpath(".//div[@class='SitePreferences ContentItem']//a[contains(.,'%s')]");
		public static final By errorMessage = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
		public static final By changePassword = By.xpath(".//a[contains(.,'Change Password')]");
		public static final By checkoutDefault = By.xpath(".//a[contains(.,'Checkout Defaults')]");
		
        
		public static class Iphone{
        	public static final By account = By.xpath("//span[contains(@class,'glyphicon glyphicon-user')]");
		}
	}

	public static class FooterPanelMap {

		public static final By footerPanel = By.xpath("//footer[@role='contentinfo']");

		public static final By shareLink = By.linkText("Share");
		public static final By contactUsLink = By.linkText("Contact Us");
		public static final By storesLink = By.xpath(".//a/span[contains(.,'Stores')] | .//a/span[contains(.,'Tiendas')]");
		public static final By moreLink = By.linkText("More...");
		public static final By copyrightLink = By.xpath(".//div[@class='footerCopyright']/a");
		
		public static final By aboutUsLink = By.linkText("About Us");
		public static final By corporateSiteLink = By.linkText("Corporate Site");
		public static final By careersLink = By.linkText("Careers");
		public static final By newsLink = By.linkText("News");
		public static final By FAQsLink = By.linkText("FAQs");
		public static final By privacyLink = By.linkText("Privacy");
		public static final By termsLink = By.linkText("Terms");
		public static final By shippingLink = By.linkText("Shipping");
		public static final By copyRightText = By.xpath("//p[contains(@data-bind,'html: copyright')]/p");
		/*public static final By copyright = By.xpath("//p[contains(@data-bind,'copyright') and p[contains(.,'Copyright � 1994 - " + Calendar.getInstance().get(Calendar.YEAR) + ""
				+ ", Oracle and/or its affiliates. All rights reserved.') and contains(.,'CSA� is a registered trademark of Oracle and/or its affiliates. "
				+ "Other names may be trademarks of their respective owners.')]]");
*/	
		public static final By copyright = By.xpath("//p[contains(@data-bind,'html: copyright')]/p");
	
	}
	public static class NavigationPanelMap{
		public static final By navigationPanel = By.id("categoryNav");
		public static final ByT topLevelCategory = ByT.xpath("//div[contains(@id,'MainMenu')]/ul/li/a[@title='%s']");
		public static final ByT subCategory = ByT.xpath("./following-sibling::ul/li/a[contains(.,\"%1$s\")]");	
	
        public static class Iphone{
        	public static final By navigationPanel = By.xpath("//nav[@role='navigation']/div[@class='container']");
        	public static final By navigationToggle = By.xpath("//button/span[contains(.,'navigation')]");
        }
	}
	public static class StorePopupMap{
		public static final By popupLocator = By.xpath("dummy");
	}
	public static class HomePageMap{
		public static final By container =By.xpath("//main[@role='main']/div[contains(@id,'ContentSlotMain')]");
		public static final By homeLink = By.linkText("home");
		public static final By promoImageNext = By.xpath("//div[@id='scrollable-media-content-carousel']/a[2]/span[1]");
		public static final ByT promoImage = ByT.xpath("//div[@id='scrollable-media-content-carousel']//span[@class='csa-media-spotlight-text1']/h1[contains(.,'%s')]");
		public static final ByT promoImageByCount = ByT.xpath("//div[@id='scrollable-media-content-carousel']//span[@class='csa-media-spotlight-text1']/h1[%d]");
		public static final By browseLink = By.linkText("browse");
		public static final By productLink = By.linkText("product");
		public static final By cartLink = By.linkText("cart");
		
		public static final By homePage = By.xpath("//form[@id='Login']");
		
		public static final ByT promoFreeShippingText = ByT.xpath("//div[@id='scrollable-media-content-carousel']//span[@class='csa-media-spotlight-text4']/h4[contains(.,'%s')]");
		
		public static final By promoPanel = By.xpath("//div[starts-with(@data-name,'ScrollableMediaContentSpotlight-ATGSlot')]");
		
		public static final By accountLink = By.linkText("account");
		public static final ByT pageType = ByT.xpath("//p[strong[contains(.,'%s')]]/parent::div");
		public static final By productdetailspage = By.xpath("//p[contains(.,'Page:') and strong[contains(.,'Product Detail Page')]]");

		public static final By checkoutpage = By.xpath("//p[contains(.,'Page:') and strong[contains(.,'Checkout Page')]]");
		public static final By homedetailsPage = By.xpath("//div[contains(.,'Page:') and strong[contains(.,'Home Page')]]");
		public static final By cartdetailsPage = By.xpath("//p[contains(.,'Page:') and strong[contains(.,'Cart Page')]]");
		public static final By accountdetailstPage = By.xpath("//p[contains(.,'Page:') and strong[contains(.,'Account Page')]]");
		public static final By browsedetailstPage = By.xpath("//p[contains(.,'Page:') and strong[contains(.,'Browse Page')]]");
		public static final By checkoutLink = By.xpath("//span[@id='CartStatus-12']//span[@class='badge csa-commerce-item-count-badge']");

	}
	
	public static class PromoItemsPanelMap{
		public static final ByT itemByText = ByT.xpath(".//article[contains(.,'%s')]");
		public static final ByT itemByIndex = ByT.xpath(".//article[%d]");
		public static final By itemForSize = By.xpath(".//article");
		public static final By navigateRight= By.xpath(".//a[@class='right carousel-control']/span");
		public static final By navigateLeft= By.xpath(".//a[@class='left carousel-control']/span");
		public static final By indicator= By.xpath(".//ol[@class='carousel-indicators']");
		public static final ByT indicatorIndex= ByT.xpath(".//ol[@class='carousel-indicators']/li[%d]");
	}
	
	public static class PromoItemMap{
		public static final By nameLabel = By.xpath(".//div[@class='carousel-caption']/div[@class='csa-carousel-text-wrapper csa-hero-title']");
		public static final By image = By.xpath(".//img");
		public static final By promolink = By.xpath(".//div[@class='csa-carousel-text-wrapper csa-hero-link']/a");
		
		public static class Iphone{
        	public static final By nameIphone = By.xpath(".//div[@class='carousel-caption']/div[@class='csa-carousel-text-wrapper csa-hero-link']");
		}
	}
	
	public static final class ShoppingCartMap{
		public static final By shoppingCart = By.xpath("//div[contains(@id='ContentSlotFooter'");
	}
	public static final class AboutUsFormMap{
		public static final By container=By.xpath("//div[contains(@id,'RichTextMain')]");
		public static final By aboutUsContent=By.xpath("/div");
		public static final By aboutUsHeader=By.xpath("/div/h2");
		public static final By aboutUsText=By.xpath("/div/h2/following-sibling::p");
	}
	public static final class CorporateSiteFormMap{
		public static final By container=By.xpath("//div[contains(@id,'RichTextMain')]");
		public static final By clickHere = By.xpath("//div[contains(@id,'RichTextMain')]//a");
	}
	public static final class StoreMainMap{
		public static final By mainContainer = By.xpath("//div[contains(@id,'RichTextMain')]");
		public static final By header = By.tagName("h2");
		public static final By clickHere = By.xpath("//div[@class='atg_store_main']//a");
	}
	public static class BaseProductItemMap {
		public static final By productImage = By.xpath(".//a/img|./parent::div/parent::div/parent::div/parent::div/parent::div//img");
		public static final By nameLink = By.xpath("./div/a|./strong|./a//p | .//span[@class='csa-inline-block']");
		public static final By namelabel = By.xpath("./strong");
		public static final By sizelabel = By.xpath("./parent::div/parent::div//small[contains(.,'Size')]");
		public static final By colorlabel = By.xpath("./parent::div/parent::div//small[contains(.,'Color')]");
		public static final By stocklabel = By.xpath("./parent::div/parent::div//span[contains(@class,'csa-availability-status')]/parent::small");
		public static final By removebutton = By.xpath("./parent::div/parent::div/parent::div/parent::div/parent::div//button[@name='remove']");
		public static final By quantitylabel = By.xpath("./parent::div/parent::div/parent::div//input[@name='quantity']");
		public static final By updatebutton = By.xpath("./parent::div/parent::div/parent::div//button[contains(.,'Update')]");
		public static final By itemprice = By.xpath("./parent::div/parent::div/parent::div//span[contains(@class,'price')]");
		public static final By itempricetotal = By.xpath("./parent::div/parent::div/parent::div//span[contains(.,'Subtotal')]/following-sibling::strong");
		public static final By errormessage = By.xpath("./parent::div/parent::div/parent::div//div[@class='ws-errorbox']/p");

		public static final By qty = By.name("quantity");
	}
	public static final class ProductItemMap{
		public static final By qtyLabel = By.xpath("");
		public static final By priceLabel = By.xpath(".//p[strong]");
		public static final By oldPriceLabel = By.tagName("del");
	}
	public static class SearchResultsPageMap {
		public static final By searchResultsPage = By.xpath("//div[contains(@id,'ResultsList')]");
		public static final By productSort = By.xpath("//select[contains(@id,'sortSelection')]");
		public static final By productImg = By.tagName("img");
		public static final ByT itemByName = ByT.xpath("//div[contains(@id,'ResultsList')]//div[contains(@class,'csa') and contains(.,'%s')]");
		public static final ByT itemByIndex = ByT.xpath(".//div[contains(@class,'row text-center')][%1$s]/div[contains(@class,'col-xs')][%2$s]");
		//public static final ByT itemByIndex = ByT.xpath(".//div[contains(@class,'container')]/div[contains(@class,'row text-')][%1$s]/div[contains(@class,'col-')][%2$s]//div");
		public static final By noResultsMessage = By.xpath(".//strong[contains(.,'No results found.')]");
		public static final By previousPageButton = By.xpath("//a[@rel='prev']");
		public static final By nextPageButton = By.xpath("//a[@rel='next']");
		public static final By pagination = By.xpath("//ul[contains(@class,'pagination pa')]");
		public static final By resultsCount = By.xpath("./div[1]/div[1]");
		public static final By noResultFound = By.xpath(".//div[contains(.,'No results found')]");
		public static final ByT pageNumber = ByT.xpath("//ul[contains(@class,'pagination pa')]/li/a[contains(.,'%s')]");
		public static final By activePageNumber = By.xpath("//ul[contains(@class,'pagination pa')]/li[@class='active']/a");
		public static final By disabledPageNavigation = By.xpath("//ul[contains(@class,'pagination pa')]/li[@class='disabled']/a");
		public static final By paginationText = By.xpath(".//ul[@class='pagination pagination-sm']");
		public static final By paginationMore = By.xpath(".//li[@class='csa-pagination-more']/span");
		
		
		
	}
	
	public static final class FeaturedListMap{
		public static final By featuredProductsList = By.xpath("//div[contains(@id,'ScrollableProductSpotlight')]/div[@id='scrollable-product-carousel']");
		public static final By scrollList= By.xpath(".//div[@class='carousel-inner']");
		public static final By pageCounter= By.xpath(".//div[@class='text-center']");
		public static final ByT featuredProduct= ByT.xpath(".//article[@class='item active']//div[starts-with(@class,'col')]//img[@alt='%1$s']");
		public static final ByT featuredItem= ByT.xpath(".//article[@class='item active']//div[starts-with(@class,'col')][%1$s]/a");
		public static final ByT featuredItemName= ByT.xpath(".//img[@alt='%1$s']/../../a");
		public static final ByT featuredItemNameByPage= ByT.xpath(".//article[@class='item active']//div[starts-with(@class,'col')][%1$s]/a//span[@class='csa-inline-block']");
		public static final ByT featuredItemPrice= ByT.xpath(".//img[@alt='%1$s']/../span/span");
		public static final By navigateRight= By.xpath(".//a[@class='right carousel-control']/span");
		public static final By navigateLeft= By.xpath(".//a[@class='left carousel-control']/span");
	}
	public static final class ProductListMap{
		public static final By viewAllLink = By.xpath("");
		public static final ByT pageNumber = ByT.xpath("//*[@class='atg_store_pagination'][%1$s]/ul/li/a[contains(.,'%2$s')]");
		public static final By ajaxSpinner = By.id("ajaxSpinner");
	}
	public static class BaseProductListMap{
		public static final By productList = By.xpath("//main[@role='main']//div[contains(@id,'ResultsList')]");
		public static final ByT selectedProduct = ByT.xpath(".//img[@alt=\"%1$s\"]");
	}
	public static class ShoppingCartPageMap {
		public static final By shoppingCartPage = By.xpath("//main[@role='main']//div[contains(@id,'CartEditor')]");
		public static final ByT productname = ByT.xpath(".//strong[contains(.,'%1$s')]");
		public static final ByT productSize = ByT.xpath(".//small[contains(.,'%1$s')]");
		public static final ByT productColor = ByT.xpath(".//span[contains(.,'%1$s')]");
		public static final By checkOutButton = By.xpath(".//button[contains(.,'Checkout')]");
		public static final By productsListPanel = By.xpath("./div[@class='row']");
		public static final By orderSummaryPanel = By.xpath(".//table[contains(@data-bind,'orderSummary')]");
		public static final By continueShopping = By.linkText("Continue shopping");
		public static final By emptyCartText = By.xpath(".//div[contains(@class,'alert alert-info') and contains(.,'Your shopping cart is empty.')]");
		public static final By errorMessage  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
	}
	
	public static class ShippingAddressPageMap {
		public static final By shippingAddressPage = By.xpath("//div[@class='col-xs-12']/div[@id='checkoutSteps']");
		public static final By shipTo = By.xpath("//strong[contains(.,'Ship')]");
		
	}
	
	
	public static class ProductsListPanelMap {
		public static final ByT productItemByText = ByT.xpath(".//strong[contains(.,'%s')]/parent::a");
		public static final ByT productItemByIndex = ByT.xpath(".//div/div[%d]//div/div[2]/div/div/div[1]/a/strong/parent::a");
		public static final By itemsCount = By.xpath("//div[@class='row cart-product']");
		
	}
	public static class LoginPageMap {
		public static final By loginPage = By.xpath(".//form[@id='Login']");
		public static final By username = By.xpath(".//input[@id='userid']");
		public static final By password = By.xpath(".//input[@id='password']");
		//public static final By returnLoginFormContainer=By.xpath("");
		public static final By submitBtn=By.xpath(".//button[@id='btnActive']");
//		public static final By submitBtn=By.id("btnActive");
//		public static final By signupFormContainer = By.xpath("//div[contains(@id,'RegisterUser')]");
	}
	
	public static class LoginFormMap {
		public static final By emailField = By.dataName("logInEmailAddress");
		public static final By passwordField = By.dataName("logInPassword");
		public static final By loginButton = By.xpath(".//button[contains(.,'Log in')]|.//button[contains(.,'Anmelden')]");//temp change until login text has been fixed
//		public static final ByT errorMessage = ByT.xpath("//*[@id='toast-container']/div[2]/div[@class='toast-message' and contains(text(),'%s')]"); ////*[@id="toast-container"]/div/div
		public static final By errorMessage = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
		public static final By guestCheckoutEmailAddress = By.dataName("guestLogInEmailAddress");
		public static final By guestCheckoutBtn = By.xpath(".//button[contains(.,'Guest')]");
		public static final By createAccountEmailAddress = By.xpath(".//input[contains(@id,'registerEmailAddress')]");
		public static final By createAccountBtn = By.xpath(".//button[contains(.,'Create account')]");
		public static final By newUserLabel = By.xpath(".//*[contains(.,'New user')]|.//*[contains(.,'Neuer Benutzer')]");
		public static final By privacyPolicyLink = By.xpath("//a[contains(.,'Privacy policy')]|//a[contains(.,'Datenschutzrichtlinie')]");
	}
	public static class ReturnLoginFormMap {
		//public static final By guestEmailField = By.dataName("guestLogInEmailAddress");
		
		public static final By guestEmailField = By.xpath(".//input[contains(@id,'-guestLogInEmailAddress')]");
		
		//public static final By guestCheckout = By.xpath(".//button[contains(.,'Guest checkout')]");
		public static final By guestCheckout = By.xpath(".//button[text()='Guest checkout']");
	}
	public static class MandatoryFieldMap {
		public static final By errorMessage = By.xpath("//div[contains(@id,'errorbox')]");
		public static final By errorMessageforfield = By.xpath("./following-sibling::div/p");
	}
	public static class MandatorySelectMap {
		public static final By errorMessage = By.xpath("ancestor::*[contains(@class,'errorState')]//span[contains(@class,'errorMessage')]");
		public static final By errorMessageforfield = By.xpath("./following-sibling::div/p");
	}

	public static class NewAddressFormMap {
		public static final By nickNameField = By.xpath(".//input[@data-name='billingNickName']|.//input[@data-name='nickName']|.//input[contains(@id,'addressNickname')]|.//input[@name='nickName']");
		public static final By firstNameField = By.xpath(".//input[@data-name='billingfirstName']|.//input[@data-name='firstName']");
		public static final By lastNameField = By.xpath(".//input[@data-name='billinglastName']|.//input[contains(@data-name,'lastName')]");
		public static final By street1Field = By.xpath(".//input[@data-name='billingaddress1']|.//input[@data-name='address1']|.//input[contains(@data-name,'addressLine1')]");
		public static final By street2Field = By.xpath(".//input[@data-name='billingaddress2']|.//input[@data-name='address2']|.//input[contains(@data-name,'addressLine2')]");
		public static final By cityField = By.xpath(".//input[@data-name='billingcity']|.//input[@data-name='city']");
		public static final By zipCodeField = By.dataName("zip");
		public static final By countrySelect = By.xpath(".//select[@data-name='billingcountry']|.//select[@data-name='country']|.//select[@name='country']");
		public static final By stateSelect = By.xpath(".//select[@data-name='billingstate']|.//select[@data-name='state']|.//select[@name='state']");
		public static final By phoneField = By.xpath(".//input[@data-name='billingphonenumber']|.//input[@data-name='phone']");
	}
	public static class NewShippingAddressFormMap {
		public static final By newShippingAddressForm = By.xpath("//div[contains(@id,'ShippingAddressSelector')]");
		public static final By saveAddressCheckBox = By.xpath(".//input[contains(@data-bind,'saveShippingAddressToProfile')]");
		public static final By successIcon = By.xpath(".//span[contains(@class,'glyphicon') and contains(@class,'text-success')]");
		public static final By shippingAddressHeading = By.id("shipToAddress-heading");
		public static final By edit = By.dataName("shipToAddress-btn-edit");
		public static final By clearFormButton = By.dataName("shipToAddress-btn-clear");
		public static final By chooseSaveAddress=By.xpath(".//a[contains(.,'saved address')]");
	}
	public static class EditAddressFormMap{
		public static final By container = By.xpath("//div[contains(@id,'EditAddress')]");
		public static final By saveButton= By.xpath(".//button[contains(.,'Save')]");
		public static final By cancelButton= By.xpath(".//button[contains(.,'Cancel')]");
		public static final By makeDefault = By.xpath(".//input[contains(@data-bind,'useAsDefaultShippingAddress')]");
	}
	public static class BaseDialogMap{
		public static final By header = By.xpath("//h4[@data-name='selectAddressModalTitle']|//h4[@id='benefitsModalTitle']");
		public static final By caption = By.xpath(".//caption");
		public static final By label=By.xpath(".//thead//th");
		public static final By close = By.xpath(".//button[@class='close']");
		public static final By cancel = By.xpath(".//button[contains(.,'Cancel')]");
		public static final By apply = By.xpath(".//button[contains(.,'Apply')]");
	}
	
	public static class SelectSavedAddressDialogMap {
		public static final By container = By.xpath("//h4[@data-name='selectAddressModalTitle']/ancestor::div[contains(@class,'modal-content')]|//h4[@id='selectAddressModalTitle']/ancestor::div[contains(@class,'modal-content')]");
		public static final ByT nickName=ByT.xpath(".//div[@class='radio csa-radio']/label[contains(.,'%1$s')]/preceding-sibling::input|.//strong[contains(.,'%1$s')]/input[1]");
		
	}
	public static class SelectBillingSavedAddressDialogMap {
		public static final By container = By.xpath("//h4[@data-name='selectBillingAddressModalTitle']/ancestor::div[contains(@class,'modal-content')]");
		public static final ByT nickName= ByT.xpath(".//div[@class='radio csa-radio']/label[contains(.,'%s')]");
		public static final By applyButton=By.xpath(".//div[@class='modal-footer']//button[contains(.,'Apply')]");
	}
	
	public static class SelectBillingSavedAddressProfileDialog {
		public static final By container = By.xpath("//div[@id='selectAddressModal']");
		public static final ByT nickName=ByT.xpath(".//strong/a[contains(.,'%s')]");
	}
	
	public static class SelectSavedCreditCardDialogMap {
		public static final By container = By.xpath("//h4[contains(.,'Choose a saved credit card')]/ancestor::div[@class='modal-dialog']|//*[@data-name='selectCreditCardModal']");
		public static final ByT nickName=ByT.xpath(".//div[@class='modal-body']//label[contains(.,'%s')]");
	}
	
	public static class SecurityCodeExplainationDialogMap {
		public static final By container = By.xpath("//*[@data-name='securityCodeExplanationModalTitle']/ancestor::div[@class='modal-dialog']");
	}
	public static class CartSummaryDialogMap {
		public static final By container = By.xpath("//div[contains(@aria-labelledby,'richCart')]//div[@class='modal-dialog']");
		public static final ByT emptyCartMessage=ByT.xpath(".//div[contains(.,'%s')]");
		public static final By continueShopping = By.linkText("Continue Shopping"); //Continue Shopping
		public static final By viewCart  =By.xpath(".//a[@href='cart']");
		//public static final By viewCart  =By.xpath(".//*[@id='CartLink-24']/div/div[2]/div/div[2]/div[2]/div[1]/a");
		public static final By checkout = By.name("checkout");	
		public static final By subtotal = By.xpath(".//div[contains(@class,'csa-subtotal')]");
		public static final By itemCount = By.xpath(".//span[contains(@class,'item-count')]");
		public static final By close = By.xpath(".//button[@class='close']");
	}
	
	public static class ChooseASavedAddressMap {
		public static final By container = By.xpath("//div[@class='modal fade in']");
		public static final By cancel = By.xpath(".//button[contains(.,'Cancel')]");
		public static final By home = By.xpath(".//a[contains(.,'Home')]");		
		
	}
	public static class NewBillingAddressFormMap {
		public static final By newBillingAddressForm = By.xpath("//div[contains(@id,'BillingAddressSelector')]");
		public static final By continueBtn = By.xpath(".//button[contains(.,'Save and continue')]");
		public static final By saveAddressCheckBox = By.id("saveBillingAddress");
		public static final By chooseSaveAddress=By.xpath(".//a[contains(.,'saved address')]");
		public static final By sameAsShipAddress = By.xpath(".//input[@type='checkbox']");
		public static final By address = By.dataName("billToAddress-heading");
		public static final By edit = By.dataName("billToAddress-btn-edit");
		public static final By clear = By.dataName("billToAddress-btn-clear");
		public static final By successIcon = By.xpath("//div[@data-name='billToAddress-heading']//span[contains(@class,'glyphicon') and contains(@class,'text-success')]");
		public static final By ErrorIcon = By.xpath("//div[@data-name='billToAddress-heading']//span[@class='glyphicon glyphicon-remove-sign text-danger']");
		
	}
	
	public static class PaymentInfoPageMap {
		public static final By paymentInfoPage =  By.xpath("//main[@role='main']/div//div[2]");
		public static final By addPayment = By.xpath(".//a[contains(.,'Add')]");
		public static final By Existing = By.xpath(".//a[contains(.,'Visa')]");
		public static final By paymentcontainer = By.xpath("//div[@class='AddCreditCard ContentItem']");
		public static final By saveButton= By.xpath(".//button[contains(.,'Save')]");
		public static final By cancelButton= By.xpath(".//button[contains(.,'Cancel')]");
		public static final By chooseSavedAddress= By.xpath(".//a[contains(.,'Choose a saved address')]");
		public static final By editPaymentContainer = By.xpath("//legend[contains(.,'Billing')]/parent::fieldset");
		public static final ByT cardLink = ByT.xpath(".//a[contains(.,'%s')]");
		public static final By message  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
	}
	
	public static class PersonalInfoPageMap {
		public static final By personalInfoPage =  By.xpath("//div[contains(@id,'AccountPersonalInformation')]");
		public static final By email = By.dataName("email");
		public static final By firstName = By.dataName("firstName");
		public static final By lastName= By.dataName("lastName");
		public static final By zip = By.dataName("zip");
		public static final By gender = By.dataName("gender");
		public static final By dateOfBirth = By.dataName("dateOfBirth");
		public static final By save = By.xpath(".//button[@class='btn btn-block csa-btn']");
		public static final By cancel = By.xpath(".//button[@class='btn btn-block btn-default']");
	}

	
	public static class PaymentInfoFormMap {
		public static final By container = By.xpath("//div[contains(@id,'PaymentMethodSelector')]");
		public static final By nickName = By.xpath("//input[@data-name='cardNickName']|//input[@data-name='nickname']");
		public static final By paymentType =  By.xpath(".//select[contains(@id,'paymentType')]|.//select[@name='creditCardType']");
		public static final By cardNumber = By.dataName("cardNumber");
		public static final By expDate = By.xpath(".//input[contains(@id,'expirationDate')]|.//input[@name='expirationDate']");

		public static final By expYear = By.dataName("expirationDateYear");
		public static final By expMonth = By.dataName("expirationDateMonth");
		public static final By cardCsv = By.dataName("cardCsv");
		public static final By continueBtn = By.xpath(".//button[contains(.,'Save and continue')]");
		public static final By paymentBtn = By.id("paymentBtn");
		public static final By chooseSaveAddress = By.xpath("//a[contains(.,'Choose a saved credit card')]");
		public static final By successIcon = By.xpath("//div[@data-name='payment-heading']//span[contains(@class,'glyphicon') and contains(@class,'text-success')]");
		public static final By editButton = By.xpath("//div[@id='payment-heading']//button[@id='payment-btn']|//button[@data-name='payment-btn-edit']");
		public static final By SavePayment = By.xpath("//input[@type='checkbox' and contains(@data-bind,'saveCreditCardToProfile')]");
		public static final By ErrorIcon = By.xpath("//div[@id='payment-heading']//span[contains(@class,'glyphicon') and contains(@class,'text-danger')]");
		public static final By heading = By.dataName("payment-heading");
		public static final By makeDefault = By.xpath(".//input[contains(@data-bind,'setAsDefaultCard')]");
		public static final By securityCodeExplaination = By.xpath(".//a[contains(@data-bind,'showSecurityCodeExplanation')]");
		
	}
	public static class EditPaymentInfoFormMap{
		public static final By editPaymentContainer = By.xpath("//div[contains(@id,'EditCreditCard')]");
		public static final By nickName = By.id("nickname");
		public static final By expDateMonth=By.xpath("//select[contains(@id,'expirationDateMonth')]");
		public static final By expDateYear = By.xpath("//select[contains(@id,'expirationDateYear')]");
		public static final By billingAddressForm = By.xpath("//legend[contains(.,'Billing')]/parent::fieldset");
		public static final By makeDefault = By.xpath(".//input[contains(@data-bind,'setAsDefaultCard')]");
		public static final By cardNumberEnding = By.xpath(".//p[contains(text(),'Ending in')]");
		public static final By save = By.xpath(".//button[contains(.,'Save')]");
	}
	public static class AddPaymentInfoFormMap{
		public static final By addPaymentContainer = By.xpath("//div[@class='AddCreditCard ContentItem']");
		public static final By billingAddressForm = By.xpath(".//legend[contains(.,'Billing')]/parent::fieldset");
		public static final By save = By.xpath(".//button[contains(.,'Save') and not(contains(@name,'update'))]");
		public static final By makeDefaultCard = By.xpath(".//input[@type='checkbox']");
	}
	public static class OrderConfirmationPageMap{
		public static final By container = By.xpath("//div[contains(@id,'OrderConfirmation')]");

		public static final By orderProcessed = By.xpath(".//div/h2[contains(.,'Success!')]");
		public static final By thankYouForOrder = By.xpath(".//div/h3[contains(.,'Your order has been placed')]");
		public static final By orderNum = By.xpath(".//div/p[contains(.,'View order ')]");
		public static final By orderid = By.xpath(".//a");
		public static final By printOrder = By.xpath(".//a[contains(.,'Print order')]");
		public static final By register = By.xpath("//button[contains(.,'Register')]");
	}
	
	public static class OrderDetailsPageMap{
		public static final By container = By.xpath("//div[@class='AccountOrderDetail ContentItem']");
		public static final By shipToAddress = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[4]");
		public static final By billToaddress = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[7]");
		public static final By payment = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[8]");
		public static final By orderHeader = By.xpath("//div[contains(@id,'orderDetailsPage')]//h2[1]");
		public static final By orderStatus = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[1]");
		public static final By orderDate = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[2]");
		public static final By orderSite = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[3]");
		public static final By shippingMethod = By.xpath("//div[contains(@class,'col')][1]/div[@class='well']/p[5]");
		public static final By cartItemsTable = By.xpath(".//table[@summary='Product description, product image, price and quantity of each item in this order']");
		public static final By ordersummarycontainer = By.xpath(".//table[@summary='Product description, product image, price and quantity of each item in this order']");
		public static final ByT productByName = ByT.xpath(".//table[2]/tbody/tr[contains(.,'%s')]");
		public static final ByT productByIndex = ByT.xpath(".//table[2]/tbody/tr[%d]");
	}
	public static class BaseCreditCardItemMap{
		public static final ByT cartdItem = ByT.xpath("//strong[a[contains(.,'%s')]]/parent::td/parent::tr");
	}
	public static class PaymentItemMap{
		public static final By container = By.xpath("//div[contains(@id,'ViewCards')]");
		public static final By paymentMethodlabel=By.xpath(".//table/thead/tr/th[1]");
		public static final By expDatelabel=By.xpath(".//table/thead/tr/th[3]");
		public static final By nameOnCardlabel=By.xpath(".//table/thead/tr/th[4]");
		
		public static final ByT cartdItemRow = ByT.xpath("//a[contains(.,'%s')]/parent::th/parent::tr");
		public static final By paymentMethod=By.xpath(".//a[contains(@data-bind,'edit')]");
		public static final By paymentExpDate = By.xpath("./td[2]");
		public static final By paymentNameOnCard = By.xpath("./td[3]");
		public static final By remove = By.xpath("./td[4]");
		public static final By defaultLabel = By.xpath("//span[contains(.,'Default')]");
		
	}
	public static class SizeChartDialogMap{
		public static final By container=By.xpath("//div[@aria-labelledby='additionalInfoModalLabel']//div[@class='modal-dialog']");
		public static final By title=By.className("modal-title");
		public static final By sizeInfoWomensShirt = By.xpath("./div/div[2]/div[1]/table/tbody/tr[1]/td");
		public static final By sizeInfoMensShirt = By.xpath("./div/div[2]/div[2]/table/tbody/tr[1]/td");
		public static final By sizeInfoWomensShoes = By.xpath("./div/div[2]/div[3]/table/tbody/tr[1]/td");
		public static final By sizeInfoMensShoes = By.xpath("./div/div[2]/div[4]/table/tbody/tr[1]/td");
	}
	public static class OrderItemMap {
		public static final By submittedDateLabel = By.xpath("./td[2]");
		public static final By siteLabel = By.xpath("./td[1]");
		public static final By itemsLabel = By.xpath("./td[3]");
		public static final By orderIdLabel = By.xpath("./th[1]/a");
		public static final By statusLabel = By.xpath("./td[4]/span");
		public static final By viewLink = By.linkText("View");
		//public static final By orderId=By.xpath("./a");
	}

	public static class OrdersHistoryPageMap {
		public static final By ordersList = By.xpath("//div[@class='AccountOrderHistory ContentItem']");
		public static final By errorMessage = By.xpath(".//div[@class='alert alert-info']");
		public static final ByT itemByText = ByT.xpath(".//a[contains(@href,'%s')]");
		public static final ByT itemByIndex = ByT.xpath(".//tbody/tr[%d]");
	}
	
	public static class ViewOrderProductItemMap {
		public static final ByT itemByName = ByT.xpath(".//img[@alt='%1$s']/parent::a/parent::th/parent::tr|.//img[@alt='%1$s']/parent::td/parent::tr");
		public static final By productImage = By.xpath(".//img");
		public static final By nameLink = By.xpath("");
		public static final By skuValue = By.xpath("");
		public static final By qtyValue = By.xpath("");
		public static final By finishValue = By.xpath("");
		public static final By colorValue = By.xpath("");
		public static final By sizeValue = By.xpath("");
		public static final By siteValue = By.xpath("");
		public static final By priceValue = By.xpath("");
	}
	
	public static class RefinePanelMap{
		public static final By refinePanel= By.xpath("//div[@class='col-sm-4 col-lg-3']");//By.xpath("//div[@class='ContentSlotSecondary ContentItem']");
		public static final By byCategory = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Category')]/parent::div/parent::div");
		public static final By byPriceRange = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Price range')]/parent::div/parent::div|.//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Price Range')]/parent::div/parent::div");
		public static final By byBrand = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Brand')]/parent::div/parent::div");
		public static final By breadcrumbpanel = By.xpath(".//div[@class='Breadcrumbs ContentItem']");
		public static final By bySize = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Size')]/parent::div/parent::div");
		public static final By byColor = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Color')]/parent::div/parent::div");
		public static final ByT genericRefinement = ByT.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'%s')]/parent::div/parent::div");
		public static final By byWoodFinish = By.xpath(".//div[@class='ContentSlotSecondary ContentItem']//h3[contains(.,'Wood finish')]/parent::div/parent::div");
	}
	public static class RefinementFacetMap{
		public static final ByT itemByText = ByT.xpath(".//li/a[contains(.,'%s')]");
		public static final ByT itemByIndex = ByT.xpath(".//li[%d]/a");
		public static final By itemForSize = By.xpath(".//li/a");
		public static final By showMore = By.xpath(".//li/a[contains(.,'Show more')]");
		public static final By showLess = By.xpath(".//li/a[contains(.,'Show fewer')]");
		public static final By filter = By.xpath("//button[contains(.,'Filter')]");
		
	}
	
	public static class RefinementItemMap {
		public static final By countLabel = By.xpath("./span[2]");
		public static final By nameLabel = By.xpath("./span[1]");
	}
	public static class BreadcrumbItemMap {
		public static final ByT name = ByT.xpath(".//li[contains(.,'%s')]");
		public static final By close = By.xpath(".//span[@class='glyphicon glyphicon-remove pull-right']");
	}
	public static class BreadcrumbPanelMap{
		public static final ByT itemByText =ByT.xpath(".//li[contains(.,'%s')]");//  ByT.xpath(".//ol[@class='breadcrumb csa-breadcrumb']//li[contains(.,'%s')]");//
		public static final By header = By.xpath("//h3[contains(.,'Your selections')]|//h3[contains(.,'Your Selections')]");
		public static final ByT categoryLink =ByT.xpath(".//li/ol/li[contains(.,'%s')]/a");
		public static final By clearAll = By.xpath(".//a[contains(.,'Clear all')]");
	}
	public static class PriceRangeSelectPanelMap {
		public static final By priceRange = By.xpath(".//form[contains(@data-bind,'submitPriceRange')]");
		public static final By minPrice = By.dataName("priceMin");
		public static final By maxPrice = By.dataName("priceMax");
		public static final By apply = By.xpath(".//button[contains(.,'Apply')]");
		public static final By filter = By.xpath("//button[contains(.,'Filter')]");
	}

	public static class SignUpFormMap {
		//Labels
		public static final By emailLabel = By.xpath("//*[@data-name='emailAddress']/preceding-sibling::label");
		public static final By passwordLabel = By.xpath("//*[@data-name='password']/preceding-sibling::label");
		public static final By confirmPasswordLabel = By.xpath("//*[@data-name='confirmPassword']/preceding-sibling::label");
		public static final By firstNameLabel = By.xpath("//*[@data-name='firstName']/preceding-sibling::label");
		public static final By lastNameLabel = By.xpath("//*[@data-name='lastName']/preceding-sibling::label");
		public static final By zipcodeLabel = By.xpath("//*[@data-name='postalCode']/preceding-sibling::label");
		public static final By genderLabel = By.xpath("//*[@data-name='gender']/preceding-sibling::label");
		public static final By birthdayLabel = By.xpath("//*[@data-name='dateOfBirth']/preceding-sibling::label");
		//Textfields
		public static final By emailField = By.dataName("emailAddress");
		public static final By passwordField = By.dataName("password");
		public static final By confirmPassword = By.dataName("confirmPassword");
		public static final By firstNameField = By.dataName("firstName");
		public static final By lastNameField = By.dataName("lastName");
		public static final By zipcodeField = By.dataName("postalCode");
		public static final By genderField = By.dataName("gender");
		public static final By birthdayField = By.dataName("dateOfBirth");
		//public static final By saveButton = By.xpath("//button[contains(.,'Save')]");
		public static final By createButton = By.xpath(".//button[contains(.,'Create')]");
		public static final By cancelButton = By.xpath(".//button[contains(.,'Cancel')]");
		public static final By errorMessage  = By.xpath("//*[@id='toast-container']//div[@class='toast-message']");
		public static final By fieldErrorMessage  = By.xpath(".//div[@class='ws-errorbox']/p");
		public static final By seeBenfitsLink  = By.xpath("//a[contains(.,'See benefits')]");
	}
	public static class AccountBenefitsDialogMap {
		public static final By container = By.id("benefitsModal");
		public static final By modalBodyLabel=By.xpath(".//div[@class='modal-body']");
	}
	
	public static class YourProfilePageMap {
		public static final By container = By.xpath("//div[contains(@class,'AccountPersonalInformation')]");
		public static final By emailAddress = By.dataName("email");
		public static final By firstName=By.dataName("firstName");
		public static final By lastName=By.dataName("lastName");
		public static final By zip = By.dataName("zip");
		public static final By gender = By.dataName("gender");
		public static final By dob = By.dataName("dateOfBirth");
		public static final By cancel = By.xpath(".//button[contains(.,'Cancel')]");
		public static final By save = By.xpath(".//button[contains(.,'Save') and not(contains(@name,'update'))]");

	}
	public static class CreateAccountPageMap{
		public static final By container = By.xpath("//*[@role='main']");
		public static final By signupFormContainer = By.xpath("//div[contains(@id,'RegisterUser')]");
		public static final By create = By.xpath(".//button[contains(.,'Create')]");
	}
	
	public static class SitePreferenceDialogMap{
		public static final By container = By.xpath("//div[@aria-labelledby='sitePreferencesTitle']");
		public static final By save = By.xpath("//button[@name='update']");
		public static final By site = By.id("siteSelection");
		public static final By language = By.id("languageSelection");
	}
	public static class AddAddressFormMap{
		public static final By container = By.xpath("//div[@class='AddAddress ContentItem']");
		public static final By saveButton= By.xpath(".//button[contains(.,'Save')]");
		public static final By makeDefault = By.xpath(".//input[contains(@data-bind,'useAsDefaultShippingAddress')]");
	}
	
	public static class CheckoutProductItemMap{
		public static final By name = By.xpath("./td[1]/p[1]/strong");
	}
	public static class CheckoutProductListPanelMap{
		public static final ByT productItemByIndex = ByT.xpath(".//tbody/tr[%d]");
		public static final ByT productItemByText = ByT.xpath("");
		public static final By itemsCount = By.xpath("");
	}
	
	public static class SearchResultItemMap{
		public static final By itemprice = By.xpath(".//span/span");
	}
	
}

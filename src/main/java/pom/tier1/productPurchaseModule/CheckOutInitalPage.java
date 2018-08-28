package pom.tier1.productPurchaseModule;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInitalPage {
	
	WebDriver driver;

	public CheckOutInitalPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = ".//h1[contains(., 'Checkout')]")
	public WebElement checkoutPage;

	@FindBy(xpath = ".//table[contains(@class,'checkout_cart')]/tbody/tr[2]")
	public List<WebElement> numberOfRowsOfChecoutTable;

	@FindBy(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[1]/th[1]")
	public WebElement productColumnHeader;

	@FindBy	(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[1]/th[2]")
	public WebElement qualityColumnHeader;

	@FindBy	(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[1]/th[3]") 
	public WebElement priceColumnHeader;

	@FindBy	(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[1]/th[4]") 
	public WebElement totalColumnHeader;

	@FindBy	(xpath=".//a[contains(., 'Magic Mouse')]")
	public WebElement checkifMagicMouseExistOrnot;
	
	@FindBy	(xpath="//table[contains(@class,'checkout_cart')]/tbody/tr[2]/td[3]/form/input[1]")
	public WebElement quantitiyNumber;
	
	@FindBy	(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[2]/td[4]")
	public WebElement productprice;
	
	@FindBy	(xpath=".//table[contains(@class,'checkout_cart')]/tbody/tr[2]/td[5]")
	public WebElement totalprice;

	@FindBy	(xpath=".//a[contains(@class,'step2')]")
	public WebElement continueButton;
	
}

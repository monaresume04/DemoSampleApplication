package pom.tier1.productPurchaseModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckOutInfoPage {
	WebDriver driver;

	public CheckOutInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = ".//h1[contains(.,'Checkout')]")
	public WebElement pageName;
	
	@FindBy(xpath = ".//input[contains(@title,'billingemail')]")
	public WebElement email;
	
	@FindBy(xpath = ".//input[starts-with(@title,'billingfirstname')]")
	public WebElement  firstName;

	@FindBy(xpath = ".//input[starts-with(@title,'billinglastname')]")
	public WebElement lastName;

	@FindBy(xpath = ".//textarea[starts-with(@title,'billingaddress')]")
	public WebElement address;

	@FindBy(xpath = ".//input[starts-with(@title,'billingcity')]")
	public WebElement city;

	@FindBy(xpath = ".//input[starts-with(@title,'billingstate')]")
	public WebElement undefined;

	@FindBy(xpath = ".//select[starts-with(@title,'billingcountry')]")
	public WebElement country;

	@FindBy(xpath = ".//input[starts-with(@title,'billingpostcode')]")
	public WebElement postalCode ;

	@FindBy(xpath = ".//input[starts-with(@title,'billingphone')]")
	public WebElement phone ;

	@FindBy(xpath = ".//*[@id='shippingSameBilling']")
	public WebElement sameAsBillingAddressCheckBox;

	@FindBy(xpath = ".//table[contains(@class,'wpsc_checkout_table table-4' )]/tbody/tr[2]/td[2]/span/span")
	public WebElement  totalShippingValue ;

	@FindBy(xpath = ".//table[contains(@class,'wpsc_checkout_table table-4' )]/tbody/tr[3]/td[2]/span/span")
	public WebElement itemCost;

	@FindBy(xpath = ".//table[contains(@class,'wpsc_checkout_table table-4' )]/tbody/tr[3]/td[2]/span/span")
	public WebElement totalTax;

	@FindBy(xpath = ".//table[contains(@class,'wpsc_checkout_table table-4' )]/tbody/tr[5]/td[2]/span/span")
	public WebElement totalPrice;

	@FindBy(xpath = ".//input[starts-with(@class,'make_purchase')]")
	public WebElement purchaseButton;
	
	public void populateCheckoutfieldInfo(String FirstName, String LastName, String Address,
			String city ,String state,String country ,String PostalCode ,String Phone ,String email){
		
		this.email.sendKeys(email);
		this.firstName.sendKeys(FirstName);
		this.lastName.sendKeys(LastName);
		this.address.sendKeys(Address);
		this.city.sendKeys(city);
		this.undefined.sendKeys(state);
		Select countrySelect =new Select(this.country);
		countrySelect.selectByVisibleText(country);
		this.postalCode.sendKeys(PostalCode);
		this.phone.sendKeys(Phone);
		this.sameAsBillingAddressCheckBox.click();
	}

}

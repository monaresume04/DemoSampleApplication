package pom.tier1.productPurchaseModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = ".//a[contains(text(),'Product Category')]")
	public WebElement menuItemProductCategory;

	@FindBy(xpath = ".//a[starts-with(text(),'Home')]")
	public WebElement menuItemHome;

	@FindBy(xpath = ".//a[contains(text(),'All Product')]")
	public WebElement menuItemAllProduct;

	@FindBy(xpath = ".//a[contains(text(),'Accessories')]")
	public WebElement submenuItemAccessories;

	}

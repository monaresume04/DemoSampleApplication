package pom.tier1.productPurchaseModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccessoriesSearchPage {
	
	WebDriver driver;

	public AccessoriesSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = ".//a[contains(text(),'Magic Mouse')]")
	public WebElement magicMouseLink;

	@FindBy(xpath = ".//form[contains(@action , 'magic-mouse')]/div[2]/div[1]/span/input")
	public WebElement magicMouseAddToCardButton;

	@FindBy(xpath = ".//a[contains(., 'Checkout')]")
	public WebElement checkOutButton;

}

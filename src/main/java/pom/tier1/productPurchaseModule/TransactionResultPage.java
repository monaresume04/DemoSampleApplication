package pom.tier1.productPurchaseModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionResultPage {
	WebDriver driver;

	public TransactionResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath=".//h1[contains(.,'Transaction Results')")
	public WebElement transactionPage;
	@FindBy(xpath=".//div[contains(@class,'wpsc-transaction-results-wrap')]/p[1]")
	public WebElement containofFirstPara;
	@FindBy(xpath=".//div[contains(@class,'wpsc-transaction-results-wrap')]/p[2]")
	public WebElement containofSecondPara;
	@FindBy(xpath=".//div[contains(@class,'wpsc-transaction-results-wrap')]/p[3]")
	public WebElement containofThirdPara;
}

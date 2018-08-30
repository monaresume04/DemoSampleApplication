package testScript.tier1.productPurchaseModule;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import coreModules.TestContext.TestContext;
import coreModules.dataProvider.BaseDataProvider;
import pom.tier1.productPurchaseModule.AccessoriesSearchPage;
import pom.tier1.productPurchaseModule.CheckOutInfoPage;
import pom.tier1.productPurchaseModule.CheckOutInitalPage;
import pom.tier1.productPurchaseModule.HomePage;
import pom.tier1.productPurchaseModule.TransactionResultPage;
import utility.Config;
import utility.HelperClass;
import utility.beforeTestActivityDetails;

public class TestCaseSample extends beforeTestActivityDetails {



	@Test(dataProvider = "testDataProvider", dataProviderClass = BaseDataProvider.class)
	public void test(String TestCaseName, String FirstName, String LastName, String Address, String city, String state,
			String country, String PostalCode, String Phone, String email) throws InterruptedException {

		WebDriver webdriver = TestContext.get().getDriver();
		webdriver.get(Config.getString("environmentConfig.applicationloginLink")); // Navigated to
																					// http://store.demoqa.com/
		webdriver.manage().window().maximize(); // maximize window
		// Create page object model instance for home page
		HomePage homePage = new HomePage(webdriver);
		Actions action = new Actions(webdriver);
		System.out.println(homePage.menuItemProductCategory.getText());
		action.moveToElement(homePage.menuItemProductCategory).build().perform();
		HelperClass.waitForElement(webdriver, homePage.submenuItemAccessories, 30); // wait for Accessorie menu item to
																					// display
		homePage.submenuItemAccessories.click(); // click on submenu

		// NAvigated to search screen
		AccessoriesSearchPage accessoriesSearchPage = new AccessoriesSearchPage(webdriver);
		if (!HelperClass.isFoundOnPage(accessoriesSearchPage.magicMouseLink)) {
			Assert.fail("Magic mouse link does not exists  ");
		}
		// scroll till magic mouse Add to cart button
		((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();",
				accessoriesSearchPage.magicMouseAddToCardButton);
		if (!HelperClass.isFoundOnPage(accessoriesSearchPage.magicMouseAddToCardButton)) {
			Assert.fail("Add button for magic mause does not exist"); // Test will fail if add but for magic mouse is
																		// not visible or disable
		}
		accessoriesSearchPage.magicMouseAddToCardButton.click();
		accessoriesSearchPage.checkOutButton.click();
		// validate check out page
		CheckOutInitalPage checkoutInitailPage = new CheckOutInitalPage(webdriver);
		HelperClass.waitForElement(webdriver, checkoutInitailPage.checkoutPage, 60);
		Assert.assertEquals(checkoutInitailPage.checkoutPage.getText(), "Checkout");
		// check how many rows (count = 1)
		Assert.assertEquals(checkoutInitailPage.numberOfRowsOfChecoutTable.size(), 1, "More then one product in cart");
		// validate magic mouse row
		Assert.assertEquals(checkoutInitailPage.checkifMagicMouseExistOrnot.isDisplayed(), true,
				"Checkout does not Contains Magic Mouse Link");
		Assert.assertEquals(checkoutInitailPage.quantitiyNumber.getAttribute("value").toString(), new String("1"),
				"Checkout Quantity is more equal to one");
		Assert.assertEquals(checkoutInitailPage.productprice.getText().toString(), new String("$150.00"),
				"validate product price");
		Assert.assertEquals(checkoutInitailPage.totalprice.getText().toString(), new String("$150.00"),
				"validate total price");
		checkoutInitailPage.continueButton.click();
		// Step-4 Navigating to
		CheckOutInfoPage checkOutInfoPage = new CheckOutInfoPage(webdriver);
		HelperClass.waitForElement(webdriver, checkOutInfoPage.pageName, 40);
		((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();", checkOutInfoPage.email);
		HelperClass.waitForElement(webdriver, checkOutInfoPage.email, 40);
		checkOutInfoPage.populateCheckoutfieldInfo(FirstName, LastName, Address, city, state, country, PostalCode,
				Phone, email);
		checkOutInfoPage.purchaseButton.click();

		TransactionResultPage transactionPage = new TransactionResultPage(webdriver);
		HelperClass.waitForElement(webdriver, transactionPage.pageName, 40);
		Assert.assertEquals(
				transactionPage.containofFirstPara.getText().equals(
						"Thank you, your purchase is pending. You will be sent an email once the order clears."),
				true, "validate order placed success message");

	}
}

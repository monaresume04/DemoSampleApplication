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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import coreModules.TestContext.TestContext;
import coreModules.dataProvider.BaseDataProvider;
import coreModules.reportProvider.ExtentManager;
import pom.tier1.productPurchaseModule.AccessoriesSearchPage;
import pom.tier1.productPurchaseModule.CheckOutInitalPage;
import pom.tier1.productPurchaseModule.HomePage;
import utility.Config;
import utility.HelperClass;

public class TestCaseSample {
	
	private static ExtentReports extent;
    private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
    
    
	@BeforeSuite
	public void loadConfigFile() {
		Config.getInstance(); //creating instance of Config file .
		extent = ExtentManager.getInstance();
		
	}
	
	@BeforeTest
	public synchronized  void initDriver() {
		 ExtentTest parent = extent.createTest(getClass().getName());
	        parentTest.set(parent);
		TestContext.init(); //initialized testContext 
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumchromeDriver\\chromedriver.exe");
		WebDriver webdriver = new ChromeDriver();
		TestContext.get().setDriver(webdriver);
	}
	@AfterTest
	public synchronized  void afterTest( ITestResult result) {
		TestContext.get().getDriver().close();
		TestContext.get().getDriver().quit();
		 if (result.getStatus() == ITestResult.FAILURE)
	            ((ExtentTest) test.get()).fail(result.getThrowable());
	        else if (result.getStatus() == ITestResult.SKIP)
	            ((ExtentTest) test.get()).skip(result.getThrowable());
	        else
	            ((ExtentTest) test.get()).pass("Test passed");

	        extent.flush();
	}
	@Test(dataProvider ="testDataProvider", dataProviderClass = BaseDataProvider.class)
	  public void test( String TestcaseName,String FirstName, String LastName) throws InterruptedException {
		WebDriver webdriver=TestContext.get().getDriver();
		 webdriver.get(Config.getString("environmentConfig.applicationloginLink"));
		 webdriver.manage().window().maximize();
	      HomePage homePage= new HomePage(webdriver);
	      Actions action = new Actions(webdriver);
	      System.out.println(homePage.menuItemProductCategory.getText());
	      action.moveToElement(homePage.menuItemProductCategory).build().perform();
	      HelperClass.waitForElement(webdriver, homePage.submenuItemAccessories,30);
	      homePage.submenuItemAccessories.click();
	      AccessoriesSearchPage accessoriesSearchPage = new AccessoriesSearchPage(webdriver);
	      if(!HelperClass.isFoundOnPage(accessoriesSearchPage.magicMouseLink)) {
	    	  Assert.fail("Magic mouse link does not exists  ");
	      }
	      ((JavascriptExecutor)webdriver).executeScript("arguments[0].scrollIntoView();", accessoriesSearchPage.magicMouseAddToCardButton);
	      if(!HelperClass.isFoundOnPage(accessoriesSearchPage.magicMouseAddToCardButton)) {
	    	  Assert.fail("Add button for magic mause does not exist");
	      }
	      accessoriesSearchPage.magicMouseAddToCardButton.click();
	      
	      accessoriesSearchPage.checkOutButton.click();
	      //validate check out page 
	      CheckOutInitalPage checkoutInitailPage =new CheckOutInitalPage(webdriver);
	      HelperClass.waitForElement(webdriver, checkoutInitailPage.checkoutPage,40);
	      Assert.assertEquals(checkoutInitailPage.checkoutPage.getText(),"Checkout");
	      //check how many rows (count = 1)
	      Assert.assertEquals(checkoutInitailPage.numberOfRowsOfChecoutTable.size(),1 ,"More then one product in cart");
	      //validate magic mouse row 
	      Assert.assertEquals(checkoutInitailPage.checkifMagicMouseExistOrnot.isDisplayed(), true ,"Checkout does not Contains Magic Mouse Link");
	      Assert.assertEquals(checkoutInitailPage.quantitiyNumber.getAttribute("value").toString(), new String("1") ,"Checkout Quantity is more equal to one");
	      Assert.assertEquals(checkoutInitailPage.productprice.getText().toString(), new String("$150.00") ,"Checkout Quantity is more then one");
	      Assert.assertEquals(checkoutInitailPage.totalprice.getText().toString(), new String("$150.00") ,"Checkout Quantity is more then one");
	      
	      checkoutInitailPage.continueButton.click();
	  }
}

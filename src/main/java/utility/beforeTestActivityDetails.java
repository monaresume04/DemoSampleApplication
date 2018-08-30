package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import coreModules.TestContext.TestContext;

public class beforeTestActivityDetails {
	
	@BeforeSuite(alwaysRun = true)
	public void loadConfigFile() {
		Config.getInstance(); // creating instance of Config file .

	}

	@BeforeTest(alwaysRun = true)
	public synchronized void initDriver() {
		TestContext.init(); // initialized testContext
		// create chrome driver instance .
		System.setProperty("webdriver.chrome.driver", "C:\\seleniumchromeDriver\\chromedriver.exe");
		WebDriver webdriver = new ChromeDriver();
		TestContext.get().setDriver(webdriver);
	}

	@AfterTest(alwaysRun = true)
	public synchronized void afterTest() {
		TestContext.get().getDriver().close();
		TestContext.get().getDriver().quit();

	}

	
}

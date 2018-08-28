package coreModules.TestContext;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestContext {
	private static final ThreadLocal<TestContext> context = new ThreadLocal<TestContext>();

	
	public  static TestContext get() {
		return context.get();
	}
	
	public static TestContext init() {
		TestContext testContext= new TestContext();
		context.set(testContext);
		return testContext;
	}
	
	private WebDriver driver;
	private String testcaseName;
	private String teststatus;
	private Assert testAssert;


	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getTestcaseName() {
		return testcaseName;
	}

	public void setTestcaseName(String testcaseName) {
		this.testcaseName = testcaseName;
	}

	public String getTeststatus() {
		return teststatus;
	}

	public void setTeststatus(String teststatus) {
		this.teststatus = teststatus;
	}

	public Assert getTestAssert() {
		return testAssert;
	}

	public void setTestAssert(Assert testAssert) {
		this.testAssert = testAssert;
	}

	
	
	
}

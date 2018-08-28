package utility;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {

	public static boolean isFoundOnPage(WebElement elements) {
		boolean display = true;
		try {
			display = elements.isDisplayed();
		} catch (NoSuchElementException e) {
			display = false;
		}
		return display;
	}

	public static void waitForElement(WebDriver driver, final WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return element.isDisplayed();
			}
		});
	}
	public static boolean isEnableOnPage(WebElement elements) {
		boolean display = true;
		try {
			display = elements.isEnabled();
		} catch (NoSuchElementException e) {
			display = false;
		}
		return display;
	}
}

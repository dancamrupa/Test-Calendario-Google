package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumWebDriver {

	public static WebDriver driver;
	
	public static SeleniumWebDriver ChromeWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-infobars");

		driver = new ChromeDriver(options);
		return new SeleniumWebDriver();
	}
	
	public static WebDriver on(String url) {
		driver.get(url);
		return driver;
	}
}
package org.academy.trainer.login;

import org.academy.trainer.webUI.Wvf_Trainer_UI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

	public static WebDriver driver;
	public static WebElement element;
	public static By privacyPage = By.xpath("//h1[text()='Your connection is not private']");
	public static By advancedBtn = By.xpath("//button[contains(text(),'Advanced')]");
	public static By unsafeLink = By.xpath("//a[@id='proceed-link']");
	public static By usernameFloat=By.xpath("//*[text()='Email or Username *']");
	public static By passwordFloat=By.xpath("//*[text()='Password *']");
	public static By userName=By.xpath("//input[@id='username']");
	public static By password=By.xpath("//input[@id='password']");
	public static By btnLogin=By.xpath("//input[@id='login-submit']");
	public static By homeLogo=By.xpath("(//a)[1]");
	public static By LoginError=By.xpath("//*[contains(text(),'Invalid Username/Password')]");
	public static Actions actions;
	public static boolean errorNotication = false;

	/* Function Name: lunchBrowser();
	 * Purpose: This function is using - lunching the Chrome browser
	 */
	public static void lunchBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("headless");
		driver = new ChromeDriver(opt);
		//		driver=new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Chrome browser lunched!");
		
	}
	/* Function Name: UserLogin();
	 * Purpose: This function is using - navigation the URL and login the credentials
	 */
	public static void LoginAsTrainer() throws InterruptedException {
		driver.get("https://academy.winvinayafoundation.org/");
		System.out.println("URL openned!");
		driver.findElement(usernameFloat).click();
		driver.findElement(userName).sendKeys(Wvf_Trainer_UI.loginID.getText());
		driver.findElement(passwordFloat).click();
		driver.findElement(password).sendKeys(new String(Wvf_Trainer_UI.password.getPassword()));
		driver.findElement(btnLogin).click();
		System.out.println("Login button clicked!");
		Thread.sleep(2000);
		element = driver.findElement(homeLogo);
		actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		System.out.println("Home Logo clicked!");
	}

	public static void trainerLogin() throws InterruptedException {
		lunchBrowser();
		LoginAsTrainer();
	}
}

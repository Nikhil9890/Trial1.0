package com.TestingShastraLogic;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class KeyWord {
	public static WebDriver driver;
	static WebDriverWait wait;

	public static WebElement getelement(String Locatortype, String LocatorValue) {
		if (Locatortype.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(LocatorValue));
		}
		if (Locatortype.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(LocatorValue));
		}
		if (Locatortype.equalsIgnoreCase("cssselector")) {
			return driver.findElement(By.cssSelector(LocatorValue));
		}
		if (Locatortype.equalsIgnoreCase("className")) {
			return driver.findElement(By.className(LocatorValue));
		}
		if (Locatortype.equalsIgnoreCase("partialLinkText")) {
			return driver.findElement(By.partialLinkText(LocatorValue));
		}
		if (Locatortype.equalsIgnoreCase("name")) {
			return driver.findElement(By.name(LocatorValue));
		} else
			return null;
	}

	public static List<String> getTexts(String LocatorType, String LocatorValue) {
		List<WebElement> element = new ArrayList<WebElement>();
		List<String> text = new ArrayList<String>();
		if (LocatorType.equalsIgnoreCase("ID")) {
			element = driver.findElements(By.id(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElements(By.xpath(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElements(By.cssSelector(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("linkText")) {
			element = driver.findElements(By.linkText(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("name")) {
			element = driver.findElements(By.name(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("partialLinkText")) {
			element = driver.findElements(By.partialLinkText(LocatorValue));
		}
		for (WebElement str : element) {
			text.add(str.getText());
		}
		return text;
	}
	
	public static void presenceofelement(By element) {
		wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(10));
		wait.pollingEvery(Duration.ofSeconds(1));
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
	}

	public static void alert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static Actions mouseAction() {
		Actions action = new Actions(driver);
		return action;
	}

	public void scrolldown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)"); // roll till bottom
	}

	@Parameters("browser-name")
	public static void openbrowser(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		if (browsername.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		if (browsername.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else {
			System.err.println("Invalid Browser");
		}
	}

	public static void launchurl(String webaddress) {
		driver.get(webaddress);
	}

	public static void windowmanage() {
		driver.manage().window().maximize();
	}

	public static void takepngscreenshot_savedat(String location) {
		Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(s.getImage(), "png", new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void rightclickonwebelement_OR_anywere(String rightclick) {
		Actions action=new Actions(driver);
		if(rightclick.isEmpty()) {
		action.contextClick();	
		}else {
		WebElement list=driver.findElement(By.xpath(rightclick));
		action.contextClick(list);
		}
	}

	public static void selectfromdropdown(String xpath) {
		WebElement list = driver.findElement(By.xpath(xpath));
		Select sect = new Select(list);
	}
}

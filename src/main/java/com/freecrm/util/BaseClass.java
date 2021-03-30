package com.freecrm.util;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.freecrm.objectrepository.OR;
import com.freecrm.pages.CompaniesPage;
import com.freecrm.pages.DealsPage;
import com.freecrm.pages.LoginPage;

public class BaseClass extends OR {
	public static WebDriver driver;
	public static FileInputStream f;
	public static Properties p;
	public XlsReader x = new XlsReader();
	public LoginPage lp;
	public CompaniesPage cp;
	public DealsPage dp;

	public BaseClass() {
		try {
			f = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Config/config.property");
			p = new Properties();
			p.load(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void openApp() {
		initialize("chrome");
		lp = new LoginPage();
		cp = new CompaniesPage();
		dp = new DealsPage();
	}
	
	public static void initialize(String browser) {
		System.out.println(System.getProperty("user.dir"));
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("ff")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/test/resources/Drivers/IEDrvierServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(p.getProperty("url"));
	}

	@AfterMethod
	public void closeApp() {
		try {
			if (driver.findElements(By.xpath(logOutSettX)).size() > 0) {
				driver.findElement(By.xpath(logOutSettX)).click();
				driver.findElement(By.xpath(Logoutbtnx)).click();
			}
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object[][] getData(String sheetName, String tcName, XlsReader x) {
		int tcStartRow = 0;
		while (!x.getCellData(sheetName, tcStartRow, 0).equals(tcName)) {
			tcStartRow++;
		}
		System.out.println(tcStartRow);

		int colStartRow = tcStartRow + 1;
		int cols = 0;
		while (!x.getCellData(sheetName, colStartRow, cols).equals("N")) {
			cols++;
		}
		System.out.println(cols);

		int dataStartRow = tcStartRow + 2;
		int rows = 0;
		HashMap<String, String> map;
		while (!x.getCellData(sheetName, dataStartRow + rows, 0).equals("N")) {
			rows++;
		}
		
		Object[][] data = new Object[rows][1];
		int index = 0;
		for (int rNum = dataStartRow; rNum < dataStartRow + rows; rNum++) {
			map = new HashMap();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = x.getCellData(sheetName, colStartRow, cNum);
				System.out.println(key);
				String value = x.getCellData(sheetName, rNum, cNum);
				System.out.println(value);
				map.put(key, value);
			}
			data[index][0] = map;
			index++;
		}
		return data;
	}

	public static void selectDropLooklike(WebElement dropdown, String value, String tag) {
		try {
			dropdown.click();
			Thread.sleep(2000);
			List<WebElement> items = driver.findElements(By.tagName(tag));
			System.out.println(items.size());
			for (WebElement item : items) {
				System.out.println(item.getText());
				if (item.getText().equals(value)) {
					item.click();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.hcl.main;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Cookie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HCLDemowReportTestcasesLogger {
	static{
	
		System.setProperty("Log4j.ConfigurationFile", "C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\src\\test\\java\\com\\hcl\\main\\log4j2.xml");
	}
	final Logger logger = LogManager.getLogger(HCLDemowReportTestcasesLogger.class);

	public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    WebDriver driver;
 
    @BeforeClass
    public void setup() throws InterruptedException, IOException{
    	
		logger.info("Setting up the test environment");

        sparkReporter = new ExtentSparkReporter("C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\Report\\HCLTechReportfinal.html");
        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);  
        System.setProperty("webdriver.chrome.driver",
    			"C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\Drivers\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.hcltech.com");	
        logger.info("Test environment setup complete");     
    }
    
   
@Test(priority = 1)
public void MenuTestCase() throws InterruptedException {

	extentTest = extentReport.createTest("Test Case 1 - Menu");
    
    extentTest.log(Status.INFO, "Navigated to Menu Page");
    logger.info("Navigated to Menu Page");


    
	//menu
	for(int i=1; i<7; i++)
    {
    	WebElement menu = driver.findElement(By.xpath("//*[@id='block-mainnavigationbt']/ul/li[" + i + "]"));        	

    	System.out.println("Menu : " + menu.getText());
        logger.debug("Menu item: " + menu.getText());


        // Hover over the menu
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();

        // Find all submenu items under the current menu
        List<WebElement> submenuItems = menu.findElements(By.xpath(".//following-sibling::ul//a"));

        // Print each sub-menu item's text
        int index = 1;
        for (WebElement item : submenuItems) {
//            System.out.println(index + ". " + item.getText());
//            System.out.println(item.getText());
        	
            logger.debug("Submenu item: " + item.getText());
            index++;
        }
        System.out.println("Menu Completed : " + i +" with size : "+ submenuItems.size());
        System.out.println("===============================================================================");
        logger.info("Menu Completed: " + i + " with size: " + submenuItems.size());
    }
    extentTest.log(Status.PASS, "Menu test case passed");
    logger.info("Menu test case passed");

    Thread.sleep(2000);
}


@Test(priority = 2)
public void LoginTestCase() throws IOException, InterruptedException {
    extentTest = extentReport.createTest("Test Case 2 - Login");
    
    extentTest.log(Status.INFO, "Navigated to ContactUs Page");
    
    logger.info("Navigated to ContactUs Page");
    


	WebElement menu1 = driver.findElement(By.xpath("//*[@id=\"block-mainnavigationbt\"]/ul/li[7]/a"));
	Actions action = new Actions(driver);
	action.moveToElement(menu1).click().build().perform();

	Properties properties;

	properties = new Properties();
	FileInputStream fis = new FileInputStream(
			"C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\src\\test\\java\\com\\hcl\\main\\Configuration.properties");
	properties.load(fis);

	String name = properties.getProperty("Name");
	String mail = properties.getProperty("Email");
	String org = properties.getProperty("Org");
	String phone = properties.getProperty("Phone");
	String txtmessage = properties.getProperty("Txtmessage");
	String country = properties.getProperty("Country");
	String jobtitle = properties.getProperty("JobTitle");
  
	    //^[A-Za-z]+\\s[A-Za-z]+$+
		if (!name.matches("^[A-Za-z]+(\s[A-Za-z]+){1,2}$")) {
            logger.error("Invalid name: " + name);
			return;
		}

		if (!mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            logger.error("Invalid email: " + mail);
			return;
		}

		if (!org.matches("[A-Za-z0-9 ]+")) {
            logger.error("Invalid Organization: " + org);
			return;
		}

		if (!phone.matches("\\d{10}")) {
            logger.error("Invalid phone number: " + phone);
			return;
		}

		if (!country.matches("[A-Za-z ]+")) {
            logger.error("Invalid country: " + country);
			return;
		}

		if (txtmessage.isEmpty()) {
            logger.error("Text message cannot be empty.");
			return;
		}

		if (!jobtitle.matches("[A-Za-z ]+")) {
            logger.error("Invalid job title: " + jobtitle);
			return;
		}
   
		extentTest.log(Status.INFO, "Entering Name: Vedant Bob smith");
		driver.findElement(By.xpath("//*[@id=\"edit-full-name\"]")).sendKeys(name);

		extentTest.log(Status.INFO, "Entering Email id");
		driver.findElement(By.xpath("//*[@id=\"edit-email-address\"]")).sendKeys(mail);

		extentTest.log(Status.INFO, "Entered Organization : HCLTech ");
		driver.findElement(By.xpath("//*[@id=\"edit-organization\"]")).sendKeys(org);

		extentTest.log(Status.INFO, "Entered phone no ");
		driver.findElement(By.xpath("//*[@id=\"edit-phone\"]")).sendKeys(phone);
  
		// Country
		// *[@id="edit-country"]
		extentTest.log(Status.INFO, "Entered country ");
		WebElement dropdown1 = driver.findElement(By.name("country"));
		Select s1 = new Select(dropdown1);
		s1.selectByVisibleText(country);
		Thread.sleep(2000);

		// Job title
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement dropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-designation")));
        Select s2 = new Select(dropdown2);
        s2.selectByVisibleText(jobtitle);
		Thread.sleep(2000);
		// WebElement dropdown2=driver.findElement(By.id("edit-designation-error"));
		extentTest.log(Status.INFO, "Entered Job title");
//		WebElement dropdown2 = driver.findElement(By.id("designation"));
////		WebElement dropdown2 = driver.findElement(By.xpath("//*[@id=\"edit-designation\"]"));
//		Select s2 = new Select(dropdown2);
//		s2.selectByVisibleText(jobtitle);
		
		
		extentTest.log(Status.INFO, "Entered text message");
		driver.findElement(By.xpath("//*[@id=\"edit-message-comments\"]")).sendKeys(txtmessage);
		Thread.sleep(2000);

		// file upload
		extentTest.log(Status.INFO, "Uploaded file");

		WebElement uploadbtn = driver.findElement(By.name("files[upload_multifile][]"));
		uploadbtn.sendKeys(
				"C:\\Users\\vedantsantosh.kajre\\OneDrive - HCL TECHNOLOGIES LIMITED\\Documents\\DemoWord.docx");
		Thread.sleep(2000);

		//privacy
//		WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"edit-privacy-policy\"]"));
//		WebElement checkbox = driver.findElement(By.name("privacy_policy"));
//		checkbox.click();
//		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.name("privacy_policy"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", checkbox);


		//Submit button
        //*[@id="edit-actions-submit"]
		extentTest.log(Status.INFO, "Clicked Submit Button");
		WebElement submitbtn = driver.findElement(By.id("edit-actions-submit"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(submitbtn).click().build().perform();
		Thread.sleep(4000);

		extentTest.log(Status.PASS, " Login test case passed");
        logger.info("Login test case passed");
		Thread.sleep(2000);
}


@Test(priority = 3)
public void ScreenShotTestCase() throws IOException, InterruptedException {
    extentTest = extentReport.createTest("Test Case 3 - ScreenShot");
    
    extentTest.log(Status.INFO, "Capturing ScreenShot");
    logger.info("Capturing ScreenShot");


    
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File des = new File(
			"C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\ScreenShots\\Sreenshot1.png");
	FileHandler.copy(src, des);
	Thread.sleep(2000);

    extentTest.log(Status.PASS, "Screenshot test case passed");
    logger.info("Screenshot test case passed");

    Thread.sleep(2000);
}


@Test(priority = 4)

public void AboutusTestCase() throws IOException, InterruptedException {
	extentTest = extentReport.createTest("Test Case 4 - About Us");

	extentTest.log(Status.INFO, "Navigated to AboutUs Page");
    logger.info("Navigated to AboutUs Page");

    
	FileWriter fileWriter = new FileWriter("C:\\Users\\vedantsantosh.kajre\\eclipse-workspace\\java\\UseCase\\OutputFiles\\output.txt", true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
   
    WebElement aboutmenu = driver.findElement(By.xpath("//*[@id=\"block-mainnavigationbt\"]/ul/li[4]"));
	Actions act = new Actions(driver);
	act.moveToElement(aboutmenu).perform();

	WebElement submenu = driver.findElement(By.xpath("//*[@id=\"block-mainnavigationbt\"]/ul/li[4]/ul/li[1]/a"));
	submenu.click();
	Thread.sleep(2000);

    for (int i = 1; i <= 7; i++) {
        WebElement button = driver.findElement(
            By.xpath("//*[@id='our-history']/div/div/div/div/div/div/div[2]/div/div/div[" + i + "]/div/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).click().build().perform();
        Thread.sleep(2000);

        WebElement div = driver.findElement(
            By.xpath("//*[@id=\"our-history\"]/div/div/div/div/div/div/div[3]/div/div/div[" + i
                    + "]/div/div/div/div/div/div/div/div"));

        String year = div.findElement(By.className("year")).getText();
        String textbox = div.findElement(By.className("text")).getText();
        System.out.println(year);
        System.out.println(textbox);
        System.out.println("-------------");

        // Generating to text file
        bufferedWriter.write(year + " : " + textbox);
        bufferedWriter.newLine();
    }

    for (int i = 8; i <= 9; i++) {
    	WebElement button = driver.findElement(
            By.xpath("//*[@id='our-history']/div/div/div/div/div/div/div[2]/div/div/div[" + i + "]/div/button"));
            Actions actions = new Actions(driver);
            actions.moveToElement(button).click().build().perform();
            Thread.sleep(2000); 

            WebElement div;
            if (i == 8) {
                div = driver.findElement(By.xpath("//*[@id=\"slick-slide100\"]/div/div"));
            } else {
                div = driver.findElement(By.xpath("//*[@id=\"slick-slide110\"]/div/div"));
            }

            String year = div.findElement(By.className("year")).getText();
            String textbox = div.findElement(By.className("text")).getText();
            System.out.println(year);
            System.out.println(textbox);
            System.out.println("-------------");

            // Generating to text file
            bufferedWriter.write(year + " : " + textbox);
            bufferedWriter.newLine();
        }
    
    bufferedWriter.close();
    Thread.sleep(2000);


	driver.navigate().to("https://www.hcltech.com");
	Thread.sleep(2000);

	extentTest.log(Status.PASS, "About Us est case Passed",
			MediaEntityBuilder.createScreenCaptureFromPath(screenshot(driver)).build());
    logger.info("AboutUs test case passed");

}


private String screenshot(WebDriver driver) throws IOException {
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File dest = new File(
			"C:\\\\Users\\\\vedantsantosh.kajre\\\\eclipse-workspace\\\\java\\\\UseCase\\\\ScreenShots\\\\HCLTechreport.png");
	Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	return dest.getAbsolutePath();
}


@AfterClass
public void tearDown() {
    driver.quit();
    extentReport.flush();
}
}

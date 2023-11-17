package com.mb.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.mb.StepDef.Hook;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseProperty {

	public static WebDriver driverMBBAT;
	public static WebDriverWait wait;

	public static Connection con;
	public static Statement st;
	public static ResultSet res;

	public static Connection con1;
	public static Statement st1;
	public static ResultSet res1;

	public static Connection con2;
	public static Statement st2;
	public static ResultSet res2;

	public static String Server_Name = "";
	public static String DB_Name = "";
	public static String Instance = "";
	public static String DB_Username = "";
	public static String DB_Password = "";
	public static String companyUrl = "";

	public static String indexurl = "";
	public static String defaulturl = "";

	public static String directory = "";
	public static String userID = "";

	public static String activateUrl = "";
	public static String afRowID = "";
	public static String afRowID2 = "";
	public static String companyID = "";
	public static String companyVersion = "";
	public static String dbNameAfRowId = "";
	public static String dbcount = "";
	public static String dbusercount = "";
	public static String dbmultiusercount = "";

	public static ExtentReports extent;
	public static ExtentTest scenariodef;
	public static ExtentTest features;

	public static PrintWriter pw;
	public ArrayList<String> reportStatus = new ArrayList<String>();
	public static String ver = "";

	static String sysprojectpath = System.getProperty("user.dir");

	public static String Build1 = "";
	public static String fullBuildVersion = "";

	public static String reportName = Build1;

	public WebDriver initializedriver() throws IOException {

		System.out.println("entered the method");
//		WebDriverManager.chromedriver().setup();
//		driverMBBAT = new ChromeDriver();
//		driverMBBAT.manage().window().maximize();
//		driverMBBAT.manage().deleteAllCookies();
//		driverMBBAT.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		System.setProperty("webdriver.chrome.driver","D:\\Eclipse\\eclipse-workspace\\MaxBloxBATAutomation\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		System.out.println("chorem option is working");
		WebDriverManager.chromedriver().setup();
		System.out.println("webdriver manager is working");
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		System.out.println("all options working");
		driverMBBAT = new ChromeDriver(options);
		// System.setProperty("webdriver.chrome.driver","D:\\Eclipse\\eclipse-workspace\\MaxBloxBATAutomation\\driver\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver","C:\\Users\\Automation\\eclipse-workspace\\MaxBloxBATAutomation\\driver\\chromedriver.exe");
		System.out.println("chrome driver working");

		return driverMBBAT;
	}

	public static void dropDown(WebElement element, String data) {
		Select s = new Select(element);
		s.selectByVisibleText(data);
	}

	public void HtmlReport() throws IOException {

		pw = new PrintWriter(new FileWriter("D:\\Automation\\CucumberTestNG\\Report1.html"));// Result saved location
		pw.println("<style>");// To Generate result in HTML
		pw.println("table{font-family: arial, sans-serif;border-collapse: collapse;width: 50%;}");// To Generate result
																									// in HTML
		pw.println("td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;}");// To Generate result in HTML
		pw.println("</style>");// To Generate result in HTML
		pw.println("<TABLE><TR><TH>MaxBlox Released Version<TH>Status<TH>Notes/Comments</TR>");

	}

	public void inputData() throws IOException {

		File file = new File(sysprojectpath + "//resources//Baseproperty.xls");
		// File file = new
		// File("D:\\Automation\\MaxBlox_BAT\\BATMaxBlox\\src\\test\\java\\resources\\Baseproperty.xls");
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook w = new HSSFWorkbook(fis);
		Sheet sheet = w.getSheet("Login");

		Row row = sheet.getRow(1);
		Cell cell1 = row.getCell(0);
		indexurl = cell1.getStringCellValue();
		Row row12 = sheet.getRow(1);
		Cell cell12 = row12.getCell(1);
		defaulturl = cell12.getStringCellValue();

		Row row124 = sheet.getRow(1);
		Cell cell124 = row124.getCell(2);
		Server_Name = cell124.getStringCellValue();
		Row row125 = sheet.getRow(1);
		Cell cell125 = row125.getCell(3);
		DB_Name = cell125.getStringCellValue();
		/*
		 * Row row126 = sheet.getRow(1); Cell cell126 = row126.getCell(4) ; Instance =
		 * cell126.getStringCellValue();
		 */
		Row row127 = sheet.getRow(1);
		Cell cell127 = row127.getCell(5);
		DB_Username = cell127.getStringCellValue();
		Row row128 = sheet.getRow(1);
		Cell cell128 = row128.getCell(6);
		DB_Password = cell128.getStringCellValue();
		Row row129 = sheet.getRow(1);
		Cell cell129 = row129.getCell(7);
		companyUrl = cell129.getStringCellValue();

		System.out.println("Index " + indexurl);

		FileInputStream fis1 = new FileInputStream(file);
		HSSFWorkbook w1 = new HSSFWorkbook(fis1);
		Sheet sheet1 = w1.getSheet("MaxBloxRelease");

//		Row row1 = sheet1.getRow(1);
//		Cell cell13 = row1.getCell(0) ;
//		Build1 = cell13.getStringCellValue();
		fullBuildVersion = Build1;
		// reportName=fullBuildVersion.replace(".", "-");
		System.out.println("executing u[to this");
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection(
							"jdbc:jtds:sqlserver://" + Server_Name + ";databaseName=" + DB_Name + ";instance="
									+ Instance + ";integratedSecurity=true",
							"" + DB_Username + "", "" + DB_Password + "");
			st = con.createStatement();
			res = null;
		} catch (Exception e) {
			// s = ExceptionUtils.getStackTrace(e);sendMailException();
			e.printStackTrace();
		}

	}

	public static String Screenshots() throws IOException {

		String directory = System.getProperty("user.dir") + "\\Screenshots\\" + Hook.scenarioname + ".png";
		File sourcefile = ((TakesScreenshot) driverMBBAT).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(directory));
		String destination = directory;
		return destination;
	}

	public String getscreenshotasbase64() throws IOException {
		directory = System.getProperty("user.dir") + "\\Screenshots\\\\" + Hook.scenarioname + ".png";
		File sourcefile = ((TakesScreenshot) driverMBBAT).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(directory));
		byte[] imagebyte = IOUtils.toByteArray(new FileInputStream(directory));
		return Base64.getEncoder().encodeToString(imagebyte);
	}

	public void waitForPageLoad() {
		wait = new WebDriverWait(driverMBBAT, 350);
		ExpectedCondition<Boolean> pageLoaded = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driverFB) {
				// System.out.println("Page is loading...");
				if (isAlertPresent()) {
					Alert alert = driverFB.switchTo().alert();
					alert.accept();
				}
				return ((JavascriptExecutor) driverMBBAT).executeScript("return document.readyState")
						.equals("complete");
			}
		};
		wait.until(pageLoaded);
		// System.out.println("Page loaded completely");
	}

	@SuppressWarnings("unused")
	public boolean assertTrue(boolean matches) {
		return false;
	}

	@SuppressWarnings("unused")
	public String closeAlertAndGetItsText() {
		return null;
	}

	public boolean isAlertPresent() {
		try {
			driverMBBAT.switchTo().alert();
			return true;

		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public void sendMail() throws FileNotFoundException, IOException {
		System.out.println("Mail Build1" + Build1);
		DateFormat dateFormat = new SimpleDateFormat("[MMM dd, yyyy]");
		Date date = new Date();
		String todayDate = dateFormat.format(date);
		System.out.println(todayDate);
		final String username = "automation@cellarstone.com";
		final String password = "f5LXNy63p4`_s54E";
		Properties props = new Properties();
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

//pass
		if (reportStatus.size() == 0) {
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("automation@cellarstone.com"));
				  message.setRecipients(Message.RecipientType.TO,
				  InternetAddress.parse("Balaji_Arumugam@cellarstone.com"));
				 

				// message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("vijayaraghavan_p@cellarstone.com,subramani_ps@cellarstone.com,sangeetha.b.02@cellarstone.com"));
				/*
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
				 * "mb_qv@cellarstone.com, Shankar_Sethuram@cellarstone.com,vijayaraghavan_p@cellarstone.com,Balaji_Arumugam@cellarstone.com"
				 * )); message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(
				 * "mb_dev@cellarstone.com, Sanal_Sankar@cellarstone.com, Arul_Britto@cellarstone.com, Sampathkumar_Muruganandam@cellarstone.com, mb_support@cellarstone.com"
				 * ));
				 */
				message.setSubject("[MaxBlox] - BAT Automation Report " + todayDate + " - " + Build1 + " ");
				String msg = "Hi Team,";
				String br = "<br/>";

				String msg2 = "Thanks,";
				String msg3 = "Quality Verification Team";
				String dis = "*** This is an automatically generated email, please do not reply ***";
				Multipart multipart = new MimeMultipart();
				BodyPart messageBodyPart = new MimeBodyPart();

				StringWriter writer = new StringWriter();
				IOUtils.copy(new FileInputStream(new File("D:\\Automation\\CucumberTestNG\\Report1.html")), writer);
				messageBodyPart.setContent("<font face=\"arial\">" + msg + br + br + "Result : <font color=green> PASS"
						+ "</font>" + br + br + "</font>" + writer.toString() + "<font face=\"arial\">" + br + br + br
						+ msg2 + br + msg3 + "</font>" + br + br + br + br + br + dis, "text/html");
				multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				MimeBodyPart attachPart = new MimeBodyPart();

				String reportLocation = System.getProperty("user.dir") + "\\reports\\MaxBlox BAT Automation Report "
						+ reportName + ".html";

				// File extentReport = new
				// File("E:\\Automation\\Eclipse\\CucumberTestNG\\reports\\MaxBlox BAT
				// Automation Report "+reportName+".html");

				File extentReport = new File(reportLocation);

				attachPart = new MimeBodyPart();
				attachPart.attachFile(extentReport);
				multipart.addBodyPart(attachPart);

				message.setContent(multipart);
				Transport.send(message);

				System.out.println("Email Sent");
			} catch (Exception e) {
				System.out.println("Failed to sent email.");
				throw new RuntimeException(e);
			}
		} else {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("automation@cellarstone.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("Balaji_Arumugam@cellarstone.com"));
//				           fail

				/*
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
				 * "mb_qv@cellarstone.com, Shankar_Sethuram@cellarstone.com,vijayaraghavan_p@cellarstone.com,Balaji_Arumugam@cellarstone.com"
				 * )); message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(
				 * "mb_dev@cellarstone.com, Sanal_Sankar@cellarstone.com, Arul_Britto@cellarstone.com, Sampathkumar_Muruganandam@cellarstone.com, mb_support@cellarstone.com"
				 * ));
				 */

				message.setSubject("[MaxBlox] - BAT Automation Report " + todayDate + " - " + Build1 + " ");
				String msg = "Hi Team,";
				String br = "<br/>";
				String msg2 = "Thanks,";
				String msg3 = "Quality Verification Team";
				String dis = "*** This is an automatically generated email, please do not reply ***";
				Multipart multipart = new MimeMultipart();
				BodyPart messageBodyPart = new MimeBodyPart();

				message.setHeader("X-Priority", "1");

				StringWriter writer = new StringWriter();
				IOUtils.copy(new FileInputStream(new File("D:\\\\Automation\\\\CucumberTestNG\\\\Report1.html")),
						writer);
				messageBodyPart.setContent("<font face=\"arial\">" + msg + br + br + "Result : <font color=red> FAIL"
						+ "</font>" + br + br + "</font>" + writer.toString() + "<font face=\"arial\">" + br + br + br
						+ msg2 + br + msg3 + "</font>" + br + br + br + br + br + dis, "text/html");
				multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				MimeBodyPart attachPart = new MimeBodyPart();

				String reportLocation = System.getProperty("user.dir") + "\\reports\\MaxBlox BAT Automation Report "
						+ reportName + ".html";

				File extentReport = new File(reportLocation);

				attachPart = new MimeBodyPart();
				attachPart.attachFile(extentReport);
				multipart.addBodyPart(attachPart);

				message.setContent(multipart);
				Transport.send(message);

				System.out.println("Email Sent");
			} catch (MessagingException e) {
				System.out.println("Failed to sent email.");
				throw new RuntimeException(e);
			}

		}

	}

}

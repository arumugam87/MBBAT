package com.mb.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mb.Utility.BaseProperty;

public class ExtentReportNG extends BaseProperty {
	
	
	//static ExtentReports extent;
	
		public static String sysprojectpath = System.getProperty("user.dir");

	public static ExtentHtmlReporter report = null;
	//public static ExtentReports extent = null;
	public static ExtentReports getReportObject() 
	{
		try {
//			inputData();
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
	//String path =System.getProperty("user.dir")+"\\reports\\index.html";
		File file = new File(sysprojectpath+"//resources//Baseproperty.xls");
//		File file = new File(sysprojectpath+"//src//resources//Baseproperty.xls");
		//	File file = new File("D:\\Automation\\MaxBlox_BAT\\BATMaxBlox\\src\\test\\java\\resources\\Baseproperty.xls");
		try {
			FileInputStream fis1 = new FileInputStream(file);
			HSSFWorkbook w1 = new HSSFWorkbook(fis1);
			Sheet sheet1 = w1.getSheet("MaxBloxRelease");
				
//				Row row1 = sheet1.getRow(1);
//				Cell cell13 = row1.getCell(0) ;
//				Build1 = cell13.getStringCellValue();
				reportName=Build1;
			//	reportName=fullBuildVersion.replace(".", "-");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		

	String reportLocation = System.getProperty("user.dir")+"\\reports\\MaxBlox BAT Automation Report "+Build1+".html";
	report = new ExtentHtmlReporter(reportLocation);	
	report.config().setDocumentTitle("MaxBlox Automation Report");
	report.config().setReportName("MaxBlox BAT Automation Report");
	report.config().setTheme(Theme.DARK);		
	System.out.println("Extent Report location initialized . . .");
	report.start();

	extent = new ExtentReports();
	extent.attachReporter(report);		
	extent.setSystemInfo("Application", "MaxBlox");
	extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	System.out.println("System Info. set in Extent Report");		
	return extent;
	}

	public static void testStepHandle(String teststatus,WebDriver driverMBBAT,ExtentTest extenttest,Throwable throwable) {
		switch (teststatus) {
		case "FAIL":		
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));			
			extenttest.error(throwable.fillInStackTrace());
			
			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driverMBBAT));
				} catch (IOException e) {
				e.printStackTrace();
				}
			
			if (driverMBBAT != null) {
				driverMBBAT.quit();
			}		
		break;
		
		case "PASS":			
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;
			
		default:
			break;
		}
	}

	public static String captureScreenShot(WebDriver driverMBBAT) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driverMBBAT;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"\\reports\\"+ getcurrentdateandtime() +".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	private static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}	
	
	

}

package com.mb.Listeners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.mb.StepDef.Hook;
import com.mb.Utility.BaseProperty;


public class ITestListenerImpl extends BaseProperty implements ITestListener {
	
	
	//ExtentTest test;
		ExtentTest logger;
		
		ExtentReportNG extentreportNG = new ExtentReportNG();
		ExtentReports extent = ExtentReportNG.getReportObject();

		public void onTestStart(ITestResult result) {
			

		}

		public void onTestSuccess(ITestResult result) {
			
		//	System.out.println("Itest Scenario pass:"+Hook.scenarioname);
			logger = extent.createTest(Hook.scenarioname);
			//test.log(Status.PASS, "Test passed+"+result.getName()+"");
			logger.log(Status.PASS,"");
		}
		public void onTestFailure(ITestResult result) {
			
			//System.out.println("Itest Scenario fail:"+Hook.scenarioname);
			logger = extent.createTest(Hook.scenarioname);
			/*
			 * try { test.addScreenCaptureFromPath(Screenshots()); } catch (IOException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); }
			 */
			reportStatus.add("FAIL");
		    try {
				logger.fail("Test case Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(getscreenshotasbase64()).build());
			} catch (IOException e) {
			}
		    //reportStatus.add("FAIL");
		}

		public void onTestSkipped(ITestResult result) {
			logger = extent.createTest(Hook.scenarioname);
			System.out.println("SKIP");
			reportStatus.add("FAIL");
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		public void onStart(ITestContext context) {
			//System.out.println("Execution started on UAT env...");
			
		}

		public void onFinish(ITestContext context) {
			System.out.println("Execution completed on UAT env...");
		//	extent.flush();		
			System.out.println("Generated Report. . .");	
			extent.flush();
			Connection con3;
			Statement st3;
			int res3;
		
		
			try {
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				con3=DriverManager.getConnection("jdbc:jtds:sqlserver://74.208.10.111;databaseName=ECSHARED_DB_01;instance=;integratedSecurity=true", "e7bd8256db21a0f5", "e7bd8256db21a0f5_123");
				st3 = con3.createStatement();
//				res3= null;
				System.out.println(ver);
				res3 = st3.executeUpdate("UPDATE bu_Release_Email SET Automated='Done' where Product='MaxBlox'");
				System.out.println("Sucessfully updated Automated Done in Mfd");
			}
			catch (Exception e) {
				System.out.println("Problem in updating Automated Done in Mfd");
				 e.printStackTrace();
				 
			}
			try {
				HtmlReport();
			} catch (IOException e1) {
			}
			
			if(reportStatus.size()==0)
		    {
				pw.println("<TR><TD>" + ""+Build1+"" + "<TD>"+ "<font color=green>PASS</font>"+"<TD>"+ "<font color=>Please find the attached report</font>");
		    }
			else {
				pw.println("<TR><TD>" + ""+Build1+"" + "<TD>"+ "<font color=red>FAIL</font>"+"<TD>"+ "<font color=>Please find the attached report</font>");
			}
			
			pw.println("</TABLE>");
			pw.close();
			try {
				sendMail();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driverMBBAT.close();
			driverMBBAT.quit();
			
		}

}

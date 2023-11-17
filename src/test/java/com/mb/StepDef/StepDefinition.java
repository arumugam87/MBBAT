package com.mb.StepDef;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mb.PageObjects.AdvancedPageDesignerPage;
import com.mb.PageObjects.CompanyPage;
import com.mb.PageObjects.MaxBloxDefaultPage;
import com.mb.PageObjects.MaxBloxIndexpage;
import com.mb.PageObjects.PageDesigner;
import com.mb.PageObjects.RegistrationPage;
import com.mb.PageObjects.UserSecurity;
import com.mb.Utility.BaseProperty;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition extends BaseProperty {
	
	MaxBloxDefaultPage MBdefaultpage;
	MaxBloxIndexpage maxBloxindexpage;
	CompanyPage companypage;
	RegistrationPage registrationpage;
	PageDesigner pagedesignerpage;
	UserSecurity usersecurity;
	AdvancedPageDesignerPage advancedpagedesignerpage;
	
	
	BaseProperty base;
	static String usrId="";
	static String usrId2="";
//	String indexURL="";
	/*
	 * String activateUrl = ""; String afRowID =""; String companyVersion ="";
	 * String dbNameAfRowId =""; String dbcount ="";
	 */
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHMMSS");  
	 static LocalDateTime now = LocalDateTime.now();  
	 static String format = dtf.format(now);
	public static String text="PageAdv"+format;
	public static String Build="MB"+format;
	 
	  
//	public static String Build=Build1;
//	public static String Build="BAT_MB45";
	public static String multipleSchemaId=Build+"multipleschemaId";
	public static String multipleSchemaId2=Build+"multipleschemaId2";
	public static String firstCompany=Build+"_first";
	public static String secondCompany=Build+"_second";
	public static String ver="";
	
	
	@Given("Get the MaxBlox released version from MFD server")
	public void get_the_MaxBlox_released_version_from_MFD_server() throws IOException {
		
		Connection con3;
		Statement st3;
		ResultSet res3;
	
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con3=DriverManager.getConnection("jdbc:jtds:sqlserver://74.208.10.111;databaseName=ECSHARED_DB_01;instance=;integratedSecurity=true", "e7bd8256db21a0f5", "e7bd8256db21a0f5_123");
			st3 = con3.createStatement();
			res3= null;
			
			res3 = st3.executeQuery("select Version from bu_Release_Email where Automated='NO' and Product='MaxBlox' ORDER BY created_at desc");
			if(res3.next())
			{
				ver = res3.getString("Version");			
			}
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		String ver1 = ver.replace("V","Version ");
	    String ver2 = ver1.replaceAll(" R",".");
	    Build1= ver2.replace("B","Build ");
		System.out.println("Version from mfd:"+Build1);
		
	}
	
	
	@Then("open the MaxBlox application index login page")
	public void open_the_MaxBlox_application_index_login_page() throws IOException {
		inputData();
		System.out.println("UP TO THE MARK");
		driverMBBAT = initializedriver();
		System.out.println("text above");
		System.out.println(text);
		System.out.println("-- MaxBlox BAT Automation Started --");
		driverMBBAT.manage().deleteAllCookies();
		System.out.println("fullBuildVersion start"+fullBuildVersion);
		System.out.println("Url"+indexurl);
		driverMBBAT.get(indexurl);
		waitForPageLoad();
	}

	@Then("Verify correct version information shows up in index.aspx")
	public void verify_correct_version_information_shows_up_in_index_aspx() {
		
		MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		String toolTip = MBdefaultpage.Tooltip().getAttribute("title");
		System.out.println("Build1"+Build1);
		assertEquals(toolTip, Build1);
		
	}

	@Then("open the MaxBlox application default login page")
	public void open_the_MaxBlox_application_default_login_page() throws IOException {
//		driverMBBAT = initializedriver();
//		inputData();
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		System.out.println("Pass1");
	}

	@Then("Verify correct version information shows up in default.aspx")
	public void verify_correct_version_information_shows_up_in_default_aspx() {
	    
		String toolTip = MBdefaultpage.Tooltip().getAttribute("title");
		assertEquals(toolTip, Build1);
		System.out.println("Pass2");
	}
	
	@Given("open the MaxBlox Index login page")
	public void open_the_MaxBlox_Index_login_page() throws Exception {
		
		maxBloxindexpage = new MaxBloxIndexpage(driverMBBAT) ;
		driverMBBAT.get(indexurl);
		waitForPageLoad();
		Thread.sleep(2000);
	}

	@Then("login to home page")
	public void login_to_home_page() throws Exception {
		
		maxBloxindexpage.UserID().clear();
		maxBloxindexpage.UserID().sendKeys(DB_Username);
		maxBloxindexpage.Password().clear();
		maxBloxindexpage.Password().sendKeys(DB_Password);
		maxBloxindexpage.Submitbutton().click();
		waitForPageLoad();
		Thread.sleep(2000);
	}

	@Then("Navigate to company page")
	public void navigate_to_company_page() throws Exception {
		driverMBBAT.get(companyUrl);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
	    driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
		waitForPageLoad();
		Thread.sleep(7000);
		
	}

	@Then("Create a company")
	public void create_a_company() throws Exception {
		
		  System.out.println(Build);
		  companypage = new CompanyPage(driverMBBAT);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder3_p_ngcompany_company_id']")));
		  companypage.CompanyID().sendKeys(Build);
		  companypage.CompanyName().sendKeys(Build); companypage.CompanySave().click();
		  waitForPageLoad();
		  Thread.sleep(4000);
	}

	@Then("Edit the company and Company Server Details")
	public void edit_the_company_and_Company_Server_Details() throws InterruptedException {
		
		  wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		//  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(Build);
		 // companypage.SearchButton().click(); Thread.sleep(5000);
		  System.out.println("search button clicked");
			 
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(Build);
		  companypage.SearchButton().click(); Thread.sleep(5000);
		  Actions a = new Actions(driverMBBAT);
	//	  a.moveToElement(companypage.SearchBOX()).click().perform();
		  System.out.println("moved to the search box");
//		  companypage.CompanyServerEdit().click(); waitForPageLoad();
		  WebElement elementp = driverMBBAT.findElement(By.xpath("//span[text()='"+Build+"']"));
		  System.out.println("ref //span[text()='"+Build+"'");
		  
		//  WebElement elementp = driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_ObjectAF_ROW_ID_2c15a3afe88d2742"));
		 
//		  
//		  driverMBBAT.findElement(By.xpath("(//i[@class='far fa-edit mb-icons-far'])[3]")).click();
		
		  Thread.sleep(5000);
		 a.moveToElement(elementp).perform();Thread.sleep(3000);
		  System.out.println("element move found");
	//	  Thread.sleep(3000);
		  
		 // JavascriptExecutor js1=( JavascriptExecutor )driverMBBAT;
		//  js1.executeScript("location.reload();");
		//  System.out.println("reloaded");
		  Thread.sleep(10000);
		//  js1.executeScript("document.getElement(By.xpath(\"//*[@id=\\\"tippy-6\\\"]/div/div/div/div/div/table/tbody/tr/td[1]/a\").click()");
		  WebElement edit=  driverMBBAT.findElement(By.xpath("//i[@title='Edit']"));
		  Thread.sleep(3000);
		  edit.click();
		  System.out.println("clicked");
		  Thread.sleep(5000);
		 // a.moveToElement(edit).click().perform();
		//  Thread.sleep(5000);
	//    //i[@title='Edit']
	//	  /html/body/form/div[3]/div[4]/section[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/table/tbody/tr[5]/td[1]/div/div/div/table/tbody/tr/td[1]/a
	//	  //i[@title='Edit']
	//	  /html/body/form/div[3]/div[4]/section[2]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/table/tbody/tr[1]/td[1]/div/div/div/table/tbody/tr/td[1]/a
	//	  //i[@title='Edit']
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(5000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window);
		  Thread.sleep(2000);} }
		 
	}

	@Then("Save the details")
	public void save_the_details() throws Exception {
		
		companypage.DbName().clear(); companypage.DbName().sendKeys(Build);
		companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		companypage.Companypwd().clear();
		companypage.Companypwd().sendKeys("Cellar@123");
		companypage.CompanySave().click(); Thread.sleep(5000);
		driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		waitForPageLoad();
		Thread.sleep(3000);
	}

	@Then("Verify the created company in DB")
	public void verify_the_created_company_in_DB() throws Exception {
		
		  companypage.Logout().click(); Thread.sleep(4000);
		  companypage.LogoutButton().click(); waitForPageLoad();
		  Thread.sleep(3000);
		  res  =st.executeQuery("select company_name from ngcompany where company_name='" +Build+"'");
		  
		  if(res.next()) {
		  
		  String dbvalue = res.getString("company_name");
		  assertEquals(dbvalue, Build); 
		  
		  }
		 
	}
	
	@Given("open the MaxBlox default login page")
	public void open_the_MaxBlox_default_login_page() throws Exception {
		
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		Thread.sleep(2000);
	}

	@Then("login to home page and choose the default")
	public void login_to_home_page_and_choose_the_default() throws Exception {
		
		  MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		  MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys("admin");
		  MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("admin");
		  MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
		  MBdefaultpage.CompanySearch().click(); MBdefaultpage.CompanySearch().clear();
		  MBdefaultpage.CompanySearch().sendKeys(Build); Thread.sleep(3000);Thread.sleep(10000);
		  driverMBBAT.findElement(By.xpath("//span[contains(text(),'"+Build+"')]")). click(); waitForPageLoad();
		  driverMBBAT.navigate().refresh();
		  Thread.sleep(9000);
		  
//		  Assert.assertEquals(true, MBdefaultpage.FeedBack().get(0).isDisplayed());

		  int status = MBdefaultpage.login().size();
		  String stus="";
		    
		  if(status==0) {
			  
			  stus="PASS";
		  }
		  else {
		    	stus="FAIL";	
		    }
		  assertEquals(stus, "PASS");
	}

	@Then("Verify the created company version in DB")
	public void verify_the_created_company_version_in_DB() throws SQLException, InterruptedException {
		
		
		res =st.executeQuery("select af_row_id from ngcompany where company_Name='"+Build+"'");
		  
		String dbvalue = "";
		  
		if(res.next()) {
		  
			dbvalue = res.getString("af_row_id"); }
		
			res =st.executeQuery("select company_version from ngcompanyserver where company_id='"+dbvalue+"'");
		  
			String dbvalue2; String Build2; String Build3;
			if(res.next()) {
		  
				dbvalue2 = res.getString("company_version");
				Build3 =Build1.replaceAll("Build", ".");
				Build2 = Build3.replaceAll("[a-zA-Z]", "");
				Build1 = Build2.replaceAll(" ", ""); 
				System.out.println("dbvalue2: "+dbvalue2);
				System.out.println("Build: "+Build1);
				assertEquals(dbvalue2, Build1); 
		  }
		
		System.out.println("Verified company version from backend");
		
		driverMBBAT.findElement(By.xpath("//span[@id='ctl00_lbl_UserName_LogOut']")).click();
		Thread.sleep(5000);
		driverMBBAT.findElement(By.xpath("//span[text()='Logout']")).click();
		Thread.sleep(5000);
		
	}
	
	@Given("open the MaxBlox Registration login page")
	public void open_the_MaxBlox_Registration_login_page() throws SQLException, Exception {
//		driverMBBAT = initializedriver();
//		inputData();
//		driverMBBAT.manage().deleteAllCookies();
		try {
			res =st.executeQuery("delete from email_queue WHERE subject LIKE '%New user for MaxBlox'");
		} catch (SQLException e) {
		}
		
		try {
			res =st.executeQuery("delete from email_queue WHERE subject LIKE '%Activate your MaxBlox account'");
		} catch (SQLException e) {
		}
		
		try {
			res =st.executeQuery("delete from email_queue WHERE subject LIKE '%Welcome to MaxBlox'");
		} catch (SQLException e) {
		}
		
		driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/mb_registration.aspx");
		System.out.println("Done");
		waitForPageLoad();
		Thread.sleep(9000);
	}

	@Then("Fill the Registration page fields")
	public void fill_the_Registration_page_fields() throws Exception {
		WebElement findElement = driverMBBAT.findElement(By.xpath("//span[contains(text(),'User Details')]"));
		 wait.until(ExpectedConditions.visibilityOf(findElement));
		System.out.println("ok");
		registrationpage = new RegistrationPage(driverMBBAT) ;
		registrationpage.UserIDEmail().clear();
		registrationpage.UserIDEmail().sendKeys("Automation"+dtf.format(now)+"@cellarstone.com");
		usrId = "Automation"+dtf.format(now)+"@cellarstone.com";
//		usrId = "AutomationV8B42@cellarstone.com";
		System.out.println(usrId);
		companyID = usrId;
		registrationpage.FirstName().sendKeys("Automation");
		registrationpage.LastName().sendKeys("CS");
		registrationpage.Pwd().clear();
		registrationpage.Pwd().sendKeys("Auto@123");
		registrationpage.ConPwd().sendKeys("Auto@123");
		registrationpage.PwdHint().click();
		Thread.sleep(2000);
		registrationpage.PwdHintChoose().click();
		registrationpage.HintAns().sendKeys("CSK");
		registrationpage.CompanyName().sendKeys("Cellarstone");
		registrationpage.Adrs().sendKeys("A6");
		registrationpage.city().sendKeys("Chennai");
		registrationpage.PsCode().sendKeys("600005");
		registrationpage.Phone().sendKeys("1234567890");
		registrationpage.country().click();
		Thread.sleep(2000);
		System.out.println("ok2");
		registrationpage.CountryChoose().sendKeys("India");
		registrationpage.CountryChoose2().click();
		registrationpage.State().click();
		Thread.sleep(2000);
		registrationpage.statechoose().sendKeys("Ala");
		registrationpage.statechoose2().click();
		registrationpage.TermsCon().click();
		userID="Automation"+dtf.format(now)+"@cellarstone.com";		
		System.out.println("user Id "+"Automation"+dtf.format(now)+"@cellarstone.com");
		System.out.println("ok3");
	}

	@Then("Click the Register button")
	public void click_the_Register_button() throws InterruptedException {
	   
		registrationpage.RegBtn().click();
		waitForPageLoad();
		Thread.sleep(35000);
	}

	@Then("Get the Url from the Backend")
	public void get_the_Url_from_the_Backend() throws SQLException {
	   
		res =st.executeQuery("select content from email_queue WHERE subject LIKE '%Activate your MaxBlox account'");
		
		if(res.next()) {
			
			activateUrl = res.getString("content");
		} 
		}

	@Then("Activate the account using url")
	public void activate_the_account_using_url() throws Exception {
		
		String value=activateUrl;
		String value2="";
		
		String[] words=value.split("\\s");//splits the string based on whitespace  
		for(String w:words){ 
			
			if(w.contains("href") && (w.contains("mb_activation"))) {
				value2=w;
				
				String[] words2=value2.split(">");
				
				for(String w2:words2){
					
					if(w2.contains("href") && (w2.contains("mb_activation"))) {
						
						String url = w2.replaceAll("href=", "");
						String url3 = url.replaceAll("\"", "");
						activateUrl = url3;
						System.out.println("Split Word: "+url3);
					}
				}
			}
		}
		
		System.out.println("Split Word2: "+activateUrl);
	    driverMBBAT.get(activateUrl);
	    waitForPageLoad();
	    Thread.sleep(2000);
	    registrationpage.ActLink().click();
	    waitForPageLoad();
	    MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
	    driverMBBAT.navigate().refresh();
	    waitForPageLoad();
	    MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys(usrId);
//	    JavascriptExecutor js=(JavascriptExecutor)driverMBBAT;
//	    js.executeScript("arguments[0].setattribute('value','Auto@123')",MBdefaultpage.Password());
	    System.out.println(MBdefaultpage.Password().isDisplayed());
	    MBdefaultpage.Password().clear(); 
	    MBdefaultpage.Password().sendKeys("Auto@123");
	    MBdefaultpage.Submitbutton().click(); waitForPageLoad();Thread.sleep(8000);
	    
	    int status = MBdefaultpage.login().size();
	    String stus="";
	    
	    if(status==0) {
	    	
	    	stus="PASS";
	    }
	    else {
	    	stus="FAIL";	
	    }
	    assertEquals(stus, "PASS");
	    
	    Thread.sleep(5000);
	    usersecurity = new UserSecurity(driverMBBAT);
	    Thread.sleep(2000);
		usersecurity.Logout().click();
		Thread.sleep(1000);
		usersecurity.Logoutbtn().click();
		waitForPageLoad();
		Thread.sleep(8000);
	}
	
	@Given("Get the afrowid from ngcompany table")
	public void get_the_afrowid_from_ngcompany_table() throws SQLException {
		
		//userID="Automation20210208130274@cellarstone.com";
		System.out.println("userID:"+userID);
		try {
			
			res =st.executeQuery("select af_row_id from ngcompany where email='"+usrId+"'");
			
			if(res.next()) {
				
				afRowID = res.getString("af_row_id");
			}
			System.out.println("afRowID:"+afRowID);
			
		} catch (SQLException e) {
		} 
		
		
	}

	@Then("Verify the company version")
	public void verify_the_company_version() throws SQLException {
		
		try {
			
			res =st.executeQuery("select company_version from  ngcompanyserver where company_Id='"+afRowID+"'");
			
			if(res.next()) {
				
				companyVersion = res.getString("company_version");
			}
			System.out.println("companyVersion "+companyVersion);
			String Build3;
			String Build2;
			Build3 =Build1.replaceAll("Build", ".");
			Build2 = Build3.replaceAll("[a-zA-Z]", "");
			Build1 = Build2.replaceAll(" ", ""); 
			System.out.println("Build "+Build1);
			System.out.println("companyVersion "+companyVersion);
			
			assertEquals(companyVersion, Build1);
		} catch (SQLException e) {
		} 
		System.out.println("Successfully verified Version from DB");
	}
	
	@Given("Get the database name from ngcompanyserver table")
	public void get_the_database_name_from_ngcompanyserver_table() throws SQLException, Exception {
		
		//companyVersion = "4d2aaa6cf0ee3457";
		//System.out.println("companyVersion: "+companyVersion);
		
		/*
		 * try {
		 * 
		 * res
		 * =st.executeQuery("select af_row_id from ngcompany where email='"+companyID+
		 * "'");
		 * 
		 * if(res.next()) {
		 * 
		 * afRowID2 = res.getString("af_row_id"); } } catch (SQLException e) { } try {
		 * 
		 * res =st.executeQuery("select dbname from  ngcompanyserver where company_Id='"
		 * +afRowID2+"'");
		 * 
		 * if(res.next()) {
		 * 
		 * dbNameAfRowId = res.getString("dbname"); } } catch (SQLException e) { }
		 * System.out.println("dbNameAfRowId: "+dbNameAfRowId);
		 */
		
		maxBloxindexpage = new MaxBloxIndexpage(driverMBBAT) ;
		driverMBBAT.get(indexurl);
		waitForPageLoad();
		Thread.sleep(2000);

		maxBloxindexpage.UserID().clear();
		maxBloxindexpage.UserID().sendKeys(DB_Username);
		maxBloxindexpage.Password().clear();
		maxBloxindexpage.Password().sendKeys(DB_Password);
		maxBloxindexpage.Submitbutton().click();
		waitForPageLoad();
		driverMBBAT.get(companyUrl);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
		    driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
		waitForPageLoad();
		Thread.sleep(7000);

		companypage = new CompanyPage(driverMBBAT);
		String singleschemaId=Build+"SingleschemaId";
		companypage.CompanyID().sendKeys(singleschemaId);
		companypage.CompanyName().sendKeys(Build+"SingleschemaName"); companypage.CompanySave().click();
		waitForPageLoad();
		Thread.sleep(20000);	
		  wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(singleschemaId);
		  companypage.SearchButton().click(); Thread.sleep(9000);
//		  companypage.CompanyServerEdit().click(); 
		WebElement companyID =  driverMBBAT.findElement(By.xpath("//span[text()='"+singleschemaId+"']"));
		Actions a = new Actions(driverMBBAT);
		a.moveToElement(companyID).perform();
		  Thread.sleep(5000);
		  driverMBBAT.findElement(By.xpath("//i[@title='Edit']")).click();
		 
		  Thread.sleep(5000);
		  waitForPageLoad();
		  
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(8000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window); } }


		  companypage.DbName().clear(); companypage.DbName().sendKeys(Build+"singleschemaDB");
		  companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		  companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		  companypage.Companypwd().clear();
		  companypage.Companypwd().sendKeys("Cellar@123");
		  companypage.CompanySave().click(); Thread.sleep(8000);
		  driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		  waitForPageLoad();
		  Thread.sleep(6000);

			
			  companypage.Logout().click(); Thread.sleep(8000);
			  companypage.LogoutButton().click(); waitForPageLoad();
			 
		  
		 
		  
		  
	}

	@Then("Verify the Afrowid character")
	public void verify_the_Afrowid_character() {
		
		/*
		 * String string = dbNameAfRowId; int count = 0;
		 * 
		 * for(int i = 0; i < string.length(); i++) { if(string.charAt(i) != ' ')
		 * count++; } System.out.println("count: "+count); assertEquals(count, 16);
		 */
		 try
			{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con1=DriverManager.getConnection("jdbc:jtds:sqlserver://"+Server_Name+";databaseName="+Build+"singleschemaDB;instance="+Instance+";integratedSecurity=true", ""+DB_Username+"", ""+DB_Password+"");
			st1 = con1.createStatement();
			res1 = null;
			} catch (Exception e)
			{
			//s = ExceptionUtils.getStackTrace(e);sendMailException();
				e.printStackTrace();
			}
		  
		  System.out.println("DB"+Build+"singleschemaDB");
		  
		  try {
		  res1 =st1.executeQuery("select count(name) from ["+Build+"singleschemaDB].sys.database_principals where name='"+Build+"SingleschemaId'");
			
			if(res1.next()) {
				
				dbusercount = res1.getString("");
			}
		} catch (SQLException e) {
		}
		  
		  System.out.println("dbusercount:"+dbusercount);
		  
		  int i=Integer.parseInt(dbusercount); 
		  
		  assertEquals(i, 1);
	
	}
	
	@Given("open the MaxBlox Index page")
	public void open_the_MaxBlox_Index_page() throws Exception {
//		driverMBBAT = initializedriver();
//		inputData();
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(3000);
		maxBloxindexpage = new MaxBloxIndexpage(driverMBBAT) ;
		driverMBBAT.get(indexurl);
		waitForPageLoad();
		Thread.sleep(3000);
	}

	@Then("login to Index Home Page")
	public void login_to_Index_Home_Page() throws Exception {
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(3000);
		maxBloxindexpage.UserID().clear();
		maxBloxindexpage.UserID().sendKeys(DB_Username);
		maxBloxindexpage.Password().clear();
		maxBloxindexpage.Password().sendKeys(DB_Password);
		maxBloxindexpage.Submitbutton().click();
		waitForPageLoad();
		Thread.sleep(2000);
	}

	@Then("Create a secondcompany")
	public void create_a_secondcompany() throws Exception {
		
		
		 companypage = new CompanyPage(driverMBBAT);
		 companypage.CompanyID().sendKeys(multipleSchemaId);
		 companypage.CompanyName().sendKeys(Build+"multipleschemaName");
		 companypage.CompanySave().click();
		 waitForPageLoad();
		 Thread.sleep(2000);
	}

	@Then("Edit the secondcompany and secondCompany Server Details")
	public void edit_the_secondcompany_and_secondCompany_Server_Details() throws InterruptedException {
	    
		wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(multipleSchemaId);
		  companypage.SearchButton().click(); Thread.sleep(10000);
//		  companypage.CompanyServerEdit().click(); waitForPageLoad();
		WebElement companyId=  driverMBBAT.findElement(By.xpath("//span[text()='"+multipleSchemaId+"']"));
		  Actions a = new Actions(driverMBBAT);
			a.moveToElement(companyId).perform();
		  Thread.sleep(5000);
		  driverMBBAT.findElement(By.xpath("//i[@title='Edit']")).click();
		  Thread.sleep(8000);
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(5000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window); } }
	  
	}

	@Then("Save all the details")
	public void save_all_the_details() throws InterruptedException {
	  
		companypage.DbName().clear(); companypage.DbName().sendKeys(Build+"multipleschemaDB");
		companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		companypage.Companypwd().clear();
		companypage.Companypwd().sendKeys("Cellar@123");
		companypage.CompanySave().click(); Thread.sleep(10000);
		driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		waitForPageLoad();
		
		/*
		 * companypage.Logout().click(); Thread.sleep(3000);
		 * companypage.LogoutButton().click(); waitForPageLoad();
		 */
	  System.out.println("ok");
	}

	@Then("Verify the number of database name ngcompanyserver")
	public void verify_the_number_of_database_name_ngcompanyserver() throws Exception {
	    
		/*
		 * try {
		 * 
		 * System.out.println("fullBuildVersion "+fullBuildVersion);
		 * 
		 * res =st.executeQuery("select count(*) from ngcompanyserver where dbname='"
		 * +fullBuildVersion+"'");
		 * 
		 * if(res.next()) {
		 * 
		 * dbcount = res.getString(""); } } catch (SQLException e) { }
		 * 
		 * System.out.println("OutPut:"+dbcount); int count = Integer.parseInt(dbcount);
		 * 
		 * String status="";
		 * 
		 * if(count>1) {
		 * 
		 * System.out.println("Count Pass:"+count); status="PASS"; } else {
		 * 
		 * System.out.println("Count Fail:"+count); status="FAIL"; }
		 * 
		 * assertEquals(status, "PASS");
		 */
		
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(3000);
		maxBloxindexpage = new MaxBloxIndexpage(driverMBBAT) ;
		/*
		 * driverMBBAT.get(indexurl); waitForPageLoad(); Thread.sleep(3000);
		 * driverMBBAT.navigate().refresh(); waitForPageLoad(); Thread.sleep(3000);
		 * maxBloxindexpage.UserID().clear();
		 * maxBloxindexpage.UserID().sendKeys(DB_Username);
		 * maxBloxindexpage.Password().clear();
		 * maxBloxindexpage.Password().sendKeys(DB_Password);
		 * maxBloxindexpage.Submitbutton().click(); waitForPageLoad();
		 * Thread.sleep(2000);	
		 */
		
		driverMBBAT.get(companyUrl);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
		    driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(12000);
		companypage = new CompanyPage(driverMBBAT);
		 companypage.CompanyID().sendKeys(multipleSchemaId2);
		 companypage.CompanyName().sendKeys(Build+"multipleschemaName2");
		 companypage.CompanySave().click();
		 waitForPageLoad();
		 Thread.sleep(10000);
		 driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/DisplayGrid.aspx?mn=61e60132941d2c08&pgs_id=5d19260c71518582");
		 Thread.sleep(15000);
		 wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(multipleSchemaId2);
		  companypage.SearchButton().click(); Thread.sleep(10000);
//		  companypage.CompanyServerEdit().click(); waitForPageLoad();Thread.sleep(3000); 
		WebElement companyID =  driverMBBAT.findElement(By.xpath("//span[text()='"+multipleSchemaId2+"']"));
		  Actions a = new Actions(driverMBBAT);
			a.moveToElement(companyID).perform();
		  Thread.sleep(10000);
		  driverMBBAT.findElement(By.xpath("//i[@title='Edit']")).click();
		  Thread.sleep(10000);
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(5000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window); } }

		  companypage.DbName().clear(); companypage.DbName().sendKeys(Build+"multipleschemaDB");
		  companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		companypage.Companypwd().clear();
		companypage.Companypwd().sendKeys("Cellar@123");
		companypage.CompanySave().click(); Thread.sleep(5000);
		driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		waitForPageLoad();
		
		companypage.Logout().click(); Thread.sleep(7000);
		companypage.LogoutButton().click(); waitForPageLoad();
		Thread.sleep(12000);
		try
		{
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		con2=DriverManager.getConnection("jdbc:jtds:sqlserver://"+Server_Name+";databaseName="+Build+"multipleschemaDB;instance="+Instance+";integratedSecurity=true", ""+DB_Username+"", ""+DB_Password+"");
		st2 = con2.createStatement();
		res2 = null;
		} catch (Exception e)
		{
		//s = ExceptionUtils.getStackTrace(e);sendMailException();
			e.printStackTrace();
		}
	  
	  System.out.println("DB"+Build+"singleschemaDB");
	  
	  try {
	  res2 =st2.executeQuery("select count(name) from ["+Build+"multipleschemaDB].sys.database_principals WHERE name IN ('"+Build+"multipleschemaId','"+Build+"multipleschemaId2')");
		
		if(res2.next()) {
			
			dbusercount = res2.getString("");
		}
	} catch (SQLException e) {
	}
	  
	  System.out.println("dbusercount:"+dbusercount);
	  
	  int i=Integer.parseInt(dbusercount); 
	  
	  String status="";
	  if(i>=2) {
		 
		  status="PASS";
	  }
	  else {
		  status="FAIL";
	  }
	  
	  assertEquals(status, "PASS");
	  
	}

	@Given("open the MaxBlox default page")
	public void open_the_MaxBlox_default_page() throws IOException {
//		driverMBBAT = initializedriver();
		inputData();
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
	}

	@When("login to home page and choose the newly created company")
	public void login_to_home_page_and_choose_the_newly_created_company() throws Exception {
	 
		 MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		 MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys("admin");
		 MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("admin");
		 MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
		 MBdefaultpage.CompanySearch().click(); MBdefaultpage.CompanySearch().clear();
		 MBdefaultpage.CompanySearch().sendKeys(Build); Thread.sleep(3000);
		 driverMBBAT.findElement(By.xpath("//span[contains(text(),'"+Build+"')]")). click(); 
		 waitForPageLoad();
		
	}	

	@Then("Navigate to page Designer and create a page")
	public void navigate_to_page_Designer_and_create_a_page() throws Exception {
	 
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		
		Thread.sleep(5000);
		 driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/pagedesigner.aspx?pgs_id=b3ef063b174e4ccc&mn=ae79b380c4bee6ba");
		 waitForPageLoad();
		 
		 pagedesignerpage = new PageDesigner(driverMBBAT) ;
			pagedesignerpage.PageName().clear();
			pagedesignerpage.PageName().sendKeys("Automation"+dtf.format(now));
			((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
					pagedesignerpage.TxtName());
			Thread.sleep(6000);
			pagedesignerpage.TxtName().sendKeys("TextBox1");
			pagedesignerpage.Desc().sendKeys("Desc");
			pagedesignerpage.Seq().sendKeys("1");
			pagedesignerpage.TxtName2().sendKeys("TextBox2");
			pagedesignerpage.Desc2().sendKeys("Desc2");
			pagedesignerpage.Seq2().sendKeys("2");
			((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
			pagedesignerpage.Menucheckbox());
			Thread.sleep(2000);
			pagedesignerpage.Menucheckbox().click();
			((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
			pagedesignerpage.SaveBtn());
			Thread.sleep(2000);
			pagedesignerpage.SaveBtn().click();
			waitForPageLoad();
	}

	@Then("Check the created page in menubar and List page")
	public void check_the_created_page_in_menubar_and_List_page() throws Exception {
	 
		List<WebElement> pages= driverMBBAT.findElements(By.xpath("//span[text()='Automation"+dtf.format(now)+"']"));
	   
		String status="";
		System.out.println("Page text count: "+pages.size());
		if(pages.size()>=3) {
			
			status="PASS";
		}
		else {
			status="FAIL";
		}
		
		assertEquals(status, "PASS");
	}
	
	@Given("open the MaxBlox usersetup page")
	public void open_the_MaxBlox_usersetup_page() throws Exception {
//	 driverMBBAT= initializedriver();
//	 inputData();
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(6000);
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		Thread.sleep(6000);
		 MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		 MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys("admin");
		 MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("admin");
		 MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
		 MBdefaultpage.CompanySearch().click(); MBdefaultpage.CompanySearch().clear();
		 MBdefaultpage.CompanySearch().sendKeys(Build); Thread.sleep(3000);
		 driverMBBAT.findElement(By.xpath("//span[contains(text(),'"+Build+"')]")). click(); waitForPageLoad();
		 waitForPageLoad();
		 Thread.sleep(6000);
		 driverMBBAT.navigate().refresh();
		 waitForPageLoad();
		 Thread.sleep(6000);
		driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/UserWizard.aspx?pgs_id=8c2cb032c3b3516d&mn=1cacb61d1610744d");
		 waitForPageLoad();
		 Thread.sleep(3000);
	}

	@Then("Add user details and save the details")
	public void add_user_details_and_save_the_details() throws InterruptedException {
		
		usersecurity = new UserSecurity(driverMBBAT);
		System.out.println("Automation"+dtf.format(now)+"@cellarstone.com");
		usrId2 = "Automation"+dtf.format(now)+"@cellarstone.com";
		System.out.println(usrId2);
		usersecurity.UserId().clear();
		usersecurity.UserId().sendKeys(usrId2);
		usersecurity.FirstName().sendKeys("Automation");
		usersecurity.LastName().sendKeys("CS");
		usersecurity.Email().sendKeys(usrId2);
//		usersecurity.Hintques().click();
//		Thread.sleep(1000);
//		usersecurity.Hintques2().click();
//		Thread.sleep(1000);
//		usersecurity.Hintans().sendKeys("CSK");
		((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		usersecurity.PWD());
		usersecurity.PWD().clear();
		usersecurity.PWD().sendKeys("Auto123");
		((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		usersecurity.Savebtn());
		usersecurity.Savebtn().click();
		waitForPageLoad();
		Thread.sleep(2000);
	}

	@Then("logout the user")
	public void logout_the_user() throws Exception {
		Thread.sleep(2000);
		usersecurity.Logout().click();
		Thread.sleep(3000);
		usersecurity.Logoutbtn().click();
		waitForPageLoad();
		Thread.sleep(5000);
	}

	@Then("Login into home page using newly created details")
	public void login_into_home_page_using_newly_created_details() throws Exception {
	    
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		Thread.sleep(2000);
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		Thread.sleep(2000);
		
		MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys(usrId2);
		MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("Auto123");
		MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
	//	Assert.assertEquals(true, MBdefaultpage.FeedBack().get(0).isDisplayed());
		
//		int status = MBdefaultpage.login().size();
//		String stus="";
//		    
//		if(status==0) {
//		    	
//			stus="PASS";
//		}
//		else {
//			stus="FAIL";	
//		}
//		assertEquals(stus, "PASS");
//		    
//		usersecurity = new UserSecurity(driverMBBAT);
//		Thread.sleep(2000);
//		usersecurity.Logout2().click();
//		Thread.sleep(2000);
//		usersecurity.Logoutbtn().click();
//		waitForPageLoad();
//		Thread.sleep(2000);
//		driverMBBAT.navigate().refresh();
//		 waitForPageLoad();
//		 Thread.sleep(2000);
	}
	
	@Given("open Index page")
	public void open_Index_page() throws IOException, InterruptedException {
//		 driverMBBAT= initializedriver();
//		 inputData();
		Thread.sleep(2000);
		driverMBBAT.findElement(By.xpath("//span[@id='ctl00_lbl_UserName_LogOut']")).click();
		Thread.sleep(5000);
		driverMBBAT.findElement(By.xpath("//span[text()='Logout']")).click();
		Thread.sleep(5000);
		driverMBBAT.get(indexurl);
		waitForPageLoad();
	}

	@Then("login into mxblx home page")
	public void login_into_mxblx_home_page() throws Exception {
		maxBloxindexpage = new MaxBloxIndexpage(driverMBBAT) ;
		maxBloxindexpage.UserID().clear();
		maxBloxindexpage.UserID().sendKeys(DB_Username);
		maxBloxindexpage.Password().clear();
		maxBloxindexpage.Password().sendKeys(DB_Password);
		maxBloxindexpage.Submitbutton().click();
		waitForPageLoad();
		Thread.sleep(3000);
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
	}

	@Then("Navigate to firstcompany page")
	public void navigate_to_firstcompany_page() throws Exception {
	    
		driverMBBAT.get(companyUrl);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
		    driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
		//	driverMBBAT.get("http://10.199.4.22:81/DisplayGrid.aspx?mn=61e60132941d2c08&pgs_id=5d19260c71518582");
		waitForPageLoad();
		 Thread.sleep(9000);
	}

	@Then("Create a firstcompany")
	public void create_a_firstcompany() throws Exception {
	    
		 companypage = new CompanyPage(driverMBBAT);
		 companypage.CompanyID().sendKeys(firstCompany);
		 companypage.CompanyName().sendKeys(firstCompany); 
		 companypage.CompanySave().click();
		 waitForPageLoad();
		 Thread.sleep(3000);
	}

	@Then("Edit the Firstcompany and Firstcompany Server Details")
	public void edit_the_Firstcompany_and_Firstcompany_Server_Details() throws Exception {
	    
		wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(firstCompany);
		  companypage.SearchButton().click(); Thread.sleep(10000);
//		  companypage.CompanyServerEdit().click(); waitForPageLoad(); Thread.sleep(3000);
		WebElement companyID=  driverMBBAT.findElement(By.xpath("//span[text()='"+firstCompany+"']"));
		  Actions a = new Actions(driverMBBAT);
			a.moveToElement(companyID).perform();
		  Thread.sleep(5000);
		  driverMBBAT.findElement(By.xpath("//i[@title='Edit']")).click();
		  Thread.sleep(5000);
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(5000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window); } }
	    
	}

	@Then("Save the Firstcompany details")
	public void save_the_Firstcompany_details() throws Exception {
	    
		companypage.DbName().clear(); companypage.DbName().sendKeys(Build);
		companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		companypage.Companypwd().clear();
		companypage.Companypwd().sendKeys("Cellar@123");
		companypage.CompanySave().click(); Thread.sleep(5000);
		driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		waitForPageLoad();
	}

	@Then("logout the Firstcompany")
	public void logout_the_Firstcompany() throws Exception {
		 Thread.sleep(3000);
		usersecurity = new UserSecurity(driverMBBAT);
		usersecurity.Logout().click();
		Thread.sleep(1000);
		usersecurity.Logoutbtn().click();
		waitForPageLoad(); 
		 Thread.sleep(3000);
	}

	@Then("Login into Maxblox default page")
	public void login_into_Maxblox_default_page() throws Exception {
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		
		  MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		  MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys("admin");
		  MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("admin");
		  MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
		  MBdefaultpage.CompanySearch().click(); MBdefaultpage.CompanySearch().clear();
		  MBdefaultpage.CompanySearch().sendKeys(Build+"_first"); Thread.sleep(3000);
		  driverMBBAT.findElement(By.xpath("//span[contains(text(),'"+Build+"_first')]")). click(); waitForPageLoad();
	}

	@Then("Navigate to page designer and create a Firstcompany page")
	public void navigate_to_page_designer_and_create_a_Firstcompany_page() throws Exception {
		 Thread.sleep(3000);
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		 driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/pagedesigner.aspx?pgs_id=b3ef063b174e4ccc&mn=ae79b380c4bee6ba");
		 waitForPageLoad();
		 Thread.sleep(3000);
		 pagedesignerpage = new PageDesigner(driverMBBAT) ;
		 pagedesignerpage.PageName().clear();
		 pagedesignerpage.PageName().sendKeys("Automationpage1");
		 Thread.sleep(3000);
		 pagedesignerpage.TxtName().sendKeys("TextBox1");
		 pagedesignerpage.Desc().sendKeys("Desc");
		 pagedesignerpage.Seq().sendKeys("1");
		 pagedesignerpage.TxtName2().sendKeys("TextBox2");
		 pagedesignerpage.Desc2().sendKeys("Desc2");
		 pagedesignerpage.Seq2().sendKeys("2");
		 ((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
				 pagedesignerpage.Menucheckbox());
		 Thread.sleep(1000);
		 pagedesignerpage.Menucheckbox().click();
		 ((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
				 pagedesignerpage.SaveBtn());
		 Thread.sleep(1000);
		 pagedesignerpage.SaveBtn().click();
		 waitForPageLoad();
		 Thread.sleep(3000);
	}

	@Then("Check the created Firstcompany page in menubar and List page")
	public void check_the_created_Firstcompany_page_in_menubar_and_List_page() {
	    
		List<WebElement> pages= driverMBBAT.findElements(By.xpath("//span[text()='Automationpage1']"));
		   
		String status="";
		System.out.println("Page text count: "+pages.size());
		if(pages.size()>=3) {
			
			status="PASS";
		}
		else {
			status="FAIL";
		}
		
		assertEquals(status, "PASS");
	}

	@When("open Indexpage")
	public void open_Indexpage() throws Exception {
		 Thread.sleep(3000);
		usersecurity.Logout().click();
		Thread.sleep(1000);
		usersecurity.Logoutbtn().click();
		waitForPageLoad();
	    driverMBBAT.navigate().refresh();
	    waitForPageLoad();
		driverMBBAT.get(indexurl);
		waitForPageLoad();
	}

	@Then("login to mxblx Homepage")
	public void login_to_mxblx_Homepage() throws Exception {
	    
		maxBloxindexpage.UserID().clear();
		maxBloxindexpage.UserID().sendKeys(DB_Username);
		maxBloxindexpage.Password().clear();
		maxBloxindexpage.Password().sendKeys(DB_Password);
		maxBloxindexpage.Submitbutton().click();
		waitForPageLoad();
		 Thread.sleep(3000);
	}

	@Then("Navigate to secondcompanypage")
	public void navigate_to_secondcompanypage() throws Exception {
		driverMBBAT.navigate().refresh();
	    waitForPageLoad();
		driverMBBAT.get(companyUrl);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
		    driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
		//	driverMBBAT.get("http://10.199.4.22:81/DisplayGrid.aspx?mn=61e60132941d2c08&pgs_id=5d19260c71518582");
		waitForPageLoad();
		 Thread.sleep(9000);
	}

	@Then("Create secondcompany")
	public void create_secondcompany() throws Exception {
		companypage = new CompanyPage(driverMBBAT);
		  companypage.CompanyID().sendKeys(secondCompany);
		  companypage.CompanyName().sendKeys(secondCompany); companypage.CompanySave().click();
		  waitForPageLoad();
		  Thread.sleep(3000);
	}

	@Then("Edit the secondcompany and secondcompany Server Details")
	public void edit_the_secondcompany_and_secondcompany_Server_Details() throws Exception {
	    
		wait.until(ExpectedConditions.visibilityOf(companypage.SearchBOX()));
		  companypage.SearchBOX().clear(); companypage.SearchBOX().sendKeys(secondCompany);
		  companypage.SearchButton().click(); Thread.sleep(5000);
//		  companypage.CompanyServerEdit().click(); waitForPageLoad();
		  WebElement companyID = driverMBBAT.findElement(By.xpath("//span[text()='"+secondCompany+"']"));
		  Actions a = new Actions(driverMBBAT);
			a.moveToElement(companyID).perform();
		  Thread.sleep(5000);
		  driverMBBAT.findElement(By.xpath("//i[@title='Edit']")).click();
		  Thread.sleep(5000);
		  ((JavascriptExecutor)
		  driverMBBAT).executeScript("arguments[0].scrollIntoView();",
		  companypage.CompanyServerAddNew());
		  companypage.CompanyServerAddNew().click(); Thread.sleep(5000); 
		  String parent=driverMBBAT.getWindowHandle();
		  
		  Set<String>s=driverMBBAT.getWindowHandles();
		  
		  Iterator<String> I1= s.iterator();
		  
		  while(I1.hasNext()) { String child_window=I1.next();
		  
		  if(!parent.equals(child_window)) {
		  driverMBBAT.switchTo().window(child_window); } }
	    
	}

	@Then("Save the secondcompany details")
	public void save_the_secondcompany_details() throws Exception {
	    
		companypage.DbName().clear(); companypage.DbName().sendKeys(Build);
		companypage.DbUser().clear(); companypage.DbUser().sendKeys(DB_Username);
		companypage.DbPwd().clear(); companypage.DbPwd().sendKeys(DB_Password);
		companypage.Companypwd().clear();
		companypage.Companypwd().sendKeys("Cellar@123");
		companypage.CompanySave().click(); Thread.sleep(5000);
		driverMBBAT.switchTo().defaultContent(); companypage.CompanyCancel().click();
		waitForPageLoad();
	}

	@Then("logout the secondcompany")
	public void logout_the_secondcompany() throws Exception {
		 Thread.sleep(3000);
		usersecurity = new UserSecurity(driverMBBAT);
		usersecurity.Logout().click();
		Thread.sleep(1000);
		usersecurity.Logoutbtn().click();
		waitForPageLoad(); 
	}

	@Then("Login into Maxblox Defaultpage")
	public void login_into_Maxblox_Defaultpage() throws Exception {
		
		driverMBBAT.navigate().refresh();
	    waitForPageLoad();
		driverMBBAT.get(defaulturl);
		waitForPageLoad();
		 Thread.sleep(3000);
		  MBdefaultpage = new MaxBloxDefaultPage(driverMBBAT) ;
		  MBdefaultpage.UserID().clear(); MBdefaultpage.UserID().sendKeys("admin");
		  MBdefaultpage.Password().clear(); MBdefaultpage.Password().sendKeys("admin");
		  MBdefaultpage.Submitbutton().click(); waitForPageLoad(); Thread.sleep(5000);
		  MBdefaultpage.CompanySearch().click(); MBdefaultpage.CompanySearch().clear();
		  MBdefaultpage.CompanySearch().sendKeys(Build+"_second"); Thread.sleep(3000);
		  driverMBBAT.findElement(By.xpath("//span[contains(text(),'"+Build+"_second')]")). click(); waitForPageLoad();
		  Thread.sleep(3000);
	    
	}

	@Then("Navigate to page designer and create a secondcompany page")
	public void navigate_to_page_designer_and_create_a_secondcompany_page() throws Exception {
		driverMBBAT.navigate().refresh();
		waitForPageLoad();
		driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/pagedesigner.aspx?pgs_id=b3ef063b174e4ccc&mn=ae79b380c4bee6ba");
		 waitForPageLoad();
		 Thread.sleep(3000);
		 pagedesignerpage = new PageDesigner(driverMBBAT) ;
			pagedesignerpage.PageName().clear();
			pagedesignerpage.PageName().sendKeys("Automationpage1");
			pagedesignerpage.TxtName().sendKeys("TextBox1");
			pagedesignerpage.Desc().sendKeys("Desc");
			pagedesignerpage.Seq().sendKeys("1");
			pagedesignerpage.TxtName2().sendKeys("TextBox2");
			pagedesignerpage.Desc2().sendKeys("Desc2");
			pagedesignerpage.Seq2().sendKeys("2");
			((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
			pagedesignerpage.Menucheckbox());
			Thread.sleep(1000);
			pagedesignerpage.Menucheckbox().click();
			((JavascriptExecutor)driverMBBAT).executeScript("arguments[0].scrollIntoView();",
			pagedesignerpage.SaveBtn());
			Thread.sleep(1000);
			pagedesignerpage.SaveBtn().click();
			waitForPageLoad();
	}

	@Then("Check the created secondcompany page in menubar and Listpage")
	public void check_the_created_secondcompany_page_in_menubar_and_Listpage() {
	    
		List<WebElement> pages= driverMBBAT.findElements(By.xpath("//span[text()='Automationpage1']"));
		   
		String status="";
		System.out.println("Page text count: "+pages.size());
		if(pages.size()>=3) {
			
			status="PASS";
		}
		else {
			status="FAIL";
		}
		
		assertEquals(status, "PASS");
	}
	
	@Then("Navigate to advanced page Designer and create a page")
	public void navigate_to_advanced_page_Designer_and_create_a_page() throws InterruptedException  {
		advancedpagedesignerpage = new AdvancedPageDesignerPage(driverMBBAT) ;
		driverMBBAT.navigate().refresh();
	   driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/PageWizard.aspx?pgs_id=b3ef063b174e4ccc&mn=8dea2ea9c5b031c4");
	    Thread.sleep(1000);
	    advancedpagedesignerpage.Pagename().sendKeys(text);
	    advancedpagedesignerpage.NextBtn().click();
	    Thread.sleep(8000);
	    advancedpagedesignerpage.AddControlBtn().click();
	    Thread.sleep(5000);
	    advancedpagedesignerpage.ControlName().sendKeys("Name");
//	    advancedpagedesignerpage.ControlID().sendKeys("Name");
	    advancedpagedesignerpage.Sequence().sendKeys("1");
	    advancedpagedesignerpage.SaveBtn().click();
	    Thread.sleep(9000);
	    advancedpagedesignerpage.AddControlBtn().click();
	    Thread.sleep(5000);
	    advancedpagedesignerpage.ControlName().sendKeys("Address");
//	    advancedpagedesignerpage.ControlID().sendKeys("Address");
	    advancedpagedesignerpage.Sequence().sendKeys("2");
	    advancedpagedesignerpage.SaveBtn().click();
	    Thread.sleep(9000);
	    advancedpagedesignerpage.FinishBtn().click();
	    Thread.sleep(5000);
	}
	@Then("Navigate to menu designer and add menu for the user")
	public void navigate_to_menu_designer_and_add_menu_for_the_user() throws InterruptedException {
		advancedpagedesignerpage = new AdvancedPageDesignerPage(driverMBBAT) ;
	   driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/DynamicScreen.aspx?pgs_id=f59620d8c01f7e94&mn=abb34ba7407cb3b2");
	   Thread.sleep(2000);
	   advancedpagedesignerpage.NameMenu().sendKeys(text);
	   advancedpagedesignerpage.SelectPageset().sendKeys(text);
	   advancedpagedesignerpage.SaveBtnMenu().click();
	   Thread.sleep(5000);
//	   Alert a = driverMBBAT.switchTo().alert();
//	  a.accept();
	   Thread.sleep(2000);
	   
	}

	@Then("Navigate to Rolesetup page and assign role for the menu")
	public void navigate_to_Rolesetup_page_and_assign_role_for_the_menu() throws InterruptedException {
		advancedpagedesignerpage = new AdvancedPageDesignerPage(driverMBBAT) ;
	    driverMBBAT.get("https://1and1dr.qcommission.net/maxblox/RoleSecurity.aspx?pgs_id=ddf6b56031513254&PK=377e55cec8d53d7d&mn=9013cfc7562dd001");
	    Thread.sleep(5000);
	    advancedpagedesignerpage.MenuSearch().clear();
	    advancedpagedesignerpage.MenuSearch().sendKeys(text);
	    Thread.sleep(8000);
	    driverMBBAT.findElement(By.xpath("(//table)[7]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox']")).click();
	    Thread.sleep(8000);
	    advancedpagedesignerpage.Object().click();
	    advancedpagedesignerpage.ObjectSearch().clear();
	    advancedpagedesignerpage.ObjectSearch().sendKeys(text);
	    Thread.sleep(2000);
//	    driverMBBAT.findElement(By.xpath("((//table)[8]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox'])[1]")).click();
	    Thread.sleep(2000);
	    driverMBBAT.findElement(By.xpath("((//table)[8]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox'])[2]")).click();
	    Thread.sleep(2000);
	    driverMBBAT.findElement(By.xpath("((//table)[8]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox'])[3]")).click();
	    Thread.sleep(2000);
	    driverMBBAT.findElement(By.xpath("((//table)[8]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox'])[4]")).click();
	    Thread.sleep(2000);
	    driverMBBAT.findElement(By.xpath("((//table)[8]//tr//td[contains(text(),'"+text+"')]/following-sibling::td//input[@type='checkbox'])[5]")).click();
	    advancedpagedesignerpage.SaveBtnObject().click();
	    Thread.sleep(5000);
	    
	    
	    
	}

	@Then("Add record to the user")
	public void add_record_to_the_user() throws InterruptedException, Exception {
		driverMBBAT.findElement(By.id("mb_txt_mnuSrch")).sendKeys(text);
		Thread.sleep(1000);
		driverMBBAT.findElement(By.xpath("//span[@class='mbSpnHighLight']")).click();
		Thread.sleep(9000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")));
		driverMBBAT.findElement(By.id("ctl00_ContentPlaceHolder3_btnAddNewTop")).click();
	    Thread.sleep(1000);
        driverMBBAT.findElement(By.xpath("(//input[@class='BindTextbox form-control'])[1]")).sendKeys("Sample");
        driverMBBAT.findElement(By.xpath("(//input[@class='BindTextbox form-control'])[2]")).sendKeys("Chennai");
	    driverMBBAT.findElement(By.id("btn_save_top")).click();
//	    Alert a1 = driverMBBAT.switchTo().alert();
//	    a1.accept();
        	
//        inputData()
	    res = st.executeQuery("select * from ngcompany where email='"+usrId2+"'");
	    String compId="";String afrowId="";
	    if (res.next()) {
			
		
	     compId = res.getString("company_id");
	     afrowId = res.getString("af_row_id");
	    }
	 res2=st.executeQuery("select * from ngcompanyserver where company_id='"+afrowId+"'");
	    String compPwd="";
	    if (res2.next()) {
	    String text1="_123$Mb";
		String compId1=compId;
		StringBuilder s=new StringBuilder(compId1);
		StringBuilder reverse = s.reverse();
		System.out.println(reverse);
		
		 compPwd=reverse+text1;
		System.out.println(compPwd);
		System.out.println("Succesfully got Company password from Backend");
	    }
//		try
//		{
//		Class.forName("net.sourceforge.jtds.jdbc.Driver");
//		con=DriverManager.getConnection("jdbc:jtds:sqlserver://"+Server_Name+";databaseName=MaxBlox_Shared_01;instance="+Instance+";integratedSecurity=true", ""+compId+"", ""+compPwd+"");
//		st = con.createStatement();
//		res = null;
//		if (res.next()) {
//		st.executeQuery("select * from bu_"+text+"");
//		String name = res.getString("Name");
//		String address=res.getString("Address");
//		org.testng.Assert.assertEquals(name,"Sample");
//		org.testng.Assert.assertEquals(address,"Chennai");
//		System.out.println("Succesfully verified from Backend");
//		}
//		} catch (Exception e)
//		{
//		//s = ExceptionUtils.getStackTrace(e);sendMailException();
//			e.printStackTrace();
//		}
		
		
		
		
		
	    
	    
	    
	    
	}

	
	
	

}

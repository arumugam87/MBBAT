package com.mb.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompanyPage {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public CompanyPage(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
//	@FindBy(css="#ctl00_ContentPlaceHolder3_p_ngcompany_company_id")
//	 WebElement companyID;
	
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_p_ngcompany_company_id']")
	 WebElement companyID;
	
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_p_ngcompany_company_name")
	WebElement companyName;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_btnSaveTop")
	WebElement companySave;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_c47e60cf5f63f3daTop")
	WebElement companyserverAddNew;
	
	@FindBy(xpath="//i[@title='Edit']")
	WebElement companyserverEdit;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_p_ngcompanyserver_dbname']")
	WebElement dbName;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_p_ngcompanyserver_dbuser']")
	WebElement dbUser;
	
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_p_ngcompanyserver_dbpassword']")
	WebElement dbPwd;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_p_ngcompanyserver_company_password']")
	WebElement companypwd;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_btnUpdateTop']")
	WebElement companyupdate;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_btnCancelTop']")
	WebElement companyCancel;
	
	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_txtObjectSearch']")
	WebElement searchBOX;
	
	@FindBy(xpath="//div[@class='input-group-addon serachIconstyle']")
	WebElement searchButton;
	
	@FindBy(xpath="//span[@id='ctl00_lbl_UserName_LogOut']")
	WebElement logout;
	
	@FindBy(xpath="//span[text()='Logout']")
	WebElement logoutButton;
	
	
	public WebElement CompanyID() {
		
		return companyID;	
	}
	
	public WebElement CompanyName() {
		
		return companyName;
	}
	
	public WebElement CompanySave() {
	
		return companySave;
	}
	
	public WebElement CompanyServerAddNew() {
		
		return companyserverAddNew;
	}
	
	public WebElement CompanyServerEdit() {
		
		return companyserverEdit;
	}
	
	public WebElement DbName() {
		
		return dbName;
	}
	
	public WebElement DbUser() {
	
		return dbUser;
	}
	
	public WebElement DbPwd() {
	
		return dbPwd;
	}
	
	public WebElement Companypwd() {
	
		return companypwd;
	}
	
	public WebElement CompanyUpdate() {
		
		return companyupdate;
	}
	
	public WebElement CompanyCancel() {
		
		return companyCancel;
	}
	
	public WebElement SearchBOX() {
		
		return searchBOX;
	}
	
	public WebElement SearchButton() {
		
		return searchButton;
	}
	
	public WebElement Logout() {
		
		return logout;
	}

	public WebElement LogoutButton() {
	
	return logoutButton;
	
	}

}

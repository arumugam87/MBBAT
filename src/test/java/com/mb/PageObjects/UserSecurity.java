package com.mb.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserSecurity {

	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public UserSecurity(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_user_id")
	WebElement userId;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_first_name")
	WebElement firstname;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_last_name")
	WebElement lastname;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_email_id")
	WebElement email;
	
	@FindBy(xpath="//div[contains(text(),'- Select One -')]")
	WebElement hintques;
	
	@FindBy(xpath="//span[contains(text(),'What is your favorite sports team?')]")
	WebElement hintques2;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_hint_answer")
	WebElement hintans;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_TB_password")
	WebElement pwd;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_User_Wizard_FinishNavigationTemplateContainerID_FinishButton")
	WebElement savebtn;
	
	@FindBy(css="#ctl00_lbl_UserName_LogOut")
	WebElement logout;
	
	@FindBy(xpath="//header/nav[@id='mainNavBar']/div[1]/ul[1]/li[3]/a[1]")
	WebElement logout2;
	
	@FindBy(xpath="//span[text()='Logout']")
	WebElement logoutbtn;
	
	public WebElement UserId() {
		
		return userId;
	}
	
	public WebElement FirstName() {
		
		return firstname;
	}
	
	public WebElement LastName() {
		
		return lastname;
	}
	
	public WebElement Email() {
		
		return email;
	}
	
	public WebElement Hintques() {
		
		return hintques;
	}
	
	public WebElement Hintques2() {
		
		return hintques2;
	}
	
	public WebElement Hintans() {
		
		return hintans;
	}
	
	public WebElement PWD() {
		
		return pwd;
	}
	
	public WebElement Savebtn() {
		
		return savebtn;
	}
	
	public WebElement Logout() {
		
		return logout;
	}
	
	public WebElement Logout2() {
		
		return logout2;
	}
	
	public WebElement Logoutbtn() {
		
		return logoutbtn;
	}
}

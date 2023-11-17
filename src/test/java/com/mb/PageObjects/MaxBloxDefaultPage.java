package com.mb.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mb.Utility.BaseProperty;



public class MaxBloxDefaultPage extends BaseProperty {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public MaxBloxDefaultPage(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
	@FindBy(css="#tb_userid")
	WebElement userName;
	
	@FindBy(css="#tb_password")
	WebElement password;
	
	@FindBy(css="#bt_login")
	WebElement submit;
	
	@FindBy(css="#bt_login")
	List<WebElement> sigin;
	
	@FindBy(xpath="//body[starts-with(@title,'Version')]")
	WebElement tooltip;
	
	@FindBy(xpath="//input[@id='txt_search']")
	WebElement companysearch;
	
	@FindBy(xpath="//span[@id='ctl00_lbltitle']")
	List<WebElement> feedback;
	
	
	
	public WebElement UserID() {
		
		return userName;
	}
	
	public WebElement Password() {
		
		return password;
	}
	
	public WebElement Submitbutton() {
	
		return submit;
	}
	
	public List<WebElement> login() {
		
		return sigin;
	}
	
	public WebElement Tooltip() {
		
		return tooltip;
	}
	
	public WebElement CompanySearch() {
		
		return companysearch;
		}
	
	public List<WebElement>  FeedBack() {
		
		return feedback;
		}
	

}

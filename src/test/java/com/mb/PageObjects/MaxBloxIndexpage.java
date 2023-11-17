package com.mb.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MaxBloxIndexpage {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
public MaxBloxIndexpage(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}

	@FindBy(css="#tb_user_id")
	WebElement userName;

	@FindBy(css="#tb_password")
	WebElement password;

	@FindBy(css="#btn_submit")
	WebElement submit;

	@FindBy(css="#btn_submit")
	List<WebElement> sigin;
	
	
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
	

}

package com.mb.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageDesigner {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public PageDesigner(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_page_name")
	WebElement pagename;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_name_0")
	WebElement txtname;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_desc_0")
	WebElement desc;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_seq_0")
	WebElement seq;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_name_1")
	WebElement txtname2;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_desc_1")
	WebElement desc2;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_txt_seq_1")
	WebElement seq2;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_cb_once_save")
	WebElement menucheckbox;
	
	@FindBy(css="#ctl00_ContentPlaceHolder3_btnSave")
	WebElement savebtn;
	
	
	public WebElement PageName() {
		
		return pagename;
	}
	
public WebElement TxtName() {
		
		return txtname;
	}

public WebElement Desc() {
	
	return desc;
}

public WebElement Seq() {
	
	return seq;
}

public WebElement TxtName2() {
	
	return txtname2;
}

public WebElement Desc2() {
	
	return desc2;
}

public WebElement Seq2() {
	
	return seq2;
}

public WebElement Menucheckbox() {
	
	return menucheckbox;
}

public WebElement SaveBtn() {
	
	return savebtn;
}

}

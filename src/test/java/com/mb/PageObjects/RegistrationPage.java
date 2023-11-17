package com.mb.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public RegistrationPage(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
	@FindBy(css="#txt_user_id")
	WebElement userIdEmail;
	
	@FindBy(css="#txt_first_name")
	WebElement firstName;
	
	@FindBy(css="#txt_last_name")
	WebElement lastName;
	
	@FindBy(css="#txt_new_pwd")
	WebElement pwd;
	
	@FindBy(css="#txt_confirm_pwd")
	WebElement conPwd;
	
	@FindBy(xpath="//button[@data-id='dd_pwd_hint']")
	WebElement pwdHint;
	
	@FindBy(xpath="//span[contains(text(),'What is your favorite sports team?')]")
	WebElement pwdHintchoose;
	
	@FindBy(css="#txt_hint_ans")
	WebElement hintAns;
	
	@FindBy(css="#txt_company_name")
	WebElement companyName;
	
	@FindBy(css="#txt_address")
	WebElement adrs;
	
	@FindBy(css="#txt_city")
	WebElement city;
	
	@FindBy(css="#txt_postal_code")
	WebElement psCode;
	
	@FindBy(css="#txt_phone")
	WebElement phone;
	
	@FindBy(xpath="//div[contains(text(),'United States of America')]")
	WebElement country;
	
	@FindBy(xpath="//body/div[1]/div[1]/div[1]/input[1]")
	WebElement countrychoose;
	
	@FindBy(xpath="//body/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]/span[1]")
	WebElement countrychoose2;
	
	@FindBy(xpath="//div[contains(text(),'-- US/Canada Only --')]")
	WebElement state;
	
	@FindBy(xpath="//body/div[1]/div[1]/div[1]/input[1]")
	WebElement statechoose;
	
	@FindBy(xpath="//span[contains(text(),'Alabama - AL')]")
	WebElement statechoose2;
	
	@FindBy(css="#cbTerms")
	WebElement termsCon;
	
	@FindBy(css="#btn_register")
	WebElement regBtn;
	
	@FindBy(xpath="//a[@class='actLink']")
	WebElement actLink;
	
	public WebElement UserIDEmail() {
		
		return userIdEmail;
	}
	
public WebElement FirstName() {
		
		return firstName;
	}

public WebElement LastName() {
	
	return lastName;
}

public WebElement Pwd() {
	
	return pwd;
}

public WebElement ConPwd() {
	
	return conPwd;
}

public WebElement PwdHint() {
	
	return pwdHint;
}

public WebElement PwdHintChoose() {
	
	return pwdHintchoose;
}

public WebElement HintAns() {
	
	return hintAns;
}

public WebElement CompanyName() {
	
	return companyName;
}

public WebElement Adrs() {
	
	return adrs;
}

public WebElement city() {
	
	return city;
}

public WebElement PsCode() {
	
	return psCode;
}

public WebElement Phone() {
	
	return phone;
}

public WebElement country() {
	
	return country;
}

public WebElement CountryChoose() {
	
	return countrychoose;
}

public WebElement CountryChoose2() {
	
	return countrychoose2;
}

public WebElement State() {
	
	return state;
}

public WebElement statechoose() {
	
	return statechoose;
}

public WebElement statechoose2() {
	
	return statechoose2;
}

public WebElement TermsCon() {
	
	return termsCon;
}

public WebElement RegBtn() {
	
	return regBtn;
}

public WebElement ActLink() {
	
	return actLink;
}

}

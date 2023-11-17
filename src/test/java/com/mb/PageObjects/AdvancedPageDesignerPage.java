package com.mb.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mb.Utility.BaseProperty;


public class AdvancedPageDesignerPage extends BaseProperty {
	
	WebDriver driverMBBAT;
	WebDriverWait wait;
	
	public AdvancedPageDesignerPage(WebDriver driverMBBAT) {
		
		this.driverMBBAT = driverMBBAT;
		PageFactory.initElements(driverMBBAT, this);
		wait = new WebDriverWait(driverMBBAT,30);
	}
	
	@FindBy(id="ctl00_ContentPlaceHolder3_pagewizard_txtpsname")
	WebElement pagename;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_pagewizard_StartNavigationTemplateContainerID_StartNextButton")
	WebElement nextBtn;
	
	@FindBy(xpath="//button[@class='btn btn-info btn-sm']")
	WebElement addControlBtn;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_txt_control_name")
	WebElement controlName;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_txt_control_id")
	WebElement controlID;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_txt_seq")
	WebElement sequence;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_dd_typeobject")
	WebElement controlType;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_imgbtn_save")
	WebElement saveBtn;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_pagewizard_StepNavigationTemplateContainerID_FinishButton")
	WebElement finishBtn;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_p_mnu_menu_name")
	WebElement nameMenu;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_txt_p_mnu_pageset_id")
	WebElement selectPageset;
	
	@FindBy(id="ctl00_ContentPlaceHolder3_btnSaveTop")
	WebElement saveBtnMenu;
	
	@FindBy(xpath="//input[@id='textsearch_menu']")
	WebElement menuSearch;
	
	@FindBy(xpath="//a[text()='Object']")
	WebElement object;
	
	@FindBy(xpath="//input[@id='textsearch_object']")
	WebElement objectSearch;
	

	@FindBy(xpath="//input[@id='ctl00_ContentPlaceHolder3_btnUpdate_bottom']")
	WebElement saveBtnObject;


	public WebElement Pagename() {
		return pagename;
	}


	public WebElement NextBtn() {
		return nextBtn;
	}


	public WebElement AddControlBtn() {
		return addControlBtn;
	}


	public WebElement ControlName() {
		return controlName;
	}


	public WebElement ControlID() {
		return controlID;
	}


	public WebElement Sequence() {
		return sequence;
	}


	public WebElement ControlType() {
		return controlType;
	}


	public WebElement SaveBtn() {
		return saveBtn;
	}


	public WebElement FinishBtn() {
		return finishBtn;
	}


	public WebElement NameMenu() {
		return nameMenu;
	}


	public WebElement SelectPageset() {
		return selectPageset;
	}


	public WebElement SaveBtnMenu() {
		return saveBtnMenu;
	}


	public WebElement MenuSearch() {
		return menuSearch;
	}


	public WebElement Object() {
		return object;
	}


	public WebElement ObjectSearch() {
		return objectSearch;
	}


	public WebElement SaveBtnObject() {
		return saveBtnObject;
	}
	


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

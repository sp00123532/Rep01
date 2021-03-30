package com.freecrm.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.objectrepository.OR;
import com.freecrm.util.BaseClass;

public class LoginPage extends BaseClass {
	@FindBy(xpath = OR.uNamex)
	public WebElement uNameTxt;

	@FindBy(xpath = OR.passWordx)
	public WebElement passWordTxt;

	@FindBy(xpath = OR.loginBtnx)
	public WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this); // Use this code for all the web pages
	}
	
	public void doLogin() {
		try {
			uNameTxt.sendKeys(p.getProperty("uName"));
			passWordTxt.sendKeys(p.getProperty("passWord"));
			loginBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package com.freecrm.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.freecrm.objectrepository.OR;
import com.freecrm.util.BaseClass;

public class CompaniesPage extends BaseClass{

	public CompaniesPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=companiesX)
	public WebElement companeisLink;
	
	@FindBy(xpath=newBtnx)
	public WebElement newBtn;
	
	@FindBy(xpath=companyNameX)
	public WebElement companyNameTxt;
	
	@FindBy(xpath=companyUrlx)
	public WebElement companyUrlTxt;
	
	@FindBy(xpath=companyAddressx)
	public WebElement companyAddresstxt;
	
	@FindBy(xpath=cityx)
	public WebElement cityTxt;
	
	@FindBy(xpath=statex)
	public WebElement stateTxt;
	
	@FindBy(xpath=zipX)
	public WebElement zipTxt;
	
	@FindBy(xpath=boxXpath)
	public WebElement Countrybox;
	
	@FindBy(xpath=countryXpath)
	public WebElement countryDrop;
	
	@FindBy (xpath=saveBtnx)
	public WebElement saveBtn;
	
	public void createCompany(String cName,String comUrl,String cAddress,String cCity,String cState,String cZip,String coutry,String value,String tag) {
		try {
			Thread.sleep(4000);
			companeisLink.click();
			newBtn.click();
			companyNameTxt.sendKeys(cName);
			companyUrlTxt.sendKeys(comUrl);
			companyAddresstxt.sendKeys(cAddress);
			cityTxt.sendKeys(cCity);
			stateTxt.sendKeys(cState);
			zipTxt.sendKeys(cZip);
			selectDropLooklike(countryDrop, coutry, "span");
			Thread.sleep(4000);
			saveBtn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editCompany() { //do changes as above
		try {
			driver.findElement(By.xpath(companiesX)).click();
			driver.findElement(By.xpath(editCompanyX)).click();
			driver.findElement(By.xpath(companiesX)).sendKeys("");
			driver.findElement(By.xpath(saveBtnx)).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void deleteCompany() { //do changes as above
		try {
			driver.findElement(By.xpath(companiesX)).click();
			driver.findElement(By.xpath(deleteLinkx)).click();
			driver.findElement(By.xpath(deletebtnx)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
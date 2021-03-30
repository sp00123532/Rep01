package com.freecrm.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.freecrm.util.BaseClass;

public class DealsPage extends BaseClass {
	@FindBy(xpath = dealsX)
	public WebElement dealsLink;

	@FindBy(xpath = newBtnx)
	public WebElement newBtn;

	@FindBy(xpath = dealRolex)
	public WebElement dealRle;

	@FindBy(xpath = saveBtnx)
	public WebElement saveBtn;

	public void createDeal(String dealTitle) {
		try {
			dealsLink.click();
			newBtn.click();
			dealRle.sendKeys(dealTitle);
			saveBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package com.freecrm.tests;
import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.pages.CompaniesPage;
import com.freecrm.util.BaseClass;

public class CompanyTest extends CompaniesPage {
	@Test(dataProvider = "getTc1Data")
	public void tc1(HashMap<String, String> map) {
		if (map.get("Runmode").equals("N"))
			new SkipException("Runmode of this test set to no skippingS,");
		
		lp.doLogin();
		cp.createCompany(map.get("CompanyName"), map.get("WebSite"), map.get("Addess"), map.get("City"),
				map.get("State"), map.get("Zip"), map.get("Country"), map.get("Runmode"), "");
	}

// @Test
	public void deleteCompany() {
	}

	@DataProvider
	public Object[][] getTc1Data() {
		return BaseClass.getData("Sheet3", "TC1_createCompany", x);
	}
}
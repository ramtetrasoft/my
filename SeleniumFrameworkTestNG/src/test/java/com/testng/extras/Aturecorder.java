package com.testng.extras;

import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.teastng.testUtil.Base;
import com.testng.pages.LoginPage;

public class Aturecorder {

	public static void main(String[] args) throws Exception 
	{
		
		Base b=new Base();
		Properties pro=b.accessProperties();
		//Open browser
		RemoteWebDriver driver=b.openBrowser("chrome");
		//Create objects to page classes
		//Launch site
		b.launchSite(pro.getProperty("url"));
		LoginPage login=new LoginPage( driver);
		int max=Integer.parseInt(pro.getProperty("maxwait"));
		WebDriverWait w=new WebDriverWait(driver,max);
		w.until(ExpectedConditions.visibilityOf(login.login));
		login.fillUserId("tomsmith"); //parameterization
		login.fillPassword("SuperSecretPassword!");
		login.clicklogin();
		Thread.sleep(3000);
		b.closeSite();
	}

}

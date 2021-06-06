package com.testng.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.teastng.testUtil.Base;

public class LoginPage 
{
	public RemoteWebDriver driver;
	static Logger log = Logger.getLogger(LoginPage.class);
	//Constructor method
		public LoginPage(RemoteWebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
		@FindBy(how=How.NAME, using="username")
		public WebElement username;
		
		@FindBy(how=How.NAME, using="password")
		public WebElement password;
		
		@FindBy(how=How.XPATH, using="//button[@type='submit']")
		public WebElement login;
		
		@FindBy(how=How.XPATH, using="//i[text()=' Logout']")
		public WebElement Logout;
		
		@FindBy(how=How.XPATH, using="//div[contains(text(),'username is invalid!')]")
		public WebElement usrnameinvalid;
		
		@FindBy(how=How.XPATH, using="//div[contains(text(),'password is invalid!')]")
		public WebElement passwordinvalid;
		
		public String validatelogin(String criteria) throws Exception
		{
			Thread.sleep(10000); //fixed wait before validations
			try
			{
				log.info("validatelogin function is initiated");
				if(criteria.equalsIgnoreCase("all_valid") && Logout.isDisplayed())
				{
					log.info("validatelogin all_valid condition is executed");
					return("Test Passed");
				}
				else if(criteria.equalsIgnoreCase("username_blank") && usrnameinvalid.isDisplayed())
				{
					log.info("validatelogin username_blank condition is executed");
					return("Test Passed");
				}
				else if(criteria.equalsIgnoreCase("username_invalid") && usrnameinvalid.isDisplayed())
				{
					log.info("validatelogin username_invalid condition is executed");
					return("Test Passed");
				}
				else if(criteria.equalsIgnoreCase("password_blank") && passwordinvalid.isDisplayed())
				{
					log.info("validatelogin password_blank condition is executed");
					return("Test Passed");
				}
				else if(criteria.equals("password_invalid") && passwordinvalid.isDisplayed())
				{
					log.info("validatelogin password_invalid condition is executed");
					return("Test Passed");
				}
				else
				{
					String temp=Base.screenshot();
					return("Test Failed & goto "+temp);
				}
			}
			catch(Exception ex)
			{
				String temp=Base.screenshot();
				return("Test interrupted due to "+ex.getMessage()+" & goto "+temp);
			}	
		}
		
		//Operational methods
		public String fillUserId(String x)
		{
			username.sendKeys(x);
			log.info("fillUserId function is executed");
			return("Done");
		}
		
		public String fillPassword(String x)
		{
			password.sendKeys(x);
			log.info("fillPassword function is executed");
			return("Done");
		}
		
		public String clicklogin()
		{
			login.click();
			log.info("clicklogin function is executed");
			return("Done");
		}

}

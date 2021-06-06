package com.testng.extras;

import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.teastng.testUtil.Base;
import com.testng.pages.LoginPage;

public class ExtentReports1 {

	public static void main(String[] args) throws Exception 
	{
		ExtentHtmlReporter reporter =new ExtentHtmlReporter("./reports/loginExtentresult.html");
		ExtentReports  extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		ExtentTest  loger=extent.createTest("Login Test");
		loger.log(Status.INFO,"Login to site");
		loger.log(Status.PASS, "Navigated to the specified URL");
		//extent.endTest(reporter);
		extent.flush();
	}

}

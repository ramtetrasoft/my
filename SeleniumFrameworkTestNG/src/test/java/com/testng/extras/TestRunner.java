package com.testng.extras;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.teastng.testUtil.Base;
import com.testng.pages.LoginPage;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class TestRunner
{
	public RemoteWebDriver driver;
	public ExtentHtmlReporter reporter;
	public Base base;
	public LoginPage login;
	public ExtentReports extent;
	public ATUTestRecorder recorder;
	public ExtentTest extloger;
	Logger log= Logger.getLogger(TestRunner.class);
	@BeforeMethod
	public void beforemethod() throws ATUTestRecorderException
	{
		reporter =new ExtentHtmlReporter("./reports/loginExtentresult.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extloger=extent.createTest("Login validation Test");
		log.info("ExtentReports is initiated");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		recorder = new ATUTestRecorder("./reports","RecordedVideo-"+dateFormat.format(date),false);
		log.info("ATUTestRecorder is Started");
		}
	@AfterMethod
	public void aftermethod()throws ATUTestRecorderException
	{
		extent.flush();
		log.info("ExtentReports is initiated");
		recorder.stop();
		log.info("ATUTestRecorder is Stoped");
	}
	@Test
	public void loginvalidation() throws Exception
	{
		String fp=System.getProperty("user.dir");
		//Open Excel file
		File f=new File(fp+"\\src\\test\\resources\\Book1testng.xlsx"); //saved in project folder
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		//Get tests info
		Sheet sh1=wb.getSheet("Sheet1"); 
		int nour1=sh1.getPhysicalNumberOfRows(); //count of used rows
		int nouc1=sh1.getRow(0).getLastCellNum(); //count of used columns
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		Cell rc1=sh1.getRow(0).createCell(nouc1);
		rc1.setCellValue("Test results on "+sf.format(dt));
		//get steps info
		Sheet sh2=wb.getSheet("Sheet2");
		int nour2=sh2.getPhysicalNumberOfRows(); //count of used rows
		int nouc2=sh2.getRow(0).getLastCellNum(); //count of used columns
		Cell rc2=sh2.getRow(0).createCell(nouc2);
		rc2.setCellValue("Step results on "+sf.format(dt));
		//Create object to methods class
		
		try
		{
			//Goto Sheet1 for tests
			//1st row(index is 0) have names of columns in Sheet1
			for(int i=1;i<nour1;i++) //from 2nd row(index=1)
			{
				DataFormatter df=new DataFormatter();
				int flag=0;
				//Get tid and mode from Sheet1
				String tid=df.formatCellValue(sh1.getRow(i).getCell(0));
				String mode=df.formatCellValue(sh1.getRow(i).getCell(2));
				if(mode.equalsIgnoreCase("yes"))
				{
					//goto Sheet2 for matching steps
					//1st row(index 0) have names of columns in Sheet2
					for(int j=1;j<nour2;j++) //from 2nd row(index=1)
					{
						String sid=df.formatCellValue(sh2.getRow(j).getCell(0));
						if(tid.equalsIgnoreCase(sid))
						{
							//take step details from sheet2
							String browser=df.formatCellValue(sh2.getRow(j).getCell(1));
							String username=df.formatCellValue(sh2.getRow(j).getCell(2));
							String password=df.formatCellValue(sh2.getRow(j).getCell(3));
							String criteria=df.formatCellValue(sh2.getRow(j).getCell(4));
							base=new Base(driver);
							Properties pro=base.accessProperties();
							//Open browser
							recorder.start();
							RemoteWebDriver driver=base.openBrowser(browser);
							//Create objects to page classes
							//Launch site
							base.launchSite(pro.getProperty("url"));
							login=new LoginPage( driver);
							int max=Integer.parseInt(pro.getProperty("maxwait"));
							WebDriverWait w=new WebDriverWait(driver,max);
							w.until(ExpectedConditions.visibilityOf(login.login));
							//Enter userid and click next
							login.fillUserId(username); //parameterization
							login.fillPassword(password);
							login.clicklogin();
							Thread.sleep(5000); //Mandatory before checking output state
							//userid testing
							//Goto previously collected methods array and search for required
							String result=login.validatelogin(criteria);
							//give step result
							Cell rc=sh2.getRow(j).createCell(nouc2);
							rc.setCellValue(result);
							extloger.log(Status.INFO, "Login test ");
							extloger.log(Status.PASS, "succesfully login");
							//terminate from "for k" because required method executed
							base.closeSite();
							  if(result.contains("Failed") || result.contains("failed")
							  ||result.contains("interrupted")) 
							  { flag=1; 
							  break; 
							  }
							 
						} 
						//if closing
					}  //for J closing 
				}//if closing
						else
						{
							break; //terminate from "for j" because matched steps are completed
						}
					//for I closing
					if(flag==0)
					{
						Cell c=sh1.getRow(i).createCell(nouc1);
						c.setCellValue("Test Passed");
						log.info("Login Test is Passed");
						Reporter.log("Login Test is Passed");
						extloger.log(Status.INFO, "Login test ");
						extloger.log(Status.PASS, "succesfully login");
						base.closeSite();
						Assert.assertTrue(true);
					}
					else
					{
						String filepath=base.screenshot();
						Cell c=sh1.getRow(i).createCell(nouc1);
						c.setCellValue("Test Failed");
						log.info("Login Test is Failed");
						Reporter.log("Login Test is Failed");
						extloger.log(Status.INFO, "Login test ");
						extloger.log(Status.FAIL, "login Failed");
						extloger.addScreenCaptureFromPath(filepath);
						base.closeSite();
						Assert.assertTrue(false);
					}
				}//if closing
			} //for i closing
		 //try block closing
		catch(Exception ex)
		{
			String filepath=base.screenshot();
			System.out.println(ex.getMessage());
			extent.createTest("Login test").fail("Failed");
			log.info("Login Test is Failed");
			Reporter.log("Login Test is Failed");
			extloger.log(Status.INFO, "Login test ");
			extloger.log(Status.FAIL, "login Failed");
			extloger.addScreenCaptureFromPath(filepath);
			base.closeSite();
			Assert.assertTrue(false);
		}
		//Save and close excel
		sh1.autoSizeColumn(nouc1); //auto fit on column size
		sh2.autoSizeColumn(nouc2); //auto fit on column size
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo); //save
		wb.close();
		fo.close();
		fi.close();
	}

}

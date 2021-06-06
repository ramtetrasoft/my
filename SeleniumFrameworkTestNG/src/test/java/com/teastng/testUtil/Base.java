package com.teastng.testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
	Logger log = Logger.getLogger(Base.class);
	//class Properties
		public static RemoteWebDriver driver=null;
		//Constructor method 
		public Base(RemoteWebDriver driver)
		{
			super();
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		//Operational methods
		public Properties accessProperties() throws Exception
		{
			String pp=System.getProperty("user.dir"); //get current project path
			FileInputStream fi=new FileInputStream(
					    pp+"\\src\\test\\resources\\testng.properties");
			Properties pro=new Properties();
			pro.load(fi);
			log.info("accessProperties function is executed");
			return(pro);
		}
		public RemoteWebDriver openBrowser(String bn)
		{
			if(bn.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				ChromeOptions co=new ChromeOptions();
				String[] s=new String[] {"enable-automation"};
				co.setExperimentalOption("excludeSwitches",s);
				driver=new ChromeDriver(co);
			}
			else if(bn.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if(bn.equalsIgnoreCase("opera"))
			{
				WebDriverManager.operadriver().setup();
				driver=new OperaDriver();
			}
			else if(bn.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			log.info("openBrowser function is executed");
			return(driver);
		}
		public void launchSite(String url)
		{
			driver.get(url);
			driver.manage().window().maximize();
			log.info("launchSite function is executed");
		}
		public String screenshot() throws Exception
		{
			SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
			Date dt=new Date();
			String fn=sf.format(dt)+".png";
			File src=driver.getScreenshotAs(OutputType.FILE);
			File dest=new File(fn);
			FileHandler.copy(src,dest);
			log.info("screenshot function is executed");
			return(dest.getAbsolutePath());
		}
		public void closeSite()
		{
			driver.quit();
		}
}

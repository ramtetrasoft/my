package com.teastng.testUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ElementUtil implements InterfaceUtil {

	static Logger log = Logger.getLogger(ElementUtil.class);

	public static WebDriver driver=null;
	
	
	public ElementUtil(WebDriver driver)
	{
		super();
		log.info("ElementUtil WebDriver Initiated");
		this.driver = driver;
	}
	
	public static String screanShot() throws Exception {  
		
		log.info("screanShot is Initiated");           
			SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
			Date dt=new Date();
			String fname=sf.format(dt)+"png";
			File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest=new File(fname);
			FileHandler.copy(src, dest);
			log.info("screanShot functions is executed");
			return(dest.getAbsolutePath());
	}
public static void failScreanShot() throws Exception {  
		
		log.info("failScreanShot is Initiated");           
		String filepath=ElementUtil.screanShot();
		  Reporter.log("Test failed ");
		  Reporter.log("<a href=\""+filepath+"\"><img src=\""+filepath+"\"height=\"100\" width=\"100\"/></a>");
			log.info("failScreanShot functions is executed");
			
	}
	public boolean doSelectByVisibleValue(String locator,String locvalue, String [] array) {   

		boolean flag = false;
		log.info("doSelectByVisibleValue is Initiated");           

		try{ 
			
		   Select select = new Select(ElementUtil.LocateElementBy(locator, locvalue));
		   for(int i=0;i<array.length;i++)
			{
				select.selectByValue(array[i]);
				log.info("deSelectByindex functions is executed:");
				return flag;
			}
		   
			}
		catch(Exception Ex) 
		{
			log.info("doSelectByVisibleValue is has exception could be due to loctor issue please check");
			return flag; 
		}
		return flag;
	}
	
	
	public boolean deselectDropDownValue(String locator,String locvalue, String type, String value) {
	    
		boolean flag = false;
		log.info("DeselectDropDownValue is Initiated");           

		try{
			Select select = new Select(ElementUtil.LocateElementBy(locator, locvalue));
			switch(type) { 
			case "index":
				select.deselectByIndex(Integer.parseInt(value));
				log.info("DeselectDropDownValue By Index is executed");
				break;
			case "value":
				select.deselectByValue(value);
				log.info("DeselectDropDownValue By Value is executed");
				break;
			case "visibletext":
				select.deselectByVisibleText(value);
				log.info("DeselectDropDownValue By visibletext is executed");
				break;
			
			default:
				System.out.println("please pass the correct criteria");
					break;
			}
			return flag;
		}
		catch(Exception Ex) {
			log.info("selectDropDownValue is has exception could be due to loctor issue please check");
			return flag; 
		}
	}
	public static boolean deSelectAll(String locator,String locvalue) {   

		boolean flag = false;
		log.info("deSelectAll  is Initiated");           

		try{ 
		   Select select = new Select(ElementUtil.LocateElementBy(locator, locvalue));
			select.deselectAll();
			flag=true;
			log.info("deSelectAll functions is executed:");
			return flag;
			}
		catch(Exception Ex) 
		{
			log.info("deSelectAll is has exception could be due to loctor issue please check");
			return flag; 
		}
	}
	public static boolean deselectMultipleByValues(String locator,String locvalue, String type, String []array) 
	{
	    boolean flag = false;
		log.info("DeselectDropDownValue is Initiated"); 
		Select select = new Select(ElementUtil.LocateElementBy(locator, locvalue));
		try{
		if(type=="visibletext") 
		{
			for(int i=0;i<array.length;i++)
			{
				select.deselectByVisibleText(array[i]);
				log.info("deSelectByvisibletext functions is executed:");
			}
			flag=true;
			return flag;
		}
		else if(type=="value")
		{
			for(int i=0;i<array.length;i++)
			{
				select.deselectByValue(array[i]);
				log.info("deSelectByvalue functions is executed:");
			}
			flag=true;
			return flag;
		}
		else if(type=="index")
		{
			for(int i=0;i<array.length;i++)
			{
				select.deselectByIndex(Integer.parseInt(array[i]));
				log.info("deSelectByindex functions is executed:");
			}
			flag=true;
			return flag;
		}
		}
		catch(Exception Ex) {
			log.info("DeselectDropDownValue is has exception could be due to loctor issue please check");
			
			return flag; 
		}
		return flag;
	}
  	public boolean selectRadioButton(String locator,String locvalue ) {   

		boolean flag = false;
		log.info("SelectRadio Button Initiated");           

		try{         
			WebElement radio = ElementUtil.LocateElementBy(locator, locvalue);
			radio.click();
			flag=true;
			log.info("SelectRadiobutton functions is executed");
			return flag;
		}
		catch(Exception Ex) {
			System.out.println(Ex.getMessage());
			log.info("SelectRadio button is has exception could be due to loctor issue please check");
			return flag; 
		}
	}
  	public static boolean alertHandling(String type,String keys) 
	{
	    boolean flag = false;
		log.info("AlertHandling is Initiated"); 
		try{
		if(type=="accept") 
		{
			driver.switchTo().alert().accept();
			log.info("Alertaccept functions is executed");
			flag=true;
			return flag;
		}
		else if(type=="dismiss")
		{
			driver.switchTo().alert().dismiss();
			log.info("Alertdismiss functions is executed");
			flag=true;
			return flag;
		}
		else if(type=="gettext")
		{
			String x= driver.switchTo().alert().getText();
			System.out.println(x);
			log.info("AlertgetText functions is executed");
			flag=true;
			return flag;
		}
		else if(type=="sendkeys")
		{
		  driver.switchTo().alert().sendKeys(keys);
		  log.info("AlertSendkeys functions is executed");
			flag=true;
			return flag;
		}
		}
		catch(Exception Ex) {
			log.info("DeselectDropDownValue is has exception could be due to loctor issue please check");
			return flag; 
		}
		return flag;
	}
  	
  	public static WebElement LocateElementBy(String locatortype,String locvalue) 
	{
		log.info("LocateElementBy functions is Initiated"); 
		try{
		if(locatortype=="name") 
		{
			WebElement element=driver.findElement(By.name(locvalue));
			log.info("LocateElementBy name functions is executed");
			return element;
		}
		else if(locatortype=="id")
		{
			WebElement element=driver.findElement(By.id(locvalue));
			log.info("LocateElementBy id functions is executed");
			return element;
		}
		else if(locatortype=="className")
		{
			WebElement element=driver.findElement(By.className(locvalue));
			log.info("LocateElementBy classname functions is executed");
			return element;
		}
		else if(locatortype=="tagName")
		{
			WebElement element=driver.findElement(By.tagName(locvalue));
			log.info("LocateElementBy tagName functions is executed");
			return element;
		}
		else if(locatortype=="linkText")
		{
			WebElement element=driver.findElement(By.linkText(locvalue));
			log.info("LocateElementBy linkText functions is executed");
			return element;
		}
		else if(locatortype=="partialLinkText")
		{
			WebElement element=driver.findElement(By.partialLinkText(locvalue));
			log.info("LocateElementBy partialLinkText functions is executed");
			return element;
		}
		else if(locatortype=="xpath")
		{
			WebElement element=driver.findElement(By.xpath(locvalue));
			log.info("LocateElementBy xpath functions is executed");
			return element;
		}
		else if(locatortype=="cssSelector")
		{
			WebElement element=driver.findElement(By.cssSelector(locvalue));
			log.info("LocateElementBy cssSelector functions is executed");
			return element;
		}
		}
		catch(Exception Ex) {
			log.info("LocateElementBy is has exception could be due to loctor issue please check");
			
		}
		return null;
	}
  	public static List<WebElement> LocateElementsBy(String locatortype, String locvalue) 
	{
		log.info("LocateElementsBy functions is Initiated"); 
		try{
		if(locatortype=="name") 
		{
			List<WebElement> elements=driver.findElements(By.name(locvalue));
			log.info("LocateElementsBy name functions is executed");
			return elements;
		}
		else if(locatortype=="id")
		{
			List<WebElement> elements=driver.findElements(By.id(locvalue));
			log.info("LocateElementsBy id functions is executed");
			return elements;
		}
		else if(locatortype=="className")
		{
			List<WebElement> elements=driver.findElements(By.className(locvalue));
			log.info("LocateElementsBy classname functions is executed");
			return elements;
		}
		else if(locatortype=="tagName")
		{
			List<WebElement> elements=driver.findElements(By.tagName(locvalue));
			log.info("LocateElementsBy tagName functions is executed");
			return elements;
		}
		else if(locatortype=="linkText")
		{
			List<WebElement> elements=driver.findElements(By.linkText(locvalue));
			log.info("LocateElementsBy linkText functions is executed");
			return elements;
		}
		else if(locatortype=="partialLinkText")
		{
			List<WebElement> elements=driver.findElements(By.partialLinkText(locvalue));
			log.info("LocateElementsBy partialLinkText functions is executed");
			return elements;
		}
		else if(locatortype=="xpath")
		{
			List<WebElement> elements=driver.findElements(By.xpath(locvalue));
			log.info("LocateElementsBy xpath functions is executed");
			return elements;
		}
		else if(locatortype=="cssSelector")
		{
			List<WebElement> elements=driver.findElements(By.cssSelector(locvalue));
			log.info("LocateElementsBy cssSelector functions is executed");
			return elements;
		}
		}
		catch(Exception Ex) {
			log.info("LocateElementsBy is has exception could be due to loctor issue please check");
			
		}
		return null;
	}
  	public static void switchToWindowBy(String type, String value)
    {
        try
        {
        Set<String> windows = driver.getWindowHandles();
        log.info("switchToWindowBy functions is Initiated");
        if (windows.size()!= 0)
        {
            for (String window:windows)
            {
            	driver.switchTo().window(window);
            	String x=driver.getTitle();
            	x.trim();
              if (type=="title" && x.equals(value))
              {
            	  System.out.println("switchToWindowBy Title functions is executed");
            	  log.info("switchToWindowBy Title functions is executed");
            	 
              break;
              }
              else if (type=="startswith" && x.startsWith(value))
              {
            	  log.info("switchToWindowBy Title Startswith functions is executed");
              break;
              }
              else if (type=="contains" && x.contains(value))
              {
            	  log.info("switchToWindowBy Title contains functions is executed");
              break;
              }
              else if (type=="endswith" && x.endsWith(value))
              {
            	  log.info("switchToWindowBy Title endsWith functions is executed");
              break;
              }}
           }} catch(Exception Ex) {
   			log.info("switchToWindowBy is has exception could be due to loctor issue please check");
           }
      	}
  	public static void createAndSwitchToWindow(String type)
    {
        try
        {
        log.info("createAndSwitchToWindow functions is executed");
        if(type.equals("tab"))
        {
         driver.switchTo().newWindow(WindowType.TAB);
        log.info("createAndSwitchToTab functions is executed");
		}
        else if(type.equals("window"))
        {
        	driver.switchTo().newWindow(WindowType.WINDOW);
        	log.info("createAndSwitchToWindow functions is executed");	
        }
           
           } catch(Exception Ex) {
   			log.info("createAndSwitchToWindow is has exception could be due to loctor issue please check");
           }
      	}
  	
  	public void switchToFrame(String type, String value)
    {
  		log.info("switchToFrame functions is Initiated");
  		try {
  			if(type=="name")
  			{
  				driver.switchTo().frame(value);	
  				log.info("switchToFrameBy Name functions is executed");
  			}
  			else if(type=="id")
  			{
  				driver.switchTo().frame(value);
  				log.info("switchToFrameBy Id functions is executed");
  			}
  			else if(type=="index")
  			{
  				driver.switchTo().frame(Integer.parseInt(value));
  				log.info("switchToFrameBy Index functions is executed");
  			}}catch(Exception Ex) {
  				System.out.println(Ex.getMessage());
       			log.info("switchToFrame is has exception could be due to locator issue please check");
            }
        }
  	public static void switchToFrameBylocator(String locator,String locvalue)
    {
  		log.info("switchToFrameBylocator functions is Initiated");
  		try {
  				driver.switchTo().frame(LocateElementBy(locator,locvalue));
  				log.info("switchToFrameBylocator functions is executed");
  			}catch(Exception Ex) {
       			log.info("switchToFrameBylocator is has exception could be due to locator issue please check");
            }
        }
  	public static void waitForFrameAndSwitch(String locator,String locvalue)
    {
  		log.info("waitForFrameAndSwitch functions is Initiated");
  		try {
  			WebDriverWait wait=new WebDriverWait(driver,10);
  			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(LocateElementBy(locator,locvalue)));
  			log.info("waitForFrameAndSwitch functions is executed");
  			}catch(Exception Ex) {
       			log.info("waitForFrameAndSwitch is has exception could be due to locator issue please check");
            }
        }
      	}
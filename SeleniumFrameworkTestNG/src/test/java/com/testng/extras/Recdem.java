package com.testng.extras;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import atu.testrecorder.ATUTestRecorder;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Recdem {
	
	public static void main(String[] args) throws Exception 
	{
	DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	Date date = new Date();
	ATUTestRecorder recorder = new ATUTestRecorder("./reports","RecordedVideo-1",false);
	recorder.start();
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get("https://www.ignatiuz.com/");
	
	driver.manage().window().setSize(new Dimension(500,700));
	Thread. sleep(1000);
	driver.manage().window().setSize(new Dimension(700,500));
	Thread. sleep(1000);
	driver.quit();
	recorder.stop();
	}
	}



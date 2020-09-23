package AdditionalSetup;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import Common.Information;
import io.appium.java_client.android.AndroidDriver;

public class NoElement implements Information{
	WebDriver driver;
	@SuppressWarnings("rawtypes")
	AndroidDriver and;
	ExtentTest test;
	boolean cond=false;
	Fluent flu;
	JavaScript js;
	Excelresult exr;
	Locators l;
	ResultScreenshot rs;
	Visible v;
	
	public NoElement(WebDriver driver,ExtentTest test) throws Exception{
		this.driver=driver;
		this.test=test;
		flu=new Fluent(driver);
		js=new JavaScript(driver);
		exr=new Excelresult();
		l=new Locators();
		rs=new ResultScreenshot(driver,test);
		v=new Visible(driver);
	}
	
	@SuppressWarnings("rawtypes")
	public NoElement(AndroidDriver driver,ExtentTest test) throws Exception{
		this.and=driver;
		this.test=test;
		flu=new Fluent(driver);
		exr=new Excelresult();
		rs=new ResultScreenshot(driver,test);
	}
	
	public void testing(Properties p,String[] record,int row, String sh, int resultRow,String status, String[] imp ) throws Exception{
		
			cond=false;
			exr.setData(cond,p,row,sh,resultRow, status,imp);
			try {
				By by=l.getObject(p,record[OBJECTNAME],record[OBJECTTYPE]);
				if(v.isElementPresent(by)){
				js.mark(driver.findElement(by));
				rs.demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				js.nomark(driver.findElement(by));
				}
				else{
					rs.demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				}
			} catch (Exception e) {
				rs.demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
				e.printStackTrace();
			}
		}
	
	public void withoutBy(Properties p,String[] record,int row, String sh, int resultRow,String result,String[] imp ){
		try{
		cond=false;
		exr.setData(cond,p,row,sh,resultRow, result,imp);
		rs.demand(record[STEPNUMBER],record[DESCRIPTION]+". But its failed.",Information.FAIL,imp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
}

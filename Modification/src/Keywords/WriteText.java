package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class WriteText implements Information {
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public WriteText(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj= new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	
	try{
	WebElement writetext=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(writetext);							// highlighting specific element
	obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE])).clear();
	obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE])).sendKeys(record[VALUE]);
	cond= true;
	obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
	obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
	return Information.PASS;
	}
	catch(Exception ne){
		
		NoElement noe=new NoElement(driver,obj.getExtentTest());
		noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		ne.printStackTrace();
		return Information.FAIL;
	}
	
}

public String texting(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
	WebElement calendar=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(calendar);
	cond= obj.getJavaScript().jsWrite(calendar, record[VALUE]);
	String testResult = cond? Information.PASS : Information.FAIL;
	LogStatus reportResult = cond? LogStatus.PASS : LogStatus.FAIL;
	obj.getExcelResult().setData(cond,p,row,sh,resultRow, testResult, imp);
	obj.getExtentTest().log(reportResult, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
	return testResult;
	}
catch(Exception ne){
		
	NoElement noe=new NoElement(driver,obj.getExtentTest());
	noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
	ne.printStackTrace();
	return Information.FAIL;
	}
}

}

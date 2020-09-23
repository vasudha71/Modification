package Keywords;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class Enable implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Enable(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		By endproducts=obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
		if(obj.getVisible().isElementPresent(endproducts)){
			WebElement visible = driver.findElement(endproducts);
			obj.getJavaScript().highlight(visible);
			Thread.sleep(500);
			visible.click();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
			obj.getJavaScript().waitforloading();
			cond=true;
			if(record[VALUE].equalsIgnoreCase("Screen")){
				obj.getJavaScript().mark(visible);
				obj.getResultScreenshot().demand(record[STEPNUMBER], record[OBJECTNAME]+" is clicked",Information.PASS,imp);
				obj.getJavaScript().nomark(visible);
				obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
				return Information.PASS;
			}
			else {
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "record[DESCRIPTION]: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);	
				obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
				return Information.PASS;
			}
		}
		else{
			obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], record[OBJECTNAME]+" is disabled");
			obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.FAIL,imp);
			return Information.FAIL;
		}
		
		}
		catch(Exception ne){
			
			NoElement noe=new NoElement(driver,obj.getExtentTest());
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			ne.printStackTrace();
			return Information.FAIL;
		}
}

}

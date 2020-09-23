package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class Checkbox implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Checkbox(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
	WebElement checkbox=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(checkbox);
	if(!checkbox.isSelected()){					// for select the checkbox
		checkbox.click();
		obj.getJavaScript().waitforloading();
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
		obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
	}
	else if(record[VALUE].equalsIgnoreCase("uncheck")&&checkbox.isSelected()){		// for uncheck checkbox
		checkbox.click();
		obj.getJavaScript().waitforloading();
		cond= true;
		obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.valueOf(cond));
		obj.getExtentTest().log(LogStatus.PASS,record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
	}
	return Information.PASS;
	}
	catch(Exception e){
		NoElement noe=new NoElement(driver,obj.getExtentTest());
		noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		e.printStackTrace();	
	return Information.FAIL;
	}
	}
}


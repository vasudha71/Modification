package Keywords;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class Popup implements Information{
WebDriver driver;
boolean cond=false;
Objects obj;

public Popup(WebDriver driver,ExtentTest test) throws Exception{
this.driver=driver;	
obj = new Objects(driver, test);
}

public String switching(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ){
	try{
	int popup_number=Integer.parseInt(record[VALUE]);
	obj.getPopupWindow().window(popup_number);		// for calling popup method
	obj.getJavaScript().waitforloading();
	cond= true;
	VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
	obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
	obj.getExtentTest().log(LogStatus.PASS,record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
	return Information.PASS;
	}
	catch(Exception e){
		cond= false;
		obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.FAIL,imp);
		obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER], "Not switching to other window");
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
		return Information.FAIL;
	}
}

public String switchingback(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		int popup_number=Integer.parseInt(record[VALUE]);
		driver.close();
	    obj.getPopupWindow().window(popup_number);		// for calling popup method
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		obj.getJavaScript().waitforloading();
		cond= true;
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
		obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
		return Information.PASS;
		}
		catch(Exception e){
			NoElement noe =new NoElement(driver,obj.getExtentTest());
			noe.withoutBy(p, record, row, sh, resultRow, Information.FAIL,imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.FAIL;
		}
}
}

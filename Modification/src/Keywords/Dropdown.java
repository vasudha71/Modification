/*package Keywords;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class Dropdown implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Dropdown(WebDriver driver,ExtentTest test)  throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}

public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception {
	System.out.println(record[VALUE]);
	try{
	WebElement selected=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(selected);
	Select sel=new Select(selected);
	record[VALUE]=record[VALUE].replace("_", " ");
	if(!sel.isMultiple()){
    sel.selectByVisibleText(record[VALUE].trim());
    VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, sel.getFirstSelectedOption().getText().toString());
	}
	else{
		String multiple[]= record[VALUE].split(";");
		for(int b=0;b<multiple.length;b++){
			sel.selectByVisibleText(multiple[b].trim());
		}
		List<String> selectedOptions = new ArrayList<String>();
		for(WebElement options: sel.getAllSelectedOptions())
			selectedOptions.add(options.getText());
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.join(",", selectedOptions));
	}
	obj.getJavaScript().waitforloading();
	cond= true;
	obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
	obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\nOutput: "+record[EXPECTED_COLUMN]);
	return Information.PASS;
	}
	catch(Exception e){
		NoElement noe=new NoElement(driver,obj.getExtentTest());
		noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		e.printStackTrace();
		return Information.FAIL;
	}
}

public String dropdownOptions(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception {
	System.out.println(record[VALUE]);
	try{
	WebElement selected=obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
	obj.getJavaScript().highlight(selected);
	Select sel=new Select(selected);
	List<WebElement> allOptions = sel.getOptions();
	String expectedOptions[] = record[VALUE].split(",");
	List<String> expected = List.of(expectedOptions);
	List<String> actualOptions = new ArrayList<String>();
	for(WebElement options: allOptions) {
		actualOptions.add(options.getText());
	}
	if(expected.equals(actualOptions)) {
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, String.join(",", actualOptions));
		return Information.PASS;
	}else {
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, FAIL);
		return Information.FAIL;
	}
	
	}
	catch(Exception e){
		NoElement noe=new NoElement(driver,obj.getExtentTest());
		noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
		e.printStackTrace();
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		return Information.FAIL;
	}
}
}
*/
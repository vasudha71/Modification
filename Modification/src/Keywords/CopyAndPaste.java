package Keywords;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class CopyAndPaste implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public CopyAndPaste(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		Actions act=new Actions(driver);
		WebElement copy=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(copy);
		act.moveToElement(copy).sendKeys(Keys.chord(Keys.CONTROL,"a")).sendKeys(Keys.chord(Keys.CONTROL,"c")).build().perform();
		return Information.PASS;
		}
		catch(Exception ne){
			
			NoElement noe=new NoElement(driver,obj.getExtentTest());
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			return Information.FAIL;
		}
}

public String paste(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		WebElement paste=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(paste);
		paste.sendKeys(Keys.chord(Keys.CONTROL,"v"));
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

}

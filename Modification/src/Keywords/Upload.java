package Keywords;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class Upload implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Upload(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		Robot robot=new Robot();
		Actions actions=new Actions(driver);
		WebElement upload=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		obj.getJavaScript().highlight(upload);
		new WebDriverWait(driver,30);
		actions.click(upload).perform();
//		obj.getFluent().fluentWait(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE])).click();
//		obj.getJavaScript().jsClick(upload);
	    StringSelection sel1 = new StringSelection(record[VALUE]);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel1,null);
	    System.out.println("selection" +sel1);
	     robot.setAutoDelay(2000);
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 robot.keyRelease(KeyEvent.VK_V);
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 Thread.sleep(2500);

		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 cond= true;
		 VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
			obj.getExcelResult().setData(cond,p,row,sh,resultRow,Information.PASS,imp);
			obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"\r\n Output: "+record[EXPECTED_COLUMN]);
			return Information.PASS;
		}
		catch(Exception ne){
			
			NoElement noe=new NoElement(driver,obj.getExtentTest());
			noe.testing(p, record, row, sh, resultRow, Information.FAIL,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.FAIL;
		}
}

}

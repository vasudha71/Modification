package Keywords;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import AdditionalSetup.NoElement;
import AdditionalSetup.Objects;
import Common.Information;

public class OpenFolder implements Information{
	WebDriver driver;
	Objects obj;
	
public OpenFolder(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj =new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	try{
		
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

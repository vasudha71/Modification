package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.Objects;
import Common.Information;

public class DemandForecastQuaterlyValidation implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
	public DemandForecastQuaterlyValidation(WebDriver driver, ExtentTest test)throws Exception{
		this.driver=driver;
		obj=new Objects(driver, test);
	}
		public String testing(Properties p, String[] record, int rowNum, String sh, int resultRow, String[] imp)
				throws Exception {
				try {
		
	}
			catch (Exception e) {
				// TODO: handle exception
			}
				return sh;
	

}
}
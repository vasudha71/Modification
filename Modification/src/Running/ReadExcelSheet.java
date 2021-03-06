package Running;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jsoup.helper.DataUtil;

import sun.util.calendar.LocalGregorianCalendar.Date;


public class ReadExcelSheet {
XSSFWorkbook wb;
XSSFSheet sheet;

SpreadSheet ods;
Sheet odsSheet;

File f;
FileInputStream fis;
String extension;
public ReadExcelSheet(String path){
	try {
		f= new File(path);
		fis = new FileInputStream(f);
		extension=f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
		if(extension.equals(".xlsx")){
		wb= new XSSFWorkbook(fis);
		}
		else if(extension.equals(".ods")){
			ods=SpreadSheet.createFromFile(f);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
@SuppressWarnings("static-access")
public String readData(String index, int row, int cell) throws ParseException {
	String data;
	if(extension.equals(".xlsx")){
	sheet = wb.getSheet(index);
	Row rows=sheet.getRow(row);
	Cell cell1=rows.getCell(cell);
	if(cell1 != null){
		if(cell1.getCellType()==cell1.CELL_TYPE_NUMERIC){
			
			if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell1)){
				System.out.println("Date fprmat before conversion"+cell1);
				DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		        java.util.Date date = sdf.parse(String.valueOf(cell1));
		        data = new SimpleDateFormat("MM-dd-yyyy").format(date);
		        System.out.println("Date format after conversion"+date);
				/*System.out.println("Date value before converting"+cell1);
				SimpleDateFormat dateFormat=new SimpleDateFormat("MM-DD-YYYY");  
				data=dateFormat.format(cell1.getDateCellValue());
				System.out.println("Date value after converting"+data);*/
				}else{
			data= NumberToTextConverter.toText(cell1.getNumericCellValue());
		}
		}
		else{
		data=cell1.toString();
		}
	}
	else{
		data= " ";
	}
	return data;
	}
	else if(extension.equals(".ods")){
		odsSheet=ods.getSheet(index);
		@SuppressWarnings("rawtypes")
		MutableCell odscell=null;
		odscell=odsSheet.getCellAt(cell, row);
		data=odscell.getValue().toString();
		return data;
	}
	else{
		return "empty";
	}
}

public int columnCount(String index){
	sheet=wb.getSheet(index);
	int numColumns=sheet.getRow(0).getPhysicalNumberOfCells();
	return numColumns;
}
public int count(String index){
	int totalrows=0;
	sheet = wb.getSheet(index);
	totalrows=sheet.getLastRowNum();
	return totalrows;
}

public boolean exists(String index){
	try {
		sheet = wb.getSheet(index);
		sheet.getLastRowNum();
		return true;
	} catch (Exception e) {
		System.out.println("Error at exists method in ReadExcelsheet");
		return false;
	}
}

public void removeCells(String name, int cellNumber) throws Exception{
	
	sheet=wb.getSheet(name);
	CellStyle style=wb.createCellStyle();
	for (int i=2;i<=count(name);i++){
		Row getRow=sheet.getRow(i);
		Cell cell1=getRow.getCell(cellNumber);
		if(cell1!=null){
		getRow.removeCell(cell1);
		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cell1= sheet.getRow(i).createCell(cellNumber);
		cell1.setCellStyle(style);
		FileOutputStream dest= new FileOutputStream(f);
		wb.write(dest);
		}
	}
	
}
}

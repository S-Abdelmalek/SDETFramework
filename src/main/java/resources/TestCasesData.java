package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCasesData {
	
	public ArrayList <String> getDataFromExcelSheet (String tcName) throws IOException
	{
		FileInputStream f = new FileInputStream("src//main//java//resources//DataDrivenTestWB.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(f);
		int noOfSheets = workBook.getNumberOfSheets();
		for (int i=0 ; i<noOfSheets;i++)
		{
			if (workBook.getSheetName(i).contains("testdata"))
			{
				XSSFSheet desiredSheet = workBook.getSheetAt(i);
				Iterator<Row> rowsIterator = desiredSheet.iterator();
				Row r = rowsIterator.next();
				Iterator <Cell> c =  r.cellIterator();
				int colIndex = 0;
				int colIndexDesired = 0 ;
				while (c.hasNext())
				{
					Cell FirstRowCellvalue = c.next();
					if (FirstRowCellvalue.getStringCellValue().contains("cases"))
					{
						colIndexDesired = colIndex;
					}colIndex++;
					
				}
				while (rowsIterator.hasNext())
				{
					Row currentRow = rowsIterator.next();
					if (currentRow.getCell(colIndexDesired).getStringCellValue().equalsIgnoreCase(tcName))
					{
						ArrayList <String> cellValues = new  ArrayList <String>();
						//You reached the desired TC, pull all data of the row
						Iterator <Cell> iCell = currentRow.cellIterator();
						iCell.next();
						while (iCell.hasNext())
						{
							DataFormatter formatter = new DataFormatter(); 
							cellValues.add(formatter.formatCellValue(iCell.next()));
													
						} return cellValues;
					}
					
				}
				
			}	
		}
		
		return null;
	}

}

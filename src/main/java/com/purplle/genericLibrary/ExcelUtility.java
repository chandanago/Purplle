package com.purplle.genericLibrary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtility  {
	static PropertiesClass property = new PropertiesClass();
	public static HSSFWorkbook workbook;
	 public static HSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 

	/**
	 * This method is used to read data from Excel based on below parameters
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return String 
	 */
	public String readDataFromExcel(String sheetName,int rowNo,int cellNo)  {
		File absPath = new File(property.readDataFromProperty("EXCELDATA_PATH1"));
		Workbook wrkbook=null;
		try {
			FileInputStream fis= new FileInputStream(absPath);
			wrkbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wrkbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).toString();
	}

	/**
	 * This method is used to get count of rows in a particular Sheet
	 * @param sheetName
	 * @return int
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) {
		Workbook wrkbook=null;
		try {
			FileInputStream fis=new FileInputStream(property.readDataFromProperty("EXCELDATA_PATH"));

			wrkbook=WorkbookFactory.create(fis);
		}
		catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wrkbook.getSheet(sheetName).getLastRowNum();
	}


	/**
	 * This method is used to get count of cells in a particular Sheet
	 * @param sheetName
	 * @return int
	 * @throws Throwable
	 */
	public int getCellCount(String sheetName) throws Throwable
	{
		Workbook wrkbook=null;
		try {
			FileInputStream fis=new FileInputStream(property.readDataFromProperty("EXCELDATA_PATH"));

			wrkbook=WorkbookFactory.create(fis);
		}
		catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return wrkbook.getSheet(sheetName).getRow(0).getLastCellNum();
	}


	/**
	 * This method is used to write data to excel file
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param data
	 * @throws Throwable
	 */
	public void setDataToExcel(String sheetName,int row,int cell,String data) 
	{

		Workbook wrkbook=null;
		try {
			FileInputStream fis=new FileInputStream(property.readDataFromProperty("EXCELDATA_PATH"));

			wrkbook=WorkbookFactory.create(fis);
		}
		catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet=wrkbook.getSheet(sheetName);
		sheet.getRow(row).getCell(cell).setCellValue(data);


		FileOutputStream fos;
		try {
			fos = new FileOutputStream(property.readDataFromProperty("EXCELDATA_PATH"));
			wrkbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method is used to read data from excel file and provide data for DataProvider
	 * @param sheetName
	 * @return Object[][]
	 * @throws Throwable
	 * @throws Throwable
	 */
	
	
	public Object[][] getDataUsingDataProvider(String sheetName) 
	{

		Workbook wrkbook=null;
		try {
			FileInputStream fis=new FileInputStream(property.readDataFromProperty("EXCELDATA_PATH"));

			wrkbook=WorkbookFactory.create(fis);
		}
		catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = wrkbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		int cellCount=sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[rowCount][cellCount];
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<cellCount;j++)
			{
		//		data[i][j]=(condition ? null :  sheet.getRow(i).getCell(j).toString());
			}
		}
		return data;
	}


	
	/**
     * Data provider used for 1 product
     *
     * 
     */
    @DataProvider(name="ReadVariant1")
	public static Object[][] ReadVariant() throws IOException
	 {
    	FileInputStream fis=new FileInputStream(property.readDataFromProperty("EXCELDATA_PATH")); //Excel sheet file location get mentioned here
	 HSSFWorkbook workbook = new HSSFWorkbook (fis); //get my workbook 
	  worksheet = workbook.getSheet("removeFromCart");// get my sheet from workbook
	       HSSFRow Row=worksheet.getRow(0);   //get my Row which start from 0   
	   
	    	int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
	    	int ColNum= Row.getLastCellNum(); // get last ColNum 
	    	
	    	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	    	
	     for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	     {  
	     HSSFRow row= worksheet.getRow(i+1);
	     
	     for (int j=0; j<ColNum; j++) //Loop work for colNum
	     {
	     if(row==null)
	     Data[i][j]= "";
	     else 
	     {
	     HSSFCell cell= row.getCell(j);
	     if(cell==null)
	     Data[i][j]= ""; //if it get Null value it pass no data 
	     else
	     {
	     String value=formatter.formatCellValue(cell);
	     Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	     }
	     }
	     }
	     }
	 
	    	return Data;
	    }
	  
}




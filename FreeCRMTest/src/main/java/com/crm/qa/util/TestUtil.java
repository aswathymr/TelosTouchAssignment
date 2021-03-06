package com.crm.qa.util;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

/*ProjectName: Classic CRM Application Framework
 * Description: TestUtil class contains generic functions which can be used across the entire framework
 * Created By:Aswathy
 * Date of Creation: 06-June-2021
 * Version: V1.1
 */

public class TestUtil extends TestBase {
	
	public static long pageLoadTimeout = 20;
	public static long Wait = 20;
	
	public static String currDirectory = System.getProperty("user.dir");
	public static String subPath = currDirectory+"\\src\\main\\java\\com\\crm\\qa";
	public static String configPath = subPath+"\\config\\config.properties";
	public static String testDataSheetPATH = subPath+"\\testdata\\ClassCRMTestData.xls";
	
	public static String driverPath = currDirectory+"\\Drivers\\";
	
	static Workbook book;
	static Sheet sheet;
	
	/*switchToFrame will switch to frame having ID
	 * @param FrameID
	 * Created By: Aswathy
	 * Date of Creation: 06-June-2017
	 */
	
	public void switchToFrame(String frameID) {
		try {
			driver.switchTo().frame(frameID);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*clickUsingJSE will click webelement using Java Script Executor
	 * @param WebElement
	 * Created By: Aswathy
	 * Date of Creation: 06-June-2017
	 */
	public static void clickUsingJSE(WebElement webElement) {
		try
		{
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	js.executeScript("arguments[0].click();", webElement);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*clickUsingJSE will click webelement using Java Script Executor
	 * @param WebElement
	 * Created By: Aswathy
	 * Date of Creation: 06-June-2017
	 */
	public static void waitTillElementVisible(WebElement webElement) {
		try
		{
	    	WebDriverWait  waittime = new WebDriverWait(driver, Wait);
	    	waittime.until(ExpectedConditions.visibilityOf(webElement));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*getTestData will fetch values from excel(TestData)
	 * @param SheetName
	 * Created By: Aswathy
	 * Date of Creation: 06-June-2017
	 */
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(testDataSheetPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

}

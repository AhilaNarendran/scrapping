package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import diabetes.Recipe;

public class ExcelFile {
	static File file = new File("src/test/resources/DiabeticRecipe.xlsx");
	static FileOutputStream fos;
	static XSSFWorkbook workBook = new XSSFWorkbook();
	static XSSFSheet sheet = null;
	static int rowIndex = 1;

	public static void createHeader() {
		sheet = workBook.createSheet("diabetes");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("RecipeId");
		headerRow.createCell(1).setCellValue("RecipeName");
		headerRow.createCell(2).setCellValue("RecipeCategory");
		headerRow.createCell(3).setCellValue("Ingredients");
		headerRow.createCell(4).setCellValue("Preparation Time");
		headerRow.createCell(5).setCellValue("Cooking Time");
		headerRow.createCell(6).setCellValue("Preparation method");
		headerRow.createCell(7).setCellValue("Nutrient value");
		headerRow.createCell(8).setCellValue("Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
		headerRow.createCell(9).setCellValue("Recipe URL");
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addRecipe(Recipe r) {
		XSSFRow row = sheet.createRow(rowIndex++);
		row.createCell(0).setCellValue(r.getrId());
		row.createCell(1).setCellValue(r.getrName());
		row.createCell(2).setCellValue(r.getrCategory());
		row.createCell(3).setCellValue(r.getIngredients());
		row.createCell(4).setCellValue(r.getPreprationTime());
		row.createCell(5).setCellValue(r.getCookTime());
		row.createCell(6).setCellValue(r.getPreprationMethod());
		row.createCell(7).setCellValue(r.getNutrientsValue());
		row.createCell(8).setCellValue("Diabetes");
		row.createCell(9).setCellValue(r.getUrl());

	}
	
	public static void writeAndCloseFile()
	{
		try {
			workBook.write(fos);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package diabetes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelFile;

public class ScrappingDiabetesRecipes {

	WebDriver driver;
	JavascriptExecutor je;
//	Recipe recipe=new Recipe(rId,rName,url, rCategory, foodCategory, ingredients,preprationTime,preprationMethod,cookTime,nutrientsValue)

	// WebDriver driver = new ChromeDriver();
	// JavascriptExecutor je = (JavascriptExecutor) driver;
	@BeforeTest

	public void launchBrowser() {
		// String url = "https://www.tarladalal.com/";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.get("https://www.tarladalal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		je = (JavascriptExecutor) driver;
		System.out.println("1-----");
	}

	@Test(priority = 1)
	public void scrappingRecipes() {
		System.out.println("2-----");
		driver.findElement(By.id("ctl00_txtsearch")).sendKeys("diabetic receipe");
		driver.findElement(By.id("ctl00_imgsearch")).click();
		WebElement PageIndex = driver.findElement(By.xpath("//div[contains(text(),'Goto Page:')]"));
		List<WebElement> listOfPages = PageIndex.findElements(By.tagName("a"));
		int totalPages = listOfPages.size();
		System.out.println("the no of pages" + totalPages);
		for (int i = 0; i <= totalPages; i++) {
			je.executeScript("window.scrollBy(0,500)", "");
			WebElement currentPage = listOfPages.get(i);
			System.out.println("clicked : Page " + (i + 1));
			System.out.println("clicked :  " + currentPage.getText());
			currentPage.click();
//			System.out.println("clicked : Page " + (i + 1));
			getRecipeDetails();
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			PageIndex = driver.findElement(By.xpath("//div[contains(text(),'Goto Page:')]"));
			listOfPages = PageIndex.findElements(By.tagName("a"));

		}
	}

	public void getRecipeDetails() {
		/*
		 * WebElement links =
		 * driver.findElement(By.id("ctl00_cntleftpanel_pnlCatSuggest"));
		 * List<WebElement> noOfRecipes =
		 * links.findElements(By.className("rcpsrch_suggest")); int size =
		 * noOfRecipes.size(); System.out.println("Total no of recipes in a page" +
		 * size); for (int j = 0; j <= size; j++) { WebElement currentRecipe =
		 * noOfRecipes.get(j); currentRecipe.click();
		 * System.out.println("click the link"+j); getScrappingRecipeDetails();
		 * driver.navigate().back(); links =
		 * driver.findElement(By.id("ctl00_cntleftpanel_pnlCatSuggest")); noOfRecipes =
		 * links.findElements(By.className("rcpsrch_suggest"));
		 * 
		 * }
		 */
		List<Recipe> recipeList = new ArrayList<Recipe>();
		WebElement links = driver.findElement(By.id("ctl00_cntleftpanel_pnlCatSuggest"));
		List<WebElement> noOfRecipes = links.findElements(By.className("rcpsrch_suggest"));
		int size = noOfRecipes.size();
		System.out.println("Total no of recipes in a page" + size);
		WebElement currentRecipe = driver.findElement(By.xpath("//a[@href='recipes-for-indian-diabetic-recipes-370']"));
		System.out.println("\t clicking the 1st link" + currentRecipe.getText());
		currentRecipe.click();
		je.executeScript("window.scrollBy(0,10000)", "");

		List<WebElement> articles = driver.findElements(By.xpath("//div[@class='recipelist']/article"));
		int noOfRecipesInaPage = articles.size();
		System.out.println("the no of articles in a current page" + noOfRecipesInaPage);

		WebElement pages = driver.findElement(By.xpath("//div[contains(text(),'Goto Page:')]"));
		List<WebElement> listOfPages = pages.findElements(By.tagName("a"));
		System.out.println("the no of total pages" + listOfPages.size());
		String foodType;

		ExcelFile.createHeader();

		try {
			for (int pageIndex = 1; pageIndex <= listOfPages.size(); pageIndex++) {

				for (int recipeIndex = 1; recipeIndex <= noOfRecipesInaPage; recipeIndex++) {
					try {
					WebElement recipeNo = driver.findElement(
							By.xpath("(//div[@class='recipelist']//article)[" + recipeIndex + "]/div[@class='rcc_rcpno']"));
					String id = recipeNo.getText();
					// System.out.println("RecipeNo" + recipeNo.getText());

					int index = id.indexOf('\n');
					String value = id.substring(0, index);
					System.out.println("RECIPENO" + value);
					WebElement recipeName = driver
							.findElement(By.xpath("//article[" + recipeIndex + "]//span[@class='rcc_recipename']"));
					String rName = recipeName.getText();
					System.out.println("RecipeName" + recipeName.getText());
					WebElement url = driver.findElement(By.xpath("//article[" + recipeIndex + "]//span[@class='rcc_recipename']/a"));
					String rUrl = url.getText();
					System.out.println("The url is" + url.getAttribute("href"));
					WebElement calories = driver
							.findElement(By.xpath("//div[@class='rcplstcal']//span[@class='unitcalamt']"));
					String calorie = calories.getText();
					System.out.println("calories" + calorie);
					/*
					 * JavascriptExecutor je = (JavascriptExecutor) driver;
					 * je.executeScript("window.scrollBy(0,200)"); // WebElement images=
					 * driver.findElement(By.xpath("//artice[@class='rcc_caticons'][1]/img[2]"));
					 * try { WebElement
					 * images=driver.findElement(By.xpath("//a[@itemprop='recipeCategory'][1]"));
					 * 
					 * if(images.getText().contains("breakfast")) foodType=value; }catch
					 * (NoSuchElementException e) {
					 * 
					 * }
					 */
					WebElement images = driver.findElement(By.xpath("//div[@class='rcc_caticons']/img[2]"));

					if (images.isDisplayed()) {
						String image = images.getText();
						if (image == null) {
							String recipeCategory = "notapplicable";

							System.out.println("recipeCategory" + recipeCategory);
						}
					}
					String recipeCatetory = "not applicable";
					url.click();

					je.executeScript("window.scrollBy(0,3500)", "");
					WebElement preprationTime = driver.findElement(By.xpath("//p/time[@itemprop][1]"));
					String pTime = preprationTime.getText();
					WebElement cookingTime = driver.findElement(By.xpath("//p/time[@itemprop][2]"));
					String cTime = cookingTime.getText();
					WebElement ingredients = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
					String ing = ingredients.getText();
					WebElement preprationMethod = driver
							.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']"));
					String method = preprationMethod.getText();
					System.out.println("preprationTime :" + preprationTime.getText());
					System.out.println("cookingTime :" + cookingTime.getText());
					System.out.println("ingredients :" + ingredients.getText());
					System.out.println("preprationMEthod :" + preprationMethod.getText());
					Recipe r = new Recipe(value, rName, rUrl, recipeCatetory, ing, pTime, method, cTime, method, calorie);
					if (Diabetes.checkForDiabetes(r)) {
						// Not a recipe recipe. do not add
					} else {
						// add
						System.out.println("Writing recipe to Excel");
						recipeList.add(r);
						ExcelFile.addRecipe(r);
					}
					}
					catch(Exception exception)
					{
						System.out.println("Exception Caught ");
					}
					driver.navigate().back();

				}
				//listOfPages.get(pageIndex).click();
				WebElement page = driver.findElement(
						By.xpath(
						"//div[@id='pagination']"+"/a["+(pageIndex+1)+"]"
						)
						);
				page.click();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Caught 2 ");
		}finally{
			ExcelFile.writeAndCloseFile();
		}
	}

	// added now

	// listOfPages.get(j).click();
	// driver.navigate().back();

	// driver.navigate().back();
	// System.out.println("\t coming back from the 1st link" +
	// currentRecipe.getText());

	@AfterTest
	public void closeBrowser() {
		// driver.close();
	}
}

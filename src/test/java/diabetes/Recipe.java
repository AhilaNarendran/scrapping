package diabetes;

public class Recipe {
	public String getrId() {
		return rId;
	}



	public void setrId(String rId) {
		this.rId = rId;
	}



	public String getrName() {
		return rName;
	}



	public void setrName(String rName) {
		this.rName = rName;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getrCategory() {
		return rCategory;
	}



	public void setrCategory(String rCategory) {
		this.rCategory = rCategory;
	}



	public String getFoodCategory() {
		return foodCategory;
	}



	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}



	public String getIngredients() {
		return ingredients;
	}



	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}



	public String getPreprationTime() {
		return preprationTime;
	}



	public void setPreprationTime(String preprationTime) {
		this.preprationTime = preprationTime;
	}



	public String getPreprationMethod() {
		return preprationMethod;
	}



	public void setPreprationMethod(String preprationMethod) {
		this.preprationMethod = preprationMethod;
	}



	public String getCookTime() {
		return cookTime;
	}



	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}



	public String getNutrientsValue() {
		return nutrientsValue;
	}



	public void setNutrientsValue(String nutrientsValue) {
		this.nutrientsValue = nutrientsValue;
	}



	String rId;

	String rName;
	String url;
	String rCategory;
	String foodCategory;
	String ingredients;
	String preprationTime;
	String preprationMethod;
	String cookTime;
	String nutrientsValue;
	
	public Recipe(String rId, String rName, String url, String rCategory, String foodCategory, String ingredients,
			String preprationTime, String preprationMethod, String cookTime, String nutrientsValue) {
		super();
		this.rId = rId;
		this.rName = rName;
		this.url = url;
		this.rCategory = rCategory;
		this.foodCategory = foodCategory;
		this.ingredients = ingredients;
		this.preprationTime = preprationTime;
		this.preprationMethod = preprationMethod;
		this.cookTime = cookTime;
		this.nutrientsValue = nutrientsValue;
	}



	public void getRecipeDetails()
	{
		
	}

}

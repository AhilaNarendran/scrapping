package diabetes;

import java.util.Arrays;
import java.util.List;

public class Diabetes {
	
	static List<String> eliminatedList=Arrays.asList("Tofu",
            "Edamame",
            "Tempeh",
            "cauliflower",
            "Broccoli",
            "kale",
            "spinach",
            "sweetpotatoes",
            "strawberries",
            "pinenuts",
            "peanuts",
            "peaches",
            "greentea",
            "coffee",
            "alcohol",
            "soymilk",
            "whitebread",
            "cakes",
            "pastries",
            "friedfood",
            "sugar",
            "processed food ham",
            "bacon",
            "salami",
            "sausages",
            "frozenfood",
            "gluten",
            "sodas",
            "energydrinkscontainingcoffee",
            "caffine",
            "packaged_food_noodles",
            "noodles",
            "siyos",
            "salad_dressings",
            "sauces",
            "peanut butter",
            "brown sugar",
            "chips",
            "sauce",
            "candies");

	//Returns true if Not a diabetic recipe
	public  static boolean checkForDiabetes(Recipe recipe) {
		boolean check=false;
		     
		     for(int i=0;i<eliminatedList.size();i++)
		     {
		    	if(true==recipe.ingredients.toLowerCase().contains( eliminatedList.get(i).toLowerCase()))
		    	{
		    		check=true;
		    		break;
		    	}
		    
		    }
			return check;
		   
				
	}
}

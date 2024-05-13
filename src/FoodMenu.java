import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
	
	private List<Food> menu;
	
	public FoodMenu() {
		menu = new ArrayList<>();
		menu.add(new Food("Pulpo a la Gallega", "Galician styled boiled octopus served on potato bed and seasoned with sweet or spicy paprika", 17));
		menu.add(new Food("Zesty Salmon Filet", "oven-baked salmon filet sersved with a garlic-infused citrusy sauce. Kosher product", 22));
		menu.add(new Food("Hummus Mezze", "an assorment of 4 hummus plates: plain, with avocado, with paprika and with truffle oil. Vegan friendly", 17));
		menu.add(new Food("Redneck Pulled Pork", "hand-pulled pork meat in a fresh bun, topped with homemade coleslaw and BBQ sauce", 18));
		menu.add(new Food("Mother's Roasted Chicken", "tenderly roasted chicken, oven-baked potatoes with rosemary", 12));
		menu.add(new Food("Uncle Jim's Double Trouble", "2 beef patties flamed in whisky and wrapped in bread, lettuce with riracha sauce", 17));
		menu.add(new Food("Nonna Edetta's Pizza", "fresh mozzarella, special home-made salami, tomato sauce, greens", 18));
	}
	
	@Override
	public String toString() {
		String fullString = "";
		int index = 1;
		for (Food item: menu) {
			fullString += index + ". " + item + "\n";
			index++;
		}
		return fullString;
	}
	
	public Food getFood(int index) {
		try {
			return menu.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Food getLowestCostFood() {
		Food cheapest = menu.get(0);
		
		for(Food item: menu) {
			if(item.getPrice() < cheapest.getPrice()) {
				cheapest = item;
			}
		}
		
		return cheapest;
	}

}

import java.util.HashMap;
import java.util.Map;

public class ShoppingBag <T extends PricedItem<Integer>> {
	
	private Map<T, Integer> shoppingBag;
	
	public ShoppingBag() {
		 shoppingBag = new HashMap<>();
	}
	
	public void addItem(T item) {
		if (shoppingBag.get(item) == null) {
			shoppingBag.put(item, 1);
		} else {
			shoppingBag.put(item,  shoppingBag.get(item)+1);
		}
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		for(T item: shoppingBag.keySet()) {
			int itemPrice = item.getPrice();
			int itemQuantity = shoppingBag.get(item);
			totalPrice += itemPrice * itemQuantity;
		}
		return totalPrice;
	}
	
}

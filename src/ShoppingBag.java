import java.util.HashMap;
import java.util.Map;

public class ShoppingBag <T extends PricedItem<Integer>> {
	
	private Map<T, Integer> shoppingBag = new HashMap<>();
	
	public ShoppingBag() {
		// TODO Auto-generated constructor stub
	}

}

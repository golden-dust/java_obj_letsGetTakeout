
public class Food implements PricedItem<Integer> {
	
	private String name;
	private String description;
	int price;
	

	public Food(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}


	@Override
	public Integer getPrice() {
		return this.price;
	}


	@Override
	public void setPrice(Integer price) {
		this.price = price;		
	}
	
	@Override
	public String toString() {
		this.description = "Enjoy Tacos: " + this.name + "\nCost: $" + this.price;
		System.out.println(this.description);
	}

}

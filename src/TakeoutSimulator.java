import java.util.Scanner;

public class TakeoutSimulator {
	
	private Customer customer;
	private FoodMenu menu;
	private Scanner input;
	
	public TakeoutSimulator(Customer customer, Scanner input) {
		this.customer = customer;
		this.input = input;
		menu = new FoodMenu();
	}

	private <T> T getOutputOnIntUserInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {
				
		while(true) {
			System.out.println(userInputPrompt);

			if(input.hasNextInt()) {
				int userInput = input.nextInt();
				input.nextLine();  // 왜 넣는지 확인할 것
				try {
					return (T) intUserInputRetriever.produceOutputOnIntUserInput(userInput);
					// 왜 (T) 넣는지 확인할 것
				} catch(IllegalArgumentException e) {
					System.out.println(userInput + " is not a valid input. Try again.\n");
				}
			} else {
				System.out.println("Input needs to be an 'int' type.\n");
				input.next();  // 왜 넣는지 확인할 것
			}
		}
	}
	
	public boolean shouldSimulate(){
		String userPrompt = "\nEnter 1 to CONTINUE simulation or 0 to EXIT program: ";
		IntUserInputRetriever<Boolean> canProceed = (s) -> {
			if(s == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
				return true;
			} else if(s == 0) {
				System.out.println("\nHope to see you soon!");
				return false;
			} else {
				throw new IllegalArgumentException();
			}
		};
		
		return this.getOutputOnIntUserInput(userPrompt, canProceed);
	}
	
	public Food getMenuSelection() {
		System.out.println("Our Menu Options:\n");
		System.out.println(menu);
		
		String userPrompt = "Choose a menu item (Enter the corresponding number): ";
		
		IntUserInputRetriever<Food> getSelectionInput = (s) -> {
			if(menu.getFood(s) != null) {
				return menu.getFood(s);
			} else {
				throw new IllegalArgumentException();
			}
		};
		return this.getOutputOnIntUserInput(userPrompt, getSelectionInput);
		
	}
	
	public boolean isStillOrderingFood() {
		String userPrompt = "Enter 1 to CONTINUE SHOPPING or 0 to CHECKOUT: ";
		
		IntUserInputRetriever<Boolean> isShopping = (s) -> {
			if(s == 1) {
				return true;
			} else if(s == 0) {
				return false;
			} else {
				throw new IllegalArgumentException();
			}
		};
		
		return this.getOutputOnIntUserInput(userPrompt, isShopping);
		
	}
	
	public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
		System.out.println("\nProcessing payment...");
		
		int remainingMoney = customer.getMoney() - shoppingBag.getTotalPrice();
		customer.setMoney(remainingMoney);
		
		System.out.println("Your balance: $" + customer.getMoney());
		System.out.println("\nThank you and enjoy your food!");
	}
	
	public void takeOutPrompt() {
		ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
		int customerMoneyLeft = customer.getMoney();
		
		boolean stillOrdering = true;
		
		while(stillOrdering) {
			System.out.println("You have $" + customerMoneyLeft + " left to spend\n");
			Food selectedFood = this.getMenuSelection();
			
			if (selectedFood.getPrice() <= customerMoneyLeft) {
				shoppingBag.addItem(selectedFood);
				customerMoneyLeft -= selectedFood.getPrice();
				
			} else {
				System.out.println("Oops! Looks like you don't have enough for that. Choose another item or checkout.");
			}
			stillOrdering = this.isStillOrderingFood();
			if (stillOrdering == false) {
				checkoutCustomer(shoppingBag);
			}
		}
		
	}
	
	public void startTakeOutSimulator() {
		boolean continueSimulating = true;
		
		while(continueSimulating) {
		
			System.out.println("Hello, welcome to my restaurant!\n\nWelcome " + customer + "!");
			
			this.takeOutPrompt();
			continueSimulating = this.shouldSimulate();
		}
	}
	
}

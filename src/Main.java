import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String customerName;
		int money = 0;
		
		while(true) {
			System.out.println("What is your name? ");
			
			try {
				customerName = input.next();
				break;
			} catch(InputMismatchException e) {
				System.out.println("\nName should be in alphabet. Try again.");
				input.nextLine();
			}
		}
		
		boolean validInput = false;
		while(!validInput) {
			System.out.println("\nWhat is your starting money? ");
			try {
				money = input.nextInt();
				if(money <= 0) {
					throw new InputMismatchException();
				}
				validInput = true;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input. Please enter a positive integer.");
				input.nextLine();
			}
		}
		
		Customer customer = new Customer(customerName, money);
		TakeoutSimulator takeOutSimulator = new TakeoutSimulator(customer, input);
		
		takeOutSimulator.startTakeOutSimulator();
			

	}

}

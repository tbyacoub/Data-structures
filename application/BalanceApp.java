package application;

import adt.Balance;

public class BalanceApp {
	
	public static void main(String[] args) {
		Balance b = new Balance("{[(", "}])");
		System.out.println(b.test("(())"));
	}

}

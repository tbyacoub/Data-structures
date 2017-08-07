package application;

import adt.PostFixEvaluator;

public class PostFixCalculator {
	
	public static void main(String[] args) {
		System.out.println(PostFixEvaluator.evaluate("2 5 6 + *"));
	}

}

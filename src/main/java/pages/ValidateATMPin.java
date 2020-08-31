package pages;

import java.util.Arrays;
import java.util.Scanner;

public class ValidateATMPin {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ValidateATMPin AtmPin = new ValidateATMPin();
		System.out.println("Enter your Attm Pin :-");
		String[] numbers = { "9654", "3578", "97A2", "9553", "3687", "98765", "76$2" };
		for (String str : numbers) {
			new ValidateATMPin().validateAtmPin(str);
		}

	}

	private void validateAtmPin(String input) {
		if (input.length() < 4) {
			System.out.println("Invalid Pin");
		} else {
			try {
				int value = Integer.parseInt(input);
				char[] charA = input.toCharArray();
				Arrays.sort(charA);
				String sorted = String.valueOf(charA);
				System.out.println(sorted);
				StringBuilder strReverse = new StringBuilder(sorted);
				strReverse.reverse();
				if(input.equals(sorted) || input.equals(String.valueOf(strReverse)))
					System.out.println("valid pin");
				else
					System.out.println("invalid pin");
				
			} catch (Exception e) {
				System.out.println("Number Format Exception : invalid input");
			}
		}
	}
}
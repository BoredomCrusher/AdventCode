// Java Program to illustrate Reading from FileReader
// using BufferedReader Class

// Importing input output classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

// Main class
public class Problem1 {

	// main driver method
	public static void main(String[] args) throws Exception {

		// File path is passed as parameter
		File file = new File("input1.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st;
		int total1 = 0;
		int total2 = 0;

		Problem1 problem = new Problem1();

		// reads next like while there is is one
		while ((st = br.readLine()) != null) {
			System.out.println(st);

			total1 += problem.getNumbers(st);
			total2 += problem.getRealNumbers(st);

		}

		System.out.println("part one answer: " + total1);
		System.out.println("part 2 answer: " + total2);
	}

	private boolean isDigit(Character input) {
		return Character.isDigit(input);
	}

	private int getNumbers(String st) {
		Character firstNumber = '0';
		Character secondNumber = '0';

		// parse the string from the beginning to the end and the end to the beginning
		// - if the beginning and end both find seperate numbers, concat them
		// - if the beginning and end meet on the same number, concat it with itself
		// - if the two numbers are found, or if the beginning and end pass each other,
		// - move onto the next string

		for (int i = 0; i < st.length(); i++) {
			if (isDigit(st.charAt(i))) {
				firstNumber = st.charAt(i);
				break;
			}
		}

		for (int i = st.length() - 1; i >= 0; i--) {
			if (isDigit(st.charAt(i))) {
				secondNumber = st.charAt(i);
				break;
			}
		}

		if (firstNumber != '0' && secondNumber != '0') {
			return Integer.parseInt("" + firstNumber + secondNumber);
		}

		return 0;
	}

	// only used if isStartOfDigit is true
	private char getStringNumber(String input) {
		HashMap<String, Character> map = new HashMap<>();
		map.put("1", '1');
		map.put("2", '2');
		map.put("3", '3');
		map.put("4", '4');
		map.put("5", '5');
		map.put("6", '6');
		map.put("7", '7');
		map.put("8", '8');
		map.put("9", '9');
		map.put("one", '1');
		map.put("two", '2');
		map.put("three", '3');
		map.put("four", '4');
		map.put("five", '5');
		map.put("six", '6');
		map.put("seven", '7');
		map.put("eight", '8');
		map.put("nine", '9');

		for (Map.Entry<String, Character> entry : map.entrySet()) {
			String key = entry.getKey();
			Character val = entry.getValue();

			if (input.startsWith(key)) {
				return val;
			}
		}
		return '0';
	}

	private int getRealNumbers(String st) {
		Character firstNumber = '0';
		Character secondNumber = '0';

		for (int i = 0; i < st.length(); i++) {
			char stringNumber = getStringNumber(st.substring(i, st.length()));
			if (stringNumber != '0') {
				firstNumber = stringNumber;
				break;
			}
		}

		for (int i = st.length() - 1; i >= 0; i--) {
			char stringNumber = getStringNumber(st.substring(i, st.length()));
			if (stringNumber != '0') {
				secondNumber = stringNumber;
				break;
			}
		}

		if (firstNumber != '0' && secondNumber != '0') {
			return Integer.parseInt("" + firstNumber + secondNumber);
		}

		return 0;
	}
}

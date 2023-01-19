package br.edu.danielkluggy.springproject.converters;

public class NumberConverter {

	public static Double convertToDouble(String strNumber) {
		if (!isNumeric(strNumber))
			return 0D;
		String number = strNumber.replaceAll(",", ".");
		return Double.parseDouble(number);
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}

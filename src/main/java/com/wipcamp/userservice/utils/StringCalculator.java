package com.wipcamp.userservice.utils;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	public static final String DELIMETER = "[,\n]";

	public static int stringCalculate(String inputString) {
		int result = 0;

		if(inputString.isEmpty())
			return result;

		List<Integer> integerList = convertStringToArrayInt(inputString);
		result = sum(integerList);

		return result;
	}

	private static int sum(List<Integer> integerList) {
		int result = 0;
		for (int i: integerList){
			result += i;
		}
		return result;
	}

	private static List<Integer> convertStringToArrayInt(String inputString) {
		ArrayList<Integer> result = new ArrayList<>();
		for(char character: inputString.toCharArray()){
			try{
				result.add(Integer.parseInt(""+character));
			}catch (Exception e){
				continue;
			}
		}
		return result;
	}
}

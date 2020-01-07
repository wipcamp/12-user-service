package com.wipcamp.userservice.utils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

	private void expectedAssertResult(int result, int expectedResult) {
		Assert.assertThat(result, CoreMatchers.equalTo(expectedResult));
	}

	@Test
	void testEmptyString() {
		int result = StringCalculator.stringCalculate("");
		expectedAssertResult(result, 0);
	}


	@Test
	void testOneStringNumber() {
		int result = StringCalculator.stringCalculate("1");
		expectedAssertResult(result, 1);
	}

	@Test
	void testTwoStringNumberWithComma() {
		int result = StringCalculator.stringCalculate("1,2");
		expectedAssertResult(result,3);
	}

	@Test
	void testThreeStringNumberWithComma() {
		int result = StringCalculator.stringCalculate("1,2,3");
		expectedAssertResult(result,6);
	}

	@Test
	void testNumberWithComplicateString() {
		int result = StringCalculator.stringCalculate("//*,\\n1,8*1");
		expectedAssertResult(result,10);
	}
}
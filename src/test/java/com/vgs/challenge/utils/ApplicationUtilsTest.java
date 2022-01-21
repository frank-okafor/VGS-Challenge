package com.vgs.challenge.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class ApplicationUtilsTest {
	final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	void testIsBirthdayValid() throws ParseException {
		LocalDate date = LocalDate.parse("2000-02-23", formater);
		boolean result = ApplicationUtils.isBirthdayValid(date);
		assertThat(result).isEqualTo(true);
	}

	@Test
	void testGetDaysToBirthday() {
		LocalDate date = LocalDate.parse("2000-12-03", formater);
		long result = ApplicationUtils.getOptimizedBirthDay(date);
		assertThat(result).isEqualTo(2l);
	}

}

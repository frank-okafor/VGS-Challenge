package com.vgs.challenge.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ApplicationUtils {

	public static boolean isBirthdayValid(LocalDate birthday) {
		LocalDate today = LocalDate.now();
		return birthday.isBefore(today);
	}

	public static long getOptimizedBirthDay(LocalDate birthDay) {
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		LocalDate nextBirthDate = birthDay.withYear(year);
		if (nextBirthDate.isBefore(today)) {
			nextBirthDate = nextBirthDate.plusYears(1);
		}
		long noOfDaysBetween = ChronoUnit.DAYS.between(today, nextBirthDate);
		return noOfDaysBetween;
	}
}

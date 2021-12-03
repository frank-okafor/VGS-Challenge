package com.vgs.challenge.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationUtils {
    private static final String USERNAME_PATTERN = "[a-zA-Z]+";

    public static boolean isUsernameValid(final String username) {
	Pattern pattern = Pattern.compile(USERNAME_PATTERN);
	Matcher matcher = pattern.matcher(username);
	return matcher.matches();
    }

    public static boolean isBirthdayValid(Date birthday) {
	Date today = new Date();
	return birthday.before(today);
    }

    public static long getDaysToBirthday(String birthdayString) {
	LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int year = currentDate.getYear();
	birthdayString = birthdayString.substring(4);
	String mainBirthDay = year + birthdayString;
	LocalDate birthday = LocalDate.parse(mainBirthDay);
	if (currentDate.getMonthValue() > birthday.getMonthValue()) {
	    year = year + 1;
	    mainBirthDay = year + birthdayString;
	}
	return getDays(mainBirthDay);
    }

    private static long getDays(String birthdayString) {
	LocalDate birthday = LocalDate.parse(birthdayString);
	LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	long noOfDaysBetween = ChronoUnit.DAYS.between(currentDate, birthday);
	return noOfDaysBetween;
    }
}

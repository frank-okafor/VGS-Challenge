package com.vgs.challenge.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApplicationUtilsTest {

    @ParameterizedTest
    @CsvSource({ "frankokafor,true", "frangmail22,false" })
    void testIsUsernameValid(String parameter, boolean expected) {
	boolean result = ApplicationUtils.isUsernameValid(parameter);
	assertThat(result).isEqualTo(expected);
    }

    @Test
    void testIsBirthdayValid() throws ParseException {
	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	Date date = formater.parse("2000-02-23");
	boolean result = ApplicationUtils.isBirthdayValid(date);
	assertThat(result).isEqualTo(true);
    }

    @Test
    void testGetDaysToBirthday() {
	String date = "2000-12-03";
	long result = ApplicationUtils.getDaysToBirthday(date);
	assertThat(result).isEqualTo(2l);
    }

}

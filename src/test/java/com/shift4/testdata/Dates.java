package com.shift4.testdata;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Dates {

    private Dates() {
    }

    static String getNextYearString() {
        return String.valueOf(LocalDate.now().plus(1, ChronoUnit.YEARS).getYear());
    }
}

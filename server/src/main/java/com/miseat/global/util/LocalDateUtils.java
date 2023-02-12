package com.miseat.global.util;

import lombok.experimental.UtilityClass;

import java.time.DayOfWeek;
import java.time.LocalDate;

@UtilityClass
public class LocalDateUtils {

    public static final int WEEK_DAYS = 7;

    /**
     * 해당 요일의 가장 가까운 날짜 반환
     **/
    public LocalDate nextDayOfWeek(LocalDate since, DayOfWeek dayOfWeek) {
        DayOfWeek sinceNextDayOfWeek = since.getDayOfWeek();
        int dayTerm = dayOfWeek.getValue() - sinceNextDayOfWeek.getValue();

        if (dayTerm < 0)
            return since.plusDays(WEEK_DAYS + dayTerm);
        if (dayTerm > 0) {
            return since.plusDays(dayTerm);
        }
        return since;
    }
}

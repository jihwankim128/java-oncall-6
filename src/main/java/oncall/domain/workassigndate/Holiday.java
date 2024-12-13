package oncall.domain.workassigndate;

import java.util.Arrays;

public enum Holiday {

    NEW_YEAR(Month.JANUARY, 1),
    THREE_ONE_DAY(Month.MARCH, 1),
    CHILDREN_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUNE, 6),
    LIBERATION_DAY(Month.AUGUST, 15),
    FOUNDATION_DAY(Month.OCTOBER, 3),
    KOREAN_DAY(Month.OCTOBER, 9),
    CHRISTMAS_DAY(Month.DECEMBER, 25),
    ;

    private final Month month;
    private final int day;

    Holiday(final Month month, final int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(final Month month, final int day) {
        return Arrays.stream(Holiday.values())
                .anyMatch(type -> type.month == month && type.day == day);
    }

}

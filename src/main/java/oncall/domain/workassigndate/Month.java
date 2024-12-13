package oncall.domain.workassigndate;

import java.util.Arrays;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public enum Month {

    JANUARY("1", 31),
    FEBRUARY("2", 28),
    MARCH("3", 31),
    APRIL("4", 30),
    MAY("5", 31),
    JUNE("6", 30),
    JULY("7", 31),
    AUGUST("8", 31),
    SEPTEMBER("9", 30),
    OCTOBER("10", 31),
    NOVEMBER("11", 30),
    DECEMBER("12", 31),
    ;

    private final String month;
    private final int day;

    Month(final String month, final int day) {
        this.month = month;
        this.day = day;
    }

    public static Month of(final String month) {
        return Arrays.stream(values())
                .filter(type -> type.month.equals(month))
                .findFirst()
                .orElseThrow(() -> new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA));
    }

    public int getDay() {
        return day;
    }

}
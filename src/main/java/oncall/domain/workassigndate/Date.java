package oncall.domain.workassigndate;

import java.util.Arrays;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public enum Date {

    SUNDAY("일", 0),
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    ;

    private final String date;
    private final int sequence;

    Date(final String month, final int sequence) {
        this.date = month;
        this.sequence = sequence;
    }

    public static Date of(final String date) {
        return Arrays.stream(values())
                .filter(type -> type.date.equals(date))
                .findFirst()
                .orElseThrow(() -> new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA));
    }

    public int getSequence() {
        return sequence;
    }

    public String getDate() {
        return date;
    }

    public boolean isWeekend() {
        return this == SUNDAY || this == SATURDAY;
    }

}

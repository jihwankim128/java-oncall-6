package oncall.domain;

import java.util.Arrays;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public enum Date {

    SUNDAY("일", 1),
    MONDAY("월", 2),
    TUESDAY("화", 3),
    WEDNESDAY("수", 4),
    THURSDAY("목", 5),
    FRIDAY("금", 6),
    SATURDAY("토", 7),
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

}

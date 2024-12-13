package oncall.domain.workassigndate;

import java.util.ArrayList;
import java.util.List;

public class WorkAssignDate {

    private static final int DATE_PERIOD = 7;

    private final Date date;
    private final Month month;

    private WorkAssignDate(final Date date, final Month month) {
        this.date = date;
        this.month = month;
    }

    public static WorkAssignDate of(final String month, final String date) {
        return new WorkAssignDate(Date.of(date), Month.of(month));
    }

    public WorkAssignDateInformation extractWorkAssignDate() {
        final List<HolidayInformation> holidayInformation = new ArrayList<>();

        for (int day = 1, startDateSequence = date.getSequence(); day <= month.getDay(); day++) {
            final Date date = Date.values()[((day + startDateSequence - 1) % DATE_PERIOD)];
            final boolean publicHoliday = Holiday.isHoliday(month, day);
            final boolean holiday = date.isWeekend() || publicHoliday;
            holidayInformation.add(new HolidayInformation(day, date.getDate(), holiday, publicHoliday));
        }
        return new WorkAssignDateInformation(month.getMonth(), month.getDay(), holidayInformation);
    }

}

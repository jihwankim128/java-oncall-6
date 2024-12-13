package oncall.domain;

public class WorkAssignDate {

    private final Date date;
    private final Month month;

    public WorkAssignDate(final Date date, final Month month) {
        this.date = date;
        this.month = month;
    }

    public static WorkAssignDate of(final String month, final String date) {
        return new WorkAssignDate(Date.of(date), Month.of(month));
    }

}

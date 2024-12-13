package oncall.domain.workassigndate;

import java.util.List;

public record WorkAssignDateInformation(
        String month,
        int day,
        List<HolidayInformation> holidayInformation
) { }

package oncall.application;

import oncall.domain.workassigndate.HolidayInformation;

public record WorkerInformationResponse(
        int day,
        String date,
        boolean holiday,
        boolean publicHoliday,
        String workerName
) {

    public static WorkerInformationResponse of(final HolidayInformation holidayInformation, final String workerName) {
        return new WorkerInformationResponse(
                holidayInformation.day(),
                holidayInformation.date(),
                holidayInformation.holiday(),
                holidayInformation.publicHoliday(),
                workerName
        );
    }

}

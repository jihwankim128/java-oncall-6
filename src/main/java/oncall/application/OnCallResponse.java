package oncall.application;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.workassigndate.HolidayInformation;
import oncall.domain.workassigndate.WorkAssignDateInformation;

public record OnCallResponse(String month, List<WorkerInformationResponse> workerInformation) {

    public static OnCallResponse of(
            final WorkAssignDateInformation workAssignDateInformation,
            final List<String> workerNames
    ) {
        return new OnCallResponse(
                workAssignDateInformation.month(),
                ofWorkInformation(workAssignDateInformation, workerNames)
        );
    }

    private static List<WorkerInformationResponse> ofWorkInformation(
            final WorkAssignDateInformation workAssignDateInformation,
            final List<String> workerNames
    ) {
        final List<WorkerInformationResponse> workerInformationResponses = new ArrayList<>();
        final List<HolidayInformation> holidayInformation = workAssignDateInformation.holidayInformation();
        for (int i = 0; i < workAssignDateInformation.day(); i++) {
            workerInformationResponses.add(WorkerInformationResponse.of(holidayInformation.get(i), workerNames.get(i)));
        }
        return workerInformationResponses;
    }

}

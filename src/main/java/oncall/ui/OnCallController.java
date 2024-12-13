package oncall.ui;

import java.util.List;
import oncall.application.OnCallResponse;
import oncall.domain.OnCall;
import oncall.domain.workassigndate.WorkAssignDate;
import oncall.domain.workassigndate.WorkAssignDateInformation;
import oncall.domain.worker.Workers;
import oncall.ui.view.View;

public class OnCallController {

    private final View view = new View();
    private final ExceptionHandler exceptionHandler = new ExceptionHandler(view);

    public void run() {
        final WorkAssignDate workAssignDate = exceptionHandler.retry(this::generateWorkAssignDate);
        final OnCall workerOnCall = exceptionHandler.retry(this::generateWeekendsWorkers);
        final WorkAssignDateInformation workAssignDateInformation = workAssignDate.extractWorkAssignDate();
        final List<String> workerNames = workerOnCall.organizeSchedule(workAssignDateInformation.holidayInformation());
        view.displayOnCallResult(OnCallResponse.of(workAssignDateInformation, workerNames));
    }

    private OnCall generateWeekendsWorkers() {
        final Workers weekendsWorkers = Workers.from(view.enterWorkOnWeekdays());
        final Workers holidayWorkers = Workers.from(view.enterWorkOnHolidays());
        return new OnCall(weekendsWorkers, holidayWorkers);
    }

    private WorkAssignDate generateWorkAssignDate() {
        final List<String> workAssignmentDate = view.enterWorkAssignmentDate();
        return WorkAssignDate.of(workAssignmentDate.get(0), workAssignmentDate.get(1));
    }

}

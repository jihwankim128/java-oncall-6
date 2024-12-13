package oncall.ui;

import java.util.List;
import oncall.domain.OnCall;
import oncall.domain.workassigndate.WorkAssignDate;
import oncall.domain.worker.Workers;
import oncall.ui.view.View;

public class OnCallController {

    private final View view = new View();
    private final ExceptionHandler exceptionHandler = new ExceptionHandler(view);

    public void run() {
        final WorkAssignDate workAssignDate = exceptionHandler.retry(this::generateWorkAssignDate);
        final OnCall workerOnCall = exceptionHandler.retry(this::generateWeekendsWorkers);
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

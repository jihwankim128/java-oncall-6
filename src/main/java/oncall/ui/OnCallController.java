package oncall.ui;

import java.util.List;
import oncall.domain.WorkAssignDate;
import oncall.ui.view.View;

public class OnCallController {

    private final View view = new View();
    private final ExceptionHandler exceptionHandler = new ExceptionHandler(view);

    public void run() {
        final WorkAssignDate workAssignDate = exceptionHandler.retry(this::generateWorkAssignDate);

    }

    private WorkAssignDate generateWorkAssignDate() {
        final List<String> workAssignmentDate = view.enterWorkAssignmentDate();
        return WorkAssignDate.of(workAssignmentDate.get(0), workAssignmentDate.get(1));
    }

}

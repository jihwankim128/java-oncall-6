package oncall.ui.view;

import java.util.List;
import oncall.ui.view.impl.ConsolePrompt;

public class View extends OutputView {

    private final Prompt prompt = new ConsolePrompt();

    public List<String> enterWorkAssignmentDate() {
        displayWorkAssignmentDate();
        return prompt.parseInputData();
    }

    public List<String> enterWorkOnWeekdays() {
        displayWorkOnWeekdays();
        return prompt.parseInputData();
    }

    public List<String> enterWorkOnHolidays() {
        displayWorkOnHolidays();
        return prompt.parseInputData();
    }

}

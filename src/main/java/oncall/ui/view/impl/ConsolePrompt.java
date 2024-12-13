package oncall.ui.view.impl;

import camp.nextstep.edu.missionutils.Console;
import oncall.ui.view.Prompt;

public class ConsolePrompt implements Prompt {

    @Override
    public String readInputData() {
        final String inputData = Console.readLine();
        validateNotNullOrNotEmpty(inputData);

        return inputData;
    }

}


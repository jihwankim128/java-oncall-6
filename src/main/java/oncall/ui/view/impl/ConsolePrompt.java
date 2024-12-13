package oncall.ui.view.impl;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import oncall.ui.view.Prompt;

public class ConsolePrompt implements Prompt {

    private static final String PARSING_DELIMITER = ",";

    @Override
    public String readInputData() {
        final String inputData = Console.readLine();
        validateNotNullOrNotEmpty(inputData);

        return inputData;
    }

    @Override
    public List<String> parseInputData() {
        return Arrays.stream(readInputData()
                .split(PARSING_DELIMITER))
                .map(String::trim).toList();
    }

}


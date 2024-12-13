package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.ui.OnCallController;

public class Application {

    public static void main(String[] args) {
        new OnCallController().run();
        Console.close();
    }

}

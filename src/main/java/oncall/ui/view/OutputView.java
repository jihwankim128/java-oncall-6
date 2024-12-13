package oncall.ui.view;

public class OutputView {

    private final StringBuilder stringBuilder = new StringBuilder();

    protected OutputView() {}

    private void clear() {
        System.out.print(stringBuilder);
        stringBuilder.setLength(0);
    }

    public void displayErrorMessage(final String message) {
        System.out.println(message);
    }

    protected void displayWorkAssignmentDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

}
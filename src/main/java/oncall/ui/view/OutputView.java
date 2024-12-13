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
        System.out.println();
    }

}
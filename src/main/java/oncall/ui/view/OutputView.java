package oncall.ui.view;

import oncall.application.OnCallResponse;
import oncall.application.WorkerInformationResponse;

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

    protected void displayWorkOnWeekdays() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    protected void displayWorkOnHolidays() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void displayOnCallResult(final OnCallResponse response) {
        for (final WorkerInformationResponse workerInformationResponse : response.workerInformation()) {
            stringBuilder.append(generateOnCallResultByDayMessage(response, workerInformationResponse));
        }
        clear();
    }

    private static String generateOnCallResultByDayMessage(
            final OnCallResponse response,
            final WorkerInformationResponse workerInformationResponse
    ) {
        if (workerInformationResponse.publicHoliday()) {
            return generateOnCallResultOnPublicHolidayMessage(response, workerInformationResponse);
        }
        return generateOnCallResultOnWeekendsAndWeekDay(response, workerInformationResponse);
    }

    private static String generateOnCallResultOnWeekendsAndWeekDay(
            final OnCallResponse response,
            final WorkerInformationResponse workerInformationResponse
    ) {
        return String.format("%s월 %s일 %s %s %n",
                response.month(),
                workerInformationResponse.day(),
                workerInformationResponse.date(),
                workerInformationResponse.workerName()
        );
    }

    private static String generateOnCallResultOnPublicHolidayMessage(
            final OnCallResponse response,
            final WorkerInformationResponse workerInformationResponse
    ) {
        return String.format("%s월 %s일 %s(휴일) %s %n",
                response.month(),
                workerInformationResponse.day(),
                workerInformationResponse.date(),
                workerInformationResponse.workerName()
        );
    }
}
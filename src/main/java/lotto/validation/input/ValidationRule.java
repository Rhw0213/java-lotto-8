package lotto.validation.input;

public enum ValidationRule {
    ONLY_NUMBER("^[1-9][0-9]+$",
            "숫자만 입력 가능합니다."),

    ONLY_NUMBER_AND_COMMA("^[1-9][0-9]*(,[0-9]+)*$",
            "숫자와 쉼표만 입력 가능합니다."),

    BLANK("\"^(?![\\\\p{Z}\\\\s]*$).+\"",
            "입력값이 비어있습니다.");

    private final String regex;
    private final String errorMessage;

    ValidationRule(final String regex, final String errorMessage) {
        this.regex = regex;
        this.errorMessage = errorMessage;
    }

    public boolean validate(final String input) {
        if (input == null) {
            throw new NullPointerException();
        }

        return !input.matches(regex);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

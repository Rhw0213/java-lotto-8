package lotto.validation.input;

public class NumberAndCommaValidator implements InputValidator {
    @Override
    public void validate(final String input) {
        if (ValidationRule.ONLY_NUMBER_AND_COMMA.validate(input)) {
            throw new IllegalArgumentException(
                    ValidationRule.ONLY_NUMBER_AND_COMMA.getErrorMessage()
            );
        }
    }
}

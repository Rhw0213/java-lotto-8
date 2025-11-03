package lotto.validation.input;


public class InputNumbersValidator implements InputValidator {
    @Override
    public void validate(final String input) {
        if (ValidationRule.ONLY_NUMBER.validate(input)) {
            throw new IllegalArgumentException(
                    ValidationRule.ONLY_NUMBER.getErrorMessage()
            );
        }
    }
}

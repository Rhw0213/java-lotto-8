package lotto.validation.input;

public class EmptyInputValidator implements InputValidator {
    @Override
    public void validate(final String input) {
        if (ValidationRule.BLANK.validate(input)) {
            throw new IllegalArgumentException(ValidationRule.BLANK.getErrorMessage());
        }
    }
}

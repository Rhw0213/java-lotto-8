package lotto.infra.io;

import lotto.validation.input.InputValidator;

public interface Input {
    String input();

    void validateFormat(final InputValidator... validators);
}

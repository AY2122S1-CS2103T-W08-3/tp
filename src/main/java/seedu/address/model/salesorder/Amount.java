package seedu.address.model.salesorder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an amount associated with a SalesOrder.
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS =
            "Sales order amount should only contain numbers, and optionally, at most one block starting with a dot"
            + "followed by between one or or two numbers";
    public static final String VALIDATION_REGEX = "^\\d+(\\.?\\d{1,2})?";
    public final String amount;

    /**
     * Constructs an {@code Amount}
     *
     * @param amount A valid SalesOrder amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return amount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Amount
                && amount.equals(((Amount) other).amount));
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}

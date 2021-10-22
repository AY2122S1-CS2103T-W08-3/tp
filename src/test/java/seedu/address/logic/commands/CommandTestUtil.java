package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEASUREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_GENDER_AMY = "FEMALE";
    public static final String VALID_GENDER_BOB = "MALE";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_MEASUREMENT_AMY = "160_50_60_70";
    public static final String VALID_MEASUREMENT_BOB = "170_80_90";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime, Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_LABEL_ORDER = "Order Cloth";
    public static final String VALID_LABEL_SEW = "Sew buttons onto blazer";
    public static final String VALID_DATE_SEPT = "19th September 2021";
    public static final String VALID_DATE_OCT = "12th October 2021";
    public static final String VALID_TASKTAG_ORDER = "SO1000";
    public static final String VALID_TASKTAG_SEW = "SO1";

    public static final String VALID_CUSTOMER_SALE1 = "Muthu";
    public static final String VALID_CUSTOMER_SALE2 = "Danish";
    public static final String VALID_AMOUNT_SALE1 = "50";
    public static final String VALID_AMOUNT_SALE2 = "30.90";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MEASUREMENT_DESC_AMY = " " + PREFIX_MEASUREMENT + VALID_MEASUREMENT_AMY;
    public static final String MEASUREMENT_DESC_BOB = " " + PREFIX_MEASUREMENT + VALID_MEASUREMENT_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String LABEL_DESC_ORDER = " " + PREFIX_LABEL + VALID_LABEL_ORDER;
    public static final String LABEL_DESC_SEW = " " + PREFIX_LABEL + VALID_LABEL_SEW;
    public static final String DATE_DESC_SEPT = " " + PREFIX_DATE + VALID_DATE_SEPT;
    public static final String DATE_DESC_OCT = " " + PREFIX_DATE + VALID_DATE_OCT;
    public static final String TASKTAG_DESC_ORDER = " " + PREFIX_TASK_TAG + VALID_TASKTAG_ORDER;
    public static final String TASKTAG_DESC_SEW = " " + PREFIX_TASK_TAG + VALID_TASKTAG_SEW;

    public static final String CUSTOMER_DESC_SALE1 = " " + PREFIX_CUSTOMER + VALID_CUSTOMER_SALE1;
    public static final String CUSTOMER_DESC_SALE2 = " " + PREFIX_CUSTOMER + VALID_CUSTOMER_SALE2;
    public static final String AMOUNT_DESC_SALE1 = " " + PREFIX_AMOUNT + VALID_AMOUNT_SALE1;
    public static final String AMOUNT_DESC_SALE2 = " " + PREFIX_AMOUNT + VALID_AMOUNT_SALE2;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "mimi"; // mimi is not a gender
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MEASUREMENT_DESC = " " + PREFIX_MEASUREMENT + "170_91"; // missing 1 measurement
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + "^happy"; // '^' not allowed in remarks
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_LABEL_DESC = " " + PREFIX_LABEL; // empty string not allowed for labels
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE; // empty string not allowed for date
    public static final String INVALID_TASKTAG_DESC = " " + PREFIX_TASK_TAG + "SOSO"; // wrong prefix and missing id

    public static final String INVALID_CUSTOMER_DESC = " " + PREFIX_CUSTOMER; // empty string not allowed for customers
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "20."; // no digit after dot

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final EditTaskCommand.EditTaskDescriptor DESC_ORDER;
    public static final EditTaskCommand.EditTaskDescriptor DESC_SEW;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withMeasurement(VALID_MEASUREMENT_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRemark(VALID_REMARK_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withMeasurement(VALID_MEASUREMENT_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRemark(VALID_REMARK_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_ORDER = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_ORDER).withDate(VALID_DATE_SEPT)
                .withTaskTag(VALID_TASKTAG_ORDER).build();
        DESC_SEW = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_SEW).withDate(VALID_DATE_OCT)
                .withTaskTag(VALID_TASKTAG_SEW).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
}

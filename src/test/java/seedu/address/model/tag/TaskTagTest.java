package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskTag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> TaskTag.isValidTagName(null));
        // wrong prefix
        assertFalse(TaskTag.isValidTagName("SG10"));
        // id is absent
        assertFalse(TaskTag.isValidTagName("SO"));
        // invalid tag
        assertFalse(TaskTag.isValidTagName("Generous"));
        // correct tag names
        assertTrue(TaskTag.isValidTagName("SO1"));
        assertTrue(TaskTag.isValidTagName("SO2021"));
        assertTrue(TaskTag.isValidTagName("General"));
    }

}

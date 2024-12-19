package backend;

import backend.enums.ConferenceDeadLineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    void setUp() {
        deadline = new Deadline();
    }

    @Test
    void setDeadline() {
        // You need to provide actual values for testing
        ConferenceDeadLineType cdlType = ConferenceDeadLineType.REPORT_SUBMISSION;
        String day = "01";
        String month = "01";
        String year = "2023";
        Integer cdlId = 1;

        deadline.SetDeadLine(cdlType, day, month, year, cdlId);

        // Add assertions based on the expected behavior of SetDeadLine method
        // For example, check if the deadline is properly set in the database
    }

    @Test
    void getDeadline() {
        // You need to provide actual values for testing
        ConferenceDeadLineType cdlType = ConferenceDeadLineType.REPORT_SUBMISSION;
        Integer cdlId = 1;

        String result = deadline.GetDeadLine(cdlType, cdlId);

        // Add assertions based on the expected behavior of GetDeadLine method
        assertEquals("TODO : DATABASE HOOKUP", result); // Replace with expected result
    }

    @Test
    void isDeadlinePassed() {
        // You need to provide an actual deadline value for testing
        String inDeadline = "2023-01-01";

        boolean result = deadline.IsDeadlinePassed(inDeadline);

        // Add assertions based on the expected behavior of IsDeadlinePassed method
        assertFalse(result); // Replace with expected result
    }

    @Test
    void deadlineConvertor() {
        // You need to provide actual values for testing
        String day = "01";
        String month = "01";
        String year = "2023";

        String result = deadline.DeadlineConvertor(day, month, year);

        // Add assertions based on the expected behavior of DeadlineConvertor method
        assertEquals("2023-01-01", result); // Replace with expected result
    }
}

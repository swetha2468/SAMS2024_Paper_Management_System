package backend.webinterface.systems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportSystemTest {

    @Test
    void getAvailablePCMs() {
        ReportSystem reportSystem = new ReportSystem();
        Integer[] result = reportSystem.GetAvailablePCMs();

        // Assertions
        Integer[] expected = {1, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    void getPapersToRate() {
        ReportSystem reportSystem = new ReportSystem();
        Integer[] result = reportSystem.GetPapersToRate();

        // Assertions
        Integer[] expected = {1, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    void assignPaper() {
        ReportSystem reportSystem = new ReportSystem();

        // TODO: Replace with actual logic to connect to the database
        Integer[] pcms = {1, 2, 3};
        reportSystem.AssignPaper(pcms);

        // TODO: Add assertions for the AssignPaper method if needed
    }

    @Test
    void informConflicts() {
        ReportSystem reportSystem = new ReportSystem();

        // TODO: Replace with actual logic to connect to the database
        Integer pcmId = 1;
        Integer reviewId = 123;
        reportSystem.InformConflicts(pcmId, reviewId);

        // TODO: Add assertions for the InformConflicts method if needed
    }

    @Test
    void ratePaper() {
        ReportSystem reportSystem = new ReportSystem();

        // TODO: Add logic to prepare for rating paper (e.g., set up necessary data)

        // Call the RatePaper method
        reportSystem.RatePaper();

        // TODO: Add assertions for the RatePaper method if needed
    }
}

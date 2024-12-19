package backend;

import backend.enums.PaperFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaperTest {

    private Paper paper;

    @BeforeEach
    void setUp() {
        // You need to provide a mock or use an actual database for testing
        Integer mockPaperId = 1;
        paper = new Paper(mockPaperId);
    }

    @Test
    void getTitle() {
        String result = paper.getTitle();

        // Add assertions based on the expected behavior of getTitle method
        assertEquals("DATABASE HOOKUP", result); // Replace with expected result
    }
}

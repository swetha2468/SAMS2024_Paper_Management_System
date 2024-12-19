package backend.webinterface.systems;

import backend.enums.Role;
import backend.database.SAMS2024DbInterface;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountSystemTest {

    private UserAccountSystem userAccountSystem;
    SAMS2024DbInterface mockDatabase;

    @BeforeEach
    void setUp() {
        // You need to provide a mock or use an actual database for testing
        userAccountSystem = new UserAccountSystem(mockDatabase, false);
    }

    @Test
    @Disabled
    void setRole() throws SQLException {
        String username = "testUser";
        Role role = Role.administrator;

        Integer result = userAccountSystem.SetRole(username, role);

        // Assertions
        assertEquals(1, result); // Assuming 1 row was updated
    }

    @Test
    @Disabled
    void getRole() throws SQLException {
        String username = "testUser";

        Role result = userAccountSystem.GetRole(username);

        // Assertions
        assertEquals(Role.unknown, result); // Replace with expected role
    }

    @Test
    @Disabled
    void getRoles() throws SQLException {
        String username = "testUser";

        ArrayList<Role> result = userAccountSystem.GetRoles(username);

        // Assertions
        assertEquals(4, result.size()); // Assuming 4 roles are returned
        // Add more specific assertions based on your actual logic
    }

    @Test
    @Disabled
    void login() throws SQLException {
        String userName = "testUser";
        String password = "testPassword";

        boolean result = userAccountSystem.Login(userName, password);

        // Assertions
        assertTrue(result); // Replace with expected result
    }

    @Test
    @Disabled
    void setPassword() throws SQLException {
        String username = "testUser";
        String password = "newPassword";

        String result = userAccountSystem.SetPassword(username, password);

        // Assertions
        assertEquals("Update Successful", result); // Replace with expected result
    }

    @Test
    @Disabled
    void setUserName() throws SQLException {
        String usernameFrom = "testUser";
        String usernameTo = "newTestUser";

        String result = userAccountSystem.SetUserName(usernameFrom, usernameTo);

        // Assertions
        assertTrue(result.contains("Update Successful")); // Replace with expected result
    }

    @Test
    @Disabled
    void setFirstName() throws SQLException {
        String username = "testUser";
        String firstName = "John";

        String result = userAccountSystem.SetFirstName(username, firstName);

        // Assertions
        assertEquals("Update Successful", result); // Replace with expected result
    }
}

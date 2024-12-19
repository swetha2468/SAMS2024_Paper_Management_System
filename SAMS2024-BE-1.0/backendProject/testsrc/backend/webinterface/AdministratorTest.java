package backend.webinterface;

import app.gui.users;
import backend.enums.Role;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdministratorTest {

    private Administrator administrator;

    @BeforeEach
    void setUp() {
        // You need to provide a mock or use an actual User instance for testing
        User mockUser = new User(null, null, false);
        administrator = new Administrator(mockUser);
    }

    @Test
    @Disabled
    void setFirstName() throws SQLException {
        String username = "testUser";
        String firstName = "John";

        String result = administrator.SetFirstName(username, firstName);

        // Assertions
        assertEquals("Update Successful", result); // Replace with expected result
    }

    @Test
    @Disabled
    void setLastName() throws SQLException {
        String username = "testUser";
        String lastName = "Doe";

        String result = administrator.SetLastName(username, lastName);

        // Assertions
        assertEquals("Update Successful", result); // Replace with expected result
    }

    @Test
    @Disabled
    void setUserName() throws SQLException {
        String usernameFrom = "testUser";
        String usernameTo = "newTestUser";

        String result = administrator.SetUserName(usernameFrom, usernameTo);

        // Assertions
        assertTrue(result.contains("Update Successful")); // Replace with expected result
    }

    @Test
    @Disabled
    void setPassword() throws SQLException {
        String username = "testUser";
        String password = "newPassword";

        String result = administrator.SetPassword(username, password);

        // Assertions
        assertEquals("Update Successful", result); // Replace with expected result
    }

    @Test
    @Disabled
    void createAccount() throws SQLException {
        String username = "newUser";
        String password = "newPassword";
        String firstName = "John";
        String lastName = "Doe";
        Role role = Role.unknown;
        String email = "john.doe@example.com";
        String phone = "123456789";
        String address = "123 Main St";

        String result = administrator.CreateAccount(username, password, firstName, lastName, role, email, phone, address);

        // Assertions
        assertTrue(result.contains("Update Successful")); // Replace with expected result
    }

    @Test
    @Disabled
    void modifyAccount() throws SQLException {
        String username = "testUser";
        String password = "newPassword";
        String firstName = "John";
        String lastName = "Doe";
        Role role = Role.administrator;

        String result = administrator.ModifyAccount(username, password, firstName, lastName, role);

        // Assertions
        assertTrue(result.contains("TODO: HOOKUP BACKEND")); // Replace with expected result
    }

    @Test
    @Disabled
    void modifyCredentials() throws SQLException {
        String username = "testUser";
        String password = "newPassword";
        String firstName = "John";
        String lastName = "Doe";
        Role role = Role.administrator;

        String result = administrator.ModifyCredentials(username, password, firstName, lastName, role);

        // Assertions
        assertTrue(result.contains("TODO: HOOKUP BACKEND")); // Replace with expected result
    }

    @Test
    @Disabled
    void modifyContactInfo() throws SQLException {
        String username = "testUser";
        String password = "newPassword";
        String firstName = "John";
        String lastName = "Doe";
        Role role = Role.administrator;

        String result = administrator.ModifyContactInfo(username, password, firstName, lastName, role);

        // Assertions
        assertTrue(result.contains("TODO: HOOKUP BACKEND")); // Replace with expected result
    }

    @Test
    @Disabled
    void deleteAccount() throws SQLException {
        String username = "testUser";

        String result = administrator.DeleteAccount(username);

        // Assertions
        assertTrue(result.contains("TODO: HOOKUP BACKEND")); // Replace with expected result
    }
}

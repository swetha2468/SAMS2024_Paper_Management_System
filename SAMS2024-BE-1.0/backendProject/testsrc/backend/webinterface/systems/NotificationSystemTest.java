package backend.webinterface.systems;

import backend.enums.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationSystemTest {

    @Test
    void getTypeOfMessage() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(MessageType.NOTIFICATION.toString(), notificationSystem.GetTypeOfMessage());
    }

    @Test
    void setTypeOfMessage() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(MessageType.NOTIFICATION.toString(), notificationSystem.SetTypeOfMessage());
    }

    @Test
    void notifyTest() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(notificationSystem.toString(), notificationSystem.Notify());
    }

    @Test
    void getNotification() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(notificationSystem.toString(), notificationSystem.GetNotification());
    }

    @Test
    void setNotification() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(notificationSystem.toString(), notificationSystem.SetNotification());
    }

    @Test
    void setMessage() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(notificationSystem.toString(), notificationSystem.SetMessage());
    }

    @Test
    void getMessage() {
        NotificationSystem notificationSystem = new NotificationSystem();
        assertEquals(notificationSystem.toString(), notificationSystem.GetMessage());
    }
}

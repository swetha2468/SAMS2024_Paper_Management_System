package backend.webinterface.systems;

import backend.enums.TemplateType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateSystemTest {

    @Test
    void getTypeOfTemplate() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.GetTypeOfTemplate();

        // Assertions
        assertEquals(TemplateType.REPORT.toString(), result);
    }

    @Test
    void setTypeOfMessage() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.SetTypeOfMessage();

        // Assertions
        assertEquals(TemplateType.REPORT.toString(), result);
    }

    @Test
    void editTemplate() {
        TemplateSystem templateSystem = new TemplateSystem();

        // TODO: Replace with actual logic to edit a template
        templateSystem.EditTemplate();

        // TODO: Add assertions for the EditTemplate method if needed
    }

    @Test
    void getNotification() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.GetNotification();

        // Assertions
        assertEquals(templateSystem.toString(), result);
    }

    @Test
    void setNotification() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.SetNotification();

        // Assertions
        assertEquals(templateSystem.toString(), result);
    }

    @Test
    void setMessage() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.SetMessage();

        // Assertions
        assertEquals(templateSystem.toString(), result);
    }

    @Test
    void getMessage() {
        TemplateSystem templateSystem = new TemplateSystem();
        String result = templateSystem.GetMessage();

        // Assertions
        assertEquals(templateSystem.toString(), result);
    }
}

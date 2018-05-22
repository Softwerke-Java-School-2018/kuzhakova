package catalog.controller;

import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ClientControllerTest {
    private static ClientController clientController = new ClientController();

    @BeforeClass
    public static void setUp() throws Exception {
        clientController.addClient("Ivan", "Ivanov", LocalDate.of(1992, 12, 12));
    }

    @AfterClass
    public static void setDown() throws Exception {
        clientController.clearList();
    }

    @Test
    public void findSimilarClients() {
    }

    @Test
    public void findClient() {
    }

    @Test
    public void addClient() {
        assertTrue(clientController.addClient("Test", "Testov",
                LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.addClient("", "Testov",
                LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.addClient("Test", "",
                LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.addClient("Test", "Testov", null));
        assertFalse(clientController.addClient(null, null, null));
        assertFalse(clientController.addClient("", "", null));
    }

    @Test
    public void deleteClient() {
        assertTrue(clientController.deleteClient("Ivan", "Ivanov",
              LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.deleteClient("Ivan", "",
                LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.deleteClient("", "",
                LocalDate.of(1992, 12, 12)));
        assertFalse(clientController.deleteClient("Ivan", "Ivanov",
                null));
    }

    @Test
    public void sort() {
    }
}
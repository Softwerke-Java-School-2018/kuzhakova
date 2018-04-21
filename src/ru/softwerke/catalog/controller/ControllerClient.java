package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelClient;
import ru.softwerke.catalog.entities.Client;
import ru.softwerke.catalog.view.InputOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerClient {
    ModelClient modelClient = new ModelClient();

    public ControllerClient() {
        addClient("Petr", "Petrov", LocalDate.of(1992, 12, 2));
        addClient("Steven", "Spielberg", LocalDate.of(1991, 1, 7));
        addClient("Stephen", "Hawking", LocalDate.of(1967, 2, 3));
        addClient("Nikola", "Tesla", LocalDate.of(1982, 6, 6));
        addClient("Mariah", "Carey", LocalDate.of(1955, 5, 15));
        addClient("James", "Cameron", LocalDate.of(1972, 4, 14));
        addClient("Niels", "Bohr", LocalDate.of(1962, 3, 13));
        addClient("Christopher", "Nolan", LocalDate.of(1971, 2, 12));
    }

    public String[] clientListToStringArray() {
        return modelClient.getStreamClientList().map(c -> c.toString()).toArray(String[]::new);
    }

    public boolean printFoundClientList(String fName, String lName, LocalDate birthDate) {
        try {
            Stream<Client> foundClients = modelClient.selectClients(fName, lName, birthDate);
            List<Client> clients = foundClients.collect(Collectors.toList());
            if (!clients.isEmpty()) { //TODO
                InputOutput.printLine("The following clients are found for this query:");
                clients.forEach(System.out::println);
                InputOutput.printLine("Enter ID to delete client. Enter 0 to cancel:");
                int ind = InputOutput.readInt();
                if (ind == 0) return false;
                modelClient.deleteClient(ind);
                return true;
            }
            InputOutput.printLine("Client not found.");
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    public Client findClient(String firstName, String lastName, LocalDate birthDate) {
        Client c = modelClient.equalsClient(Client.newClientBuilder()
                .setId()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .build());
        if (c != null) InputOutput.printLine(c.toString());
        return c;
    }

    public boolean findSimilarClients(String fName, String lName, LocalDate birthDate) {
        try {
            Stream<Client> foundClients = modelClient.selectClients(fName, lName, birthDate);
            List<Client> clients = foundClients.collect(Collectors.toList());
            if (!clients.isEmpty()) {
                InputOutput.printLine("The following clients are found for this query:");
                foundClients.forEach(System.out::println);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addClient(String firstName, String lastName, LocalDate birthDate) {
        if (!("".equals(firstName) || "".equals(lastName) || Objects.isNull(birthDate))) {
            modelClient.addClient(Client.newClientBuilder()
                    .setId()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setBirthDate(birthDate)
                    .build());
            return true;
        }
        return false;
    }

    public boolean deleteClient(String firstName, String lastName, LocalDate birthDate) {
        return modelClient.deleteClient(Client.newClientBuilder()
                .setId(0)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .build());
    }

    public void sort(int what, int how) {
        List<Client> sortClientList = modelClient.getStreamClientList().collect(Collectors.toList());
        if (how == 2) Collections.sort(sortClientList, Collections.reverseOrder(modelClient.getComparator(what-1)));
        else Collections.sort(sortClientList, modelClient.getComparator(what - 1));
        for (Client c : sortClientList) {
            InputOutput.printLine(c.toString());
        }
    }
}



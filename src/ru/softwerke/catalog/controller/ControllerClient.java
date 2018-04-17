package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelClient;
import ru.softwerke.catalog.entities.Client;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControllerClient {
    ModelClient modelClient = new ModelClient();
    Scanner scanner = new Scanner(System.in);

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

    public Boolean printFoundClientList(String fName, String lName, LocalDate birthDate) {
        try {
            if (fName.equals("")) fName = null;
            if (lName.equals("")) lName = null;
            Stream foundClients = modelClient.selectClients(fName, lName, birthDate);
            if (foundClients != null) {
                System.out.println("The following clients are found for this query:");
                foundClients.forEach(System.out::println);
                System.out.println("Enter ID to delete client. Enter 0 to cancel:");
                int ind = scanner.nextInt();
                if (ind == 0) return false;
                modelClient.deleteClient(ind);
                return true;
            }
            System.out.println("Client not found.");
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    public Client findClient(String fName, String lName, LocalDate birthDate) {
        Client c = modelClient.equalsClient(new Client(fName, lName, birthDate));
        if (c != null) System.out.println(c.toString());
        return c;
    }

    public Boolean findSimilarClients(String fName, String lName, LocalDate birthDate) {
        try {
            if (fName.equals("")) fName = null;
            if (lName.equals("")) lName = null;
            Stream<Client> foundClients = modelClient.selectClients(fName, lName, birthDate);
            if (foundClients != null) {
                System.out.println("The following clients are found for this query:");
                foundClients.forEach(System.out::println);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean addClient(String firstName, String lastName, LocalDate birthDate) {
        if (!(firstName.equals("") || lastName.equals("") || birthDate == null)) {
            modelClient.addClient(new Client(firstName, lastName, birthDate));
            return true;
        }
        return false;
    }

    public Boolean deleteClient(String firstName, String lastName, LocalDate birthDate) {
        return modelClient.deleteClient(new Client(0, firstName, lastName, birthDate));
    }

    public void sort(int what, int how) {
        List<Client> sortClientList = modelClient.getStreamClientList().collect(Collectors.toList());
        if (how == 2) Collections.sort(sortClientList, Collections.reverseOrder(modelClient.getComparator(what - 1)));
        else Collections.sort(sortClientList, modelClient.getComparator(what - 1));
        for (Client c : sortClientList) {
            System.out.println(c.toString());
        }
    }
}



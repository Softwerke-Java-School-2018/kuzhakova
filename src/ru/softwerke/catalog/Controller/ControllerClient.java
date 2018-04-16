package ru.softwerke.catalog.controller;

import ru.softwerke.catalog.model.ModelClient;
import ru.softwerke.catalog.entities.Client;

import java.time.LocalDate;
import java.util.*;

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

    public void printClientList() {
        for (Client c : modelClient.getClientList()) {
            System.out.println(c.toString());
        }
    }

    public Boolean printFoundClientList(String fName, String lName, LocalDate birthDate) {
        System.out.println("The following clients are found for this query:");
        List<Client> foundClients = findClients(fName, lName, birthDate);
        for (int i = 0; i < foundClients.size(); i++) {
            System.out.println((i + 1) + ": " + foundClients.get(i));
        }
        System.out.println("Enter number to delete client. Enter 0 to cancel:");
        int ind = scanner.nextInt();
        if (ind == 0) return false;
        else {
            fName = foundClients.get(ind - 1).getFirstName();
            lName = foundClients.get(ind - 1).getLastName();
            birthDate = foundClients.get(ind - 1).getBirthDate();
            deleteClient(fName, lName, birthDate);
            return true;
        }
    }

    public Client findClient(String fName, String lName, LocalDate birthDate) {
        Client c = modelClient.exactMatch(new Client(fName, lName, birthDate));
        if (c != null) System.out.println(c.toString());
        return c;
    }

    public Boolean findSimilarClients(String fName, String lName, LocalDate birthDate) {
        //Client enteredClient = new Client(0, fName, lName, birthDate);
        if (fName.equals("")) fName = null;
        if (lName.equals("")) lName = null;
        List<Client> foundClients = modelClient.selectClients(fName, lName, birthDate);
        if (foundClients.size() != 0) {
            System.out.println("The following clients are found for this query:");
            for (int i = 0; i < foundClients.size(); i++) {
                System.out.println((i + 1) + ": " + foundClients.get(i));
            }
            return true;
        }
        return false;
    }

    public Boolean addClient(String firstName, String lastName, LocalDate birthDate) {
        //List<Client> clientList = model.getClientList();
        if (!(firstName.equals("") || lastName.equals(""))) {
            modelClient.getClientList().add(new Client(0, firstName, lastName, birthDate));
            return true;
        }
        return false;
    }

    public Boolean deleteClient(String firstName, String lastName, LocalDate birthDate) {
        try {
            return modelClient.getClientList().remove(new Client(0, firstName, lastName, birthDate));
        } catch (Exception e) {
            // NO
            System.out.println("Could not remove client.");
            return false;
        }
    }

    public List<Client> findClients(String firstName, String lastName, LocalDate birthDate) {
        if (firstName.equals("")) firstName = null;
        if (lastName.equals("")) lastName = null;
        return modelClient.selectClients(firstName, lastName, birthDate);
    }

    public void sort(int what, int how) {
        List<Client> sortClientList = new ArrayList<Client>(modelClient.getClientList());
        if (how == 2) Collections.sort(sortClientList, Collections.reverseOrder(modelClient.getComparator(what - 1)));
        else Collections.sort(sortClientList, modelClient.getComparator(what - 1));
        for (Client c : sortClientList) {
            System.out.println(c.toString());
        }
    }
    /*public void printSortedClientList(List<Client> sortedClientList) {
        for (Client c: sortedClientList) {
            System.out.println(c.toString());
        }
    }*/
}



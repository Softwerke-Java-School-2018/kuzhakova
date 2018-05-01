package ru.softwerke.catalog.model;

import ru.softwerke.catalog.model.storing.Database;
import ru.softwerke.catalog.model.entities.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ClientModel extends Database {
    private List<Comparator<Client>> arrayComparators = new ArrayList<>();

    public ClientModel() {
        arrayComparators.add(new FirstNameComparator());
        arrayComparators.add(new LastNameComparator());
        arrayComparators.add(new BirthDateComparator());
    }

    public int getArrayComparatorsSize() {
        return arrayComparators.size();
    }

    public Comparator getComparator(int n) {
        return arrayComparators.get(n);
    }

    public class FirstNameComparator implements Comparator<Client> {
        @Override
        public int compare(Client o1, Client o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }

    public class LastNameComparator implements Comparator<Client> {
        @Override
        public int compare(Client o1, Client o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }

    public class BirthDateComparator implements Comparator<Client> {
        @Override
        public int compare(Client o1, Client o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    }

    public Stream<Client> getStreamClientList() {
        return clientList().stream();
    }

    public Stream<Client> selectClients(String fName, String lName, LocalDate birthDate) {
        List<Client> selectedClientList = new ArrayList<Client>(clientList());
        selectedClientList.removeIf(c -> !(fName.contains(c.getFirstName()) ||
                lName.contains(c.getLastName()) ||
                birthDate.isEqual(c.getBirthDate())));
        if (selectedClientList.isEmpty()) return Stream.empty();
        return selectedClientList.stream();
    }

    public boolean addClient(Client client) {
        return clientList().add(client);
    }

    public boolean deleteClient(Client client) {
        return clientList().remove(client);
    }

    public boolean deleteClient(int id) {
        return clientList().removeIf(c -> c.getId() == id);
    }

    public Client equalsClient(Client enteredClient) {
        for (Client c : clientList()) {
            if (c.equals(enteredClient)) return c;
        }
        return null;
    }
}

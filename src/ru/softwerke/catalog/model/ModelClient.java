package ru.softwerke.catalog.model;

import ru.softwerke.catalog.entities.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ModelClient {
    private List<Client> clientList = new ArrayList<>();
    private List<Comparator<Client>> arrayComparators = new ArrayList<>();

    public ModelClient() {
        arrayComparators.add(new FirstNameComparator());
        arrayComparators.add(new LastNameComparator());
        arrayComparators.add(new BirthDateComparator());
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
        return clientList.stream();
    }

    public Stream<Client> selectClients(String fName, String lName, LocalDate birthDate) {
        List<Client> selectedClientList = new ArrayList<Client>();
        Boolean findFName = false;
        Boolean findLName = false;
        Boolean findBDate = false;
        for (Client client : clientList) {
            if (fName != null) findFName = client.getFirstName().contains(fName);
            if (lName != null) findLName = client.getLastName().contains(lName);
            if (birthDate != null) findBDate = client.getBirthDate().isEqual(birthDate);
            if (findBDate || findFName || findLName) selectedClientList.add(client);
        }
        if (selectedClientList.size() != 0) return selectedClientList.stream();
        return null;
    }

    public Boolean addClient(Client client) {
        return clientList.add(client);
    }

    public Boolean deleteClient(Client client) {
        return clientList.remove(client);
    }

    public Boolean deleteClient(int ind) {
        return clientList.removeIf(c -> c.getId() == ind);
    }

    public Client equalsClient(Client enteredClient) {
        for (Client c : clientList) {
            if (c.equals(enteredClient)) return c;
        }
        return null;
    }
}

package catalog.model.dao;

import catalog.model.comparators.client.BirthDateComparator;
import catalog.model.comparators.client.FirstNameComparator;
import catalog.model.comparators.client.LastNameComparator;
import catalog.model.entities.Client;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;


public class ClientModel extends MainModel {

    /**
     * Field HashMap of comparators. The key is the client's property in String, the value is the class of comparator
     */
    private static final Map<String, Comparator<Object>> comparatorsMap =
            Collections.unmodifiableMap(new HashMap<String, Comparator<Object>>() {{
                put("first name", new FirstNameComparator());
                put("last name", new LastNameComparator());
                put("birth date", new BirthDateComparator());
            }});

    public ClientModel() {
    }

    public int getArrayComparatorsSize() {
        return comparatorsMap.size();
    }

    public Comparator getComparator(String property) {
        return comparatorsMap.get(property);
    }

    public Stream<Client> getStreamList() {
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
        return clientList().removeIf(c -> c.getID() == id);
    }

    public Client equalsClient(Client enteredClient) {
        for (Client c : clientList()) {
            if (c.equals(enteredClient)) return c;
        }
        return null;
    }

    public void clear(){
        clientList().clear();
    }
}

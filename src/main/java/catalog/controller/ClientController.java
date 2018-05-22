package catalog.controller;

import catalog.model.dao.ClientModel;
import catalog.model.entities.Client;
import catalog.view.io.InputOutput;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientController {
    private ClientModel clientModel = new ClientModel();

    public ClientController() {
    }

    public void printList() {
        clientModel.getStreamList().forEach(InputOutput::printLine);
    }

    /**
     * The method displays similar clients, if there was no exact match when searching for the client
     * @param fName First name of client
     * @param lName Last name of client
     * @param birthDate Birth date of client
     * @return true, if clients found
     */
    public boolean findSimilarClients(String fName, String lName, LocalDate birthDate) {
        try {
            Stream<Client> foundClients = clientModel.selectClients(fName, lName, birthDate);
            //Optional<Client> clientOptional = foundClients.findAny();             терминальный метод
            List<Client> clients = foundClients.collect(Collectors.toList());
            if (!clients.isEmpty()) {
                InputOutput.printLine("The following clients are found for this query:");
                clients.forEach(InputOutput::printLine);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Client findClient(String firstName, String lastName, LocalDate birthDate) {
        Client client = Client.newClientBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .build();
        Client foundClient = clientModel.equalsClient(client);
        if (Objects.nonNull(foundClient)) InputOutput.printLine(foundClient.toString());
        return foundClient;
    }

    public boolean addClient(String firstName, String lastName, LocalDate birthDate) {
        if (!("".equals(firstName) || "".equals(lastName) || Objects.isNull(birthDate))) {
            clientModel.addClient(Client.newClientBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setBirthDate(birthDate)
                    .build());
            return true;
        }
        return false;
    }

    public boolean deleteClient(String firstName, String lastName, LocalDate birthDate) {
        return clientModel.deleteClient(Client.newClientBuilder()
                .setId(0)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .build());
    }

    public boolean printFoundClientList(String fName, String lName, LocalDate birthDate) {
        try {
            if (findSimilarClients(fName, lName, birthDate)) {
                InputOutput.printLine("Enter ID to delete client. Enter 0 to cancel:");
                int ind = InputOutput.readInt();
                if (ind == 0) return false;
                clientModel.deleteClient(ind);
                return true;
            }
            InputOutput.printLine("Client not found.");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Client sorting method
     * @param property Customer field, by which it will be sorted
     * @param sortingParameter Sorting order
     */
    public void sort(String property, int sortingParameter) {
        List<Client> sortClientList = clientModel.getStreamList().collect(Collectors.toList());
        if (sortingParameter == 2) {
            Collections.sort(sortClientList, Collections.reverseOrder(clientModel.getComparator(property)));
        } else {
            Collections.sort(sortClientList, clientModel.getComparator(property));
        }
        for (Client c : sortClientList) {
            InputOutput.printLine(c.toString());
        }
    }

    public int comparatorsCount() {
        return clientModel.getArrayComparatorsSize();
    }

    public void clearList(){
        clientModel.clear();
    }
}
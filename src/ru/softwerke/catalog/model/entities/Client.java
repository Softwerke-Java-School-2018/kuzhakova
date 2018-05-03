package ru.softwerke.catalog.model.entities;

import ru.softwerke.catalog.view.io.InputOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Entity client
 *
 * @version 1.0
 * @autor Kuzhakova Zarina
 * @since 3-05-2018
 */
public class Client extends DataItem {
    public static volatile AtomicInteger COUNT = new AtomicInteger(0);
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private static final String[] propertiesArray = {"first name", "last name", "birth date"};

    private Client() {
    }

    public static String getPropertyInArray(int number) {
        return propertiesArray[number];
    }

    public static String toStringPropertiesList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < propertiesArray.length; i++) {
            stringBuilder.append((i + 1) + ". " + propertiesArray[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static ClientBuilder newClientBuilder() {
        return new Client().new ClientBuilder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Objects.isNull(o) || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(birthDate, client.birthDate);
    }

    @Override
    public String toString() {
        String stringBirthDate = "";
        if (Objects.nonNull(birthDate)) stringBirthDate = birthDate.toString();
        return "Client: " + id + ". " + firstName + " " + lastName + ", " + stringBirthDate;
    }

    public class ClientBuilder {

        private ClientBuilder() {
        }

        public ClientBuilder setId(int id) {
            Client.this.id = id;
            return this;
        }

        public ClientBuilder setFirstName(String firstName) {
            Client.this.firstName = firstName;
            return this;
        }

        public ClientBuilder setLastName(String lastName) {
            Client.this.lastName = lastName;
            return this;
        }

        public ClientBuilder setBirthDate(LocalDate birthDate) {
            Client.this.birthDate = birthDate;
            return this;
        }

        public Client build() {
            Client.this.id = COUNT.incrementAndGet();
            return Client.this;
        }

    }

}

package ru.softwerke.catalog.model.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/** Entity client
 *
 * @autor Kuzhakova Zarina
 * @version 1.0
 * @since 3-05-2018
 */
public class Client {
    public static volatile AtomicInteger COUNT = new AtomicInteger(0);
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private Client() {
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

    public int getId() {
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

        public ClientBuilder setId() {
            Client.this.id = COUNT.incrementAndGet();
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
            return Client.this;
        }

    }

}
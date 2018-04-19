package ru.softwerke.catalog.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private static int COUNT = 0;
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Client(String firstName, String lastName, LocalDate birthDate) {
        this.id = ++COUNT;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Client(int id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(birthDate, client.birthDate);
    }

    @Override
    public String toString() {
        String stringBirthDate = "";
        if (birthDate != null) stringBirthDate = birthDate.toString();
        return "Client:" + id + ". " + firstName + " " + lastName + ", " + stringBirthDate;
    }

}

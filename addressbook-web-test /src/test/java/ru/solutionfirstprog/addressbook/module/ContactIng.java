package ru.solutionfirstprog.addressbook.module;

public class ContactIng {
    private final String company;
    private final String street;
    private final String email;

    public ContactIng(String company, String street, String email) {
        this.company = company;
        this.street = street;
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail() {
        return email;
    }
}

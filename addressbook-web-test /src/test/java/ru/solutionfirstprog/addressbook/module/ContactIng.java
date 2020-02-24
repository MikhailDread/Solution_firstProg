package ru.solutionfirstprog.addressbook.module;

public class ContactIng {
    private final String company;
    private final String street;
    private final String email;
    private final String name;
    private final String middlename;
    private final String lastname;
    private String group;

    public ContactIng(String company, String street, String email, String name, String middlename, String lastname, String group) {
        this.company = company;
        this.street = street;
        this.email = email;
        this.name = name;
        this.middlename = middlename;
        this.lastname = lastname;
        this.group = group;
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

    public String getName() {
            return name;
        }

        public String getMiddlename() {
            return middlename;
        }

        public String getLastname() {
            return lastname;
        }

    public String getGroup() {
        return group;
    }
}


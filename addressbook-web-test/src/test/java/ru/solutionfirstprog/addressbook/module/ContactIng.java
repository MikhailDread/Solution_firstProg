package ru.solutionfirstprog.addressbook.module;

import java.util.Objects;

public class ContactIng {

    private int id = Integer.MAX_VALUE;;
    private String company;
    private String street;
    private String email;
    private String name;
    private String middlename;
    private String lastname;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhone;

    public String getAllPhone() {
        return allPhone;
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactIng that = (ContactIng) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public ContactIng withId(int id) {
        this.id = id;
        return this;
    }

    public ContactIng withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactIng  withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactIng  withStreet(String street) {
        this.street = street;
        return this;
    }

    public ContactIng  withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactIng  withName(String name) {
        this.name = name;
        return this;
    }

    public ContactIng withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactIng  withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactIng withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactIng withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactIng withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactIng withAllPhones(String allPhone) {
        this.allPhone = allPhone;
        return this;
    }

    @Override
    public String toString() {
        return "ContactIng{" +
                "lastname='" + lastname + '\'' +
                '}';
    }

}


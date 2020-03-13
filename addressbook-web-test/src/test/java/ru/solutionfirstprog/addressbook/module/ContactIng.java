package ru.solutionfirstprog.addressbook.module;

import java.util.Objects;

public class ContactIng {

    private int id = Integer.MAX_VALUE;;
    private String company;
    private String street;
    private String email1;
    private String name;
    private String middlename;
    private String lastname;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhone;
    private String email2;
    private String email3;
    private String allEmail;


    public String getAllPhone() {
        return allPhone;
    }

    public String getCompany() {
        return company;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail1() {
        return email1;
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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmail() {
        return allEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactIng that = (ContactIng) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
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

    public ContactIng withEmail1(String email1) {
        this.email1 = email1;
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

    public ContactIng withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactIng withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactIng withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    @Override
    public String toString() {
        return "ContactIng{" +
                "lastname='" + lastname + '\'' +
                '}';
    }

}


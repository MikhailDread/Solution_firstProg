package ru.solutionfirstprog.addressbook.module;

import java.util.Objects;

public class GroupInf {
    private int id;
    private final String name;
    private final String header;
    private final String feeder;


    public GroupInf(String name, String header, String feeder) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.feeder = feeder;
    }


    public GroupInf(int id, String name, String header, String feeder) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.feeder = feeder;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFeeder() {
        return feeder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupInf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInf groupInf = (GroupInf) o;
        return Objects.equals(name, groupInf.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package ru.solutionfirstprog.addressbook.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class GroupInf {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    private  String name;
    private  String header;
    private  String feeder;

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

    public GroupInf withId(int id) {
        this.id = id;
        return this;
    }

    public GroupInf withName(String name) {
        this.name = name;
        return this;
    }

    public GroupInf withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupInf withFeeder(String feeder) {
        this.feeder = feeder;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInf groupInf = (GroupInf) o;
        return id == groupInf.id &&
                Objects.equals(name, groupInf.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GroupInf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}

package ru.solutionfirstprog.addressbook.module;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupInf {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "group_name")
    private  String name;
    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private  String header;
    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
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
                Objects.equals(name, groupInf.name) &&
                Objects.equals(header, groupInf.header) &&
                Objects.equals(feeder, groupInf.feeder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, feeder);
    }

    @Override
    public String toString() {
        return "GroupInf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}

package ru.solutionfirstprog.addressbook.module;

public class GroupInf {
    private final String name;
    private final String header;
    private final String feeder;

    public GroupInf(String name, String header, String feeder) {
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
}

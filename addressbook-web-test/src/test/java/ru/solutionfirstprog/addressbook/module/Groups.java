package ru.solutionfirstprog.addressbook.module;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Groups extends ForwardingSet<GroupInf> {

    private Set<GroupInf> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<>();
    }

    public Groups(Collection<GroupInf> groups) {
        this.delegate = new HashSet<>(groups);
    }


    @Override
    protected Set<GroupInf> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupInf group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(GroupInf group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}

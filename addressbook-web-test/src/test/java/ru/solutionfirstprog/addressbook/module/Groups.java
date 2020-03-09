package ru.solutionfirstprog.addressbook.module;

import com.google.common.collect.ForwardingSet;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupInf> {

    private Set<GroupInf> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<>();
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

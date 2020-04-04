package ru.solutionfirstprog.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserHb> {
    private Set<UserHb> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserHb>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<UserHb>();
    }

    public Users(Collection<UserHb> contacts) {
        this.delegate = new HashSet<UserHb>(contacts);
    }

    @Override
    protected Set<UserHb> delegate() {
        return delegate;
    }

    public Users withAdded(UserHb user){
        Users users = new Users(this);
        users.add(user);
        return users;
    }

    public Users without(UserHb user){
        Users users = new Users(this);
        users.remove(user);
        return users;
    }
}

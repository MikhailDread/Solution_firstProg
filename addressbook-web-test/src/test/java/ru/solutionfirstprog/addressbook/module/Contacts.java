package ru.solutionfirstprog.addressbook.module;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactIng> {


    private Set<ContactIng> delegate;

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<ContactIng> delegate() {
        return delegate;
    }

    public Contacts(Contacts contact){
        this.delegate = new HashSet<>(contact.delegate);
    }

    public Contacts withAdded(ContactIng contact){
        Contacts cont = new Contacts(this);
        cont.add(contact);
        return cont;
    }

    public Contacts without(ContactIng contact){
        Contacts cont = new Contacts(this);
        cont.remove(contact);
        return cont;
    }
}

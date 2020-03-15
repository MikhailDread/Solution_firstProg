package ru.solutionfirstprog.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.solutionfirstprog.addressbook.module.ContactIng;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactingGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;


    public static void main(String[] args) throws IOException {
        ContactingGenerator contactingGenerator = new ContactingGenerator();
        JCommander jCommander = new JCommander(contactingGenerator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e){
            jCommander.usage();
            return;
        }
        contactingGenerator.run();
    }

    private void run() throws IOException {
        List<ContactIng> contact = generatorContact(count);
        save(contact, new File(file));
    }

    private void save(List<ContactIng> contact, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactIng e : contact){
            writer.write(String.format(e.getName() + ";" + e.getMiddlename() + ";" + e.getLastname() + "\n"));
        }
        writer.close();
        }

    private List<ContactIng> generatorContact(int count) {
        List<ContactIng> contact = new ArrayList<>();
        for(int i = 0; i < count; i++){
            contact.add(new ContactIng().withName("test 1 " + i).withMiddlename("test 2 " + i)
            .withLastname("test 3 " + i));
        }

        return contact;
    }
}

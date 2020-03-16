package ru.solutionfirstprog.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-d", description = "Date format")
    public String format;


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
        if (format.equals("csv")) {
            saveAsCSV(contact, new File(file));
        }
        else if(format.equals("xml")){
            saveAsXML(contact, new File(file));
        }
        else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXML(List<ContactIng> contact, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactIng.class);
        String xml = xStream.toXML(contact);
        try (Writer writer = new FileWriter(file)) {
        writer.write(xml);}
    }

    private void saveAsCSV(List<ContactIng> contact, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
        for(ContactIng e : contact){
            writer.write(String.format(e.getName() + ";" + e.getMiddlename() + ";" + e.getLastname()
                    + ";" + e.getGroup() + ";" + e.getStreet() + ";" + e.getEmail1() + "\n"));
        }}
        }

    private List<ContactIng> generatorContact(int count) {
        List<ContactIng> contact = new ArrayList<>();
        for(int i = 0; i < count; i++){
            contact.add(new ContactIng().withName("test 1 " + i).withMiddlename("test 2 " + i)
            .withLastname("test 3 " + i).withCompany("RZD").withStreet("street Pushkin").withEmail1("jonjolli@yandex.fu").withGroup("test1"));
        }

        return contact;
    }
}

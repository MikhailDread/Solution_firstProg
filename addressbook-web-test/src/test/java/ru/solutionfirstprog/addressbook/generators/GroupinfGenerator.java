package ru.solutionfirstprog.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupinfGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Date format")
    public String format;


    public static void main(String[] args) throws IOException {
        GroupinfGenerator gg = new GroupinfGenerator();
        JCommander jCommander = new JCommander(gg);
        try{
        jCommander.parse(args);}
        catch (ParameterException e){
        jCommander.usage();
        return;}

        gg.run();
    }

    private void run() throws IOException {
        List<GroupInf> group = generateGroup(count);
        if(format.equals("csv")){
        saveCSV(group, new File(file)); }
        else if(format.equals("xml")){
            saveXML(group, new File(file));
        } else if(format.equals("json")){
            saveJSON(group, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveJSON(List<GroupInf> group, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(group);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveXML(List<GroupInf> group, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupInf.class);
        xstream.alias("group", GroupInf.class);
        String xml = xstream.toXML(group);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private  void saveCSV(List<GroupInf> group, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
        for(GroupInf e : group){
            writer.write(String.format(e.getName() + ";" + e.getHeader() + ";" + e.getFeeder() + "\n"));
        } }
    }

    private  List<GroupInf> generateGroup(int count) {
        List<GroupInf> groups = new ArrayList<>();
        for(int i = 0; i < count; i++){
            groups.add(new GroupInf().withName(String.format("name is " + i))
                    .withHeader(String.format("header is\n" + i)).withFeeder(String.format("footer is\n" + i)));
        }
        return groups;
    }
}

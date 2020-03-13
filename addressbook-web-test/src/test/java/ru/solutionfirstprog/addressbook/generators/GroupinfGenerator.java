package ru.solutionfirstprog.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.testng.annotations.Parameters;
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
        save(group, new File(file));
    }

    private  void save(List<GroupInf> group, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(GroupInf e : group){
            writer.write(String.format(e.getName() + ";" + e.getHeader() + ";" + e.getFeeder() + "\n"));
        }
        writer.close();
    }

    private  List<GroupInf> generateGroup(int count) {
        List<GroupInf> groups = new ArrayList<>();
        for(int i = 0; i < count; i++){
            groups.add(new GroupInf().withName(String.format("name is " + i))
                    .withHeader(String.format("header is" + i)).withFeeder(String.format("footer is" + i)));
        }
        return groups;
    }
}

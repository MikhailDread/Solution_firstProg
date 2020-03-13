package ru.solutionfirstprog.addressbook.generators;

import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupinfGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<GroupInf> group = generateGroup(count);
        save(group, file);

    }

    private static void save(List<GroupInf> group, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(GroupInf e : group){
            writer.write(String.format(e.getName() + ";" + e.getHeader() + ";" + e.getFeeder() + "\n"));
        }
        writer.close();
    }

    private static List<GroupInf> generateGroup(int count) {
        List<GroupInf> groups = new ArrayList<>();
        for(int i = 0; i < count; i++){
            groups.add(new GroupInf().withName(String.format("test" + i))
                    .withHeader(String.format("test" + i)).withFeeder(String.format("test" + i)));
        }
        return groups;
    }
}

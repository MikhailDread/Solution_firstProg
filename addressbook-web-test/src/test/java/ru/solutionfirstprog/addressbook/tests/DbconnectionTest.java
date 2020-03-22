package ru.solutionfirstprog.addressbook.tests;

import org.testng.annotations.Test;
import ru.solutionfirstprog.addressbook.module.GroupInf;
import ru.solutionfirstprog.addressbook.module.Groups;

import java.sql.*;

public class DbconnectionTest {

    @Test
    public void testbconnection(){
        try {
           Connection conn = null;
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                            "user=root&password=&serverTimezone=UTC" );
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (resultSet.next()){
                groups .add(new GroupInf().withId(resultSet.getInt("group_id")).withName(resultSet.getNString("group_name"))
                        .withHeader(resultSet.getString("group_header")).withFeeder(resultSet.getNString("group_footer")));
            }
            resultSet.close();
            statement.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}

package org.fasttrackit.dev.todolist.db;

import java.sql.*;

/**
 * Created by icondor on 29/07/2017.
 */
public class UserDBAccess {


 //   final static String URL = "jdbc:postgresql://54.93.65.5:5432/5IonelD";



 final static String URL = "jdbc:postgresql://localhost:5432/mydb";
    final static String USERNAME = "userdb";
    final static String PASSWORD = "password1";

    public int isUserPwdOK(String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query
            String query = "SELECT id FROM users where username='"+user+"' and userpassword='"+pwd+"'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            // 6. iterate the result set and print the values

            // BIG HACK, DEMO PURPOSE, BECAUSE I CREATE A DEPENDENCY ON HOW THE IMPLEM IS DONE IN UI

            while (rs.next()) {
                found = rs.getInt("id");

            }

            // 7. close the objects
            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;

    }


// testing method :), instead of @Test
    public static void main(String[] args) {
        UserDBAccess udb = new UserDBAccess();
        int gasit;
        gasit = udb.isUserPwdOK("maria", "password1d");
        System.out.println(gasit);

    }

}

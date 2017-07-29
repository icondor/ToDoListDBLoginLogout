package org.fasttrackit.dev.todolist.db;

import org.fasttrackit.dev.todolist.ToDoBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icondor on 18/02/17.
 */
public class ToDoListDBAccess {

    final static String URL = "jdbc:postgresql://54.93.65.5:5432/5IonelD";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public List getTaskList(int userid) {

        List listaTaskList = new ArrayList<ToDoBean>();
        try {

            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            Statement st = conn.createStatement();

            String query = "SELECT * FROM tasklistionel where isdone=false and fkiduser="+userid+" order by taskname asc";
            // 5. execute a query
            ResultSet rs = st.executeQuery(query);

            // 6. iterate the result set and print the values
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("taskname");
                boolean isdone = rs.getBoolean("isdone");

                ToDoBean randcurent = new ToDoBean(id, name, isdone);


                listaTaskList.add(randcurent);

                // 7. close the objects

            }
            rs.close();
            st.close();
            conn.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listaTaskList;
    }

    public void insertTaskList(String nameOfTheTask, int userid) {

        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO tasklistionel (taskname,isdone, fkiduser) VALUES (?,?,?)");
            pSt.setString(1, nameOfTheTask);
            pSt.setBoolean(2, false);
            pSt.setInt(3, userid);


            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void markDone(int id) {

        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("update tasklistionel set isdone=true where id=?");
            pSt.setInt(1, id);


            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

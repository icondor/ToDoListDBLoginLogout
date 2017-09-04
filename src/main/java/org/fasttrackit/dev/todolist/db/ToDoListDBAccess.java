package org.fasttrackit.dev.todolist.db;

import org.fasttrackit.dev.todolist.ToDoBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icondor on 18/02/17.
 */
public class ToDoListDBAccess {

    //  final static String URL = "jdbc:postgresql://54.93.65.5:5432/5IonelD";
    //final static String USERNAME = "fasttrackit_dev";
    //final static String PASSWORD = "fasttrackit_dev";


    private final static String URL = "jdbc:postgresql://localhost:5432/mydb";
    private final static String USERNAME = "userdb";
    private final static String PASSWORD = "password1";

    public List<ToDoBean> getTaskList(int userid) {

        System.out.println("getting tasks from db");

        List<ToDoBean> listaTaskList = new ArrayList<>();
        try {

            // load the driver, optional with newer versions
            Class.forName("org.postgresql.Driver");

            // connect
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // prepare the query
            String query = "SELECT * FROM tasklistionel WHERE isdone=FALSE AND fkiduser=? ORDER BY taskname ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userid);

            // execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // iterate the result
            while (rs.next()) {
                System.out.println("many tasks here");
                int id = rs.getInt("id");
                String name = rs.getString("taskname");
                boolean isdone = rs.getBoolean("isdone");
                ToDoBean randcurent = new ToDoBean(id, name, isdone);
                listaTaskList.add(randcurent);
            }

            System.out.println("getting tasks from db, closing everything");

            // close all the objects, incl the connection to db
            rs.close();
            preparedStatement.close();
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void markDone(int id) {

        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("UPDATE tasklistionel SET isdone=TRUE WHERE id=?");
            pSt.setInt(1, id);


            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

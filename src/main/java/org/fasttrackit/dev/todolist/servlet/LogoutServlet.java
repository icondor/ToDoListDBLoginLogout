package org.fasttrackit.dev.todolist.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * Created by icondor on 22/07/2017.
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    public static final String USERNAME = "username";
    public static final String USERNAMEID = "usernameid";

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        System.out.println("intra in logout ");

        HttpSession s = request.getSession();
        s.removeAttribute(USERNAME);
        s.removeAttribute(USERNAMEID);

        response.sendRedirect("todolist.html");

    }





}

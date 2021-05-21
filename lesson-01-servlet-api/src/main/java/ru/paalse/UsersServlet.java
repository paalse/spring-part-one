package ru.paalse;

import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        pw.println("<h1>Выборка всех пользователей</h1>");
        pw.println("<table border='1'> <tr><th>Id</th><th>User name</th></tr>");
        for (User user : userRepository.findAll()) {
            pw.println("<tr>");
            pw.println("<td>" + user.getId() + "</td>");
            pw.println("<td><a href='" + getServletContext().getContextPath() + "/user/" + user.getId() + "'>" + user.getUsername() + "</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
    }
}

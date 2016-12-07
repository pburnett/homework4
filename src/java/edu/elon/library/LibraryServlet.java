/*
 * © Dylan Burnett and Tanner McIntyre 2016. All rights reserved.
 */
package edu.elon.library;

import edu.elon.user.User;
import elon.edu.data.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dylan
 */
public class LibraryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);

        String url = "/index.jsp";
        String action = request.getParameter("action");

        // perform action and set URL to appropriate page
        if (action == null) {
            url = "/index.jsp";
        } else if (action.equals("manage")) {
            ArrayList<User> users = UserDB.selectUsers();
            for (User user : users) {
                user.setIsDue(user.LateChecker());
            }
            request.setAttribute("users", users);
            url = "/manage.jsp";
        } else if (action.equals("checkout")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String bookTitle = request.getParameter("bookTitle");
            Date date = new Date(System.currentTimeMillis() + 1209600000);
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyLocalizedPattern("MM-dd-yyyy");
            String dueDate = sdf.format(date);
            System.out.println(dueDate);
            User user = new User(firstName, lastName, email, bookTitle, dueDate);
            UserDB.insert(user);
            request.setAttribute("user", user);
            url = "/checkoutConfirm.jsp";
        } else if (action.equals("checkin")) {
            String email = request.getParameter("email");
            String book = request.getParameter("bookTitle");
            User user = UserDB.selectUser(email, book);
            UserDB.delete(user);
            ArrayList<User> users = UserDB.selectUsers();
            request.setAttribute("users", users);
            url = "/library?action=manage";
        } else if (action.equals("goToCheckout")) {
            url = "/checkout.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}

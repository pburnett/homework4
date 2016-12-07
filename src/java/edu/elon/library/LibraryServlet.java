/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elon.library;

import edu.elon.user.User;
import elon.edu.data.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        HttpSession session = request.getSession();
        String url = "/index.jsp";
        String action = request.getParameter("action");

        // perform action and set URL to appropriate page
        if (action == null) {
            url = "/index.jsp";
        } else if (action.equals("manage")) {
            ArrayList<User> users = UserDB.selectUsers();
            for(User user : users){
                String rawDate = user.getDueDate();
                user.setIsDue(LateChecker(rawDate));
            }
            request.setAttribute("users", users);
            url = "/manage.jsp";
            System.out.println("hello");
        } else if (action.equals("checkout")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String bookTitle = request.getParameter("bookTitle");
            Date date = new Date(System.currentTimeMillis() + 1209600000);
            String dueDateUnformatted = date.toString();
            String dueDate = DateBuilder(dueDateUnformatted);
            System.out.println(bookTitle);
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

    public String DateBuilder(String dateStr) {
        String year = dateStr.substring(0, 4);
        String month = dateStr.substring(5, 7);
        String day = dateStr.substring(8);
        return month + "-" + day + "-" + year;
        //int current
    }
    
    public boolean LateChecker(String rawDate){
        String month = rawDate.substring(0,2);
        int monthNum = Integer.parseInt(month);
        String day = rawDate.substring(3,5);
        int dayNum = Integer.parseInt(day);
        String year = rawDate.substring(6);
        int yearNum = Integer.parseInt(year);
        Date lateDate = new Date(yearNum, monthNum, dayNum);
        Date date = new Date(System.currentTimeMillis());
        if(date.after(lateDate)){
            return true;
        } else{
            return false;
        }
    }

}

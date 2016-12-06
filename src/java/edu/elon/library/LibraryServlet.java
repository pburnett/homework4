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
      request.setAttribute("users", users);
      url = "/manage.jsp";
    } else if (action.equals("checkout")) {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String bookTitle = request.getParameter("bookTitle");

      User user = new User(firstName, lastName, email, bookTitle);
      UserDB.insert(user);
      url = "/checkoutConfirm.jsp";
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

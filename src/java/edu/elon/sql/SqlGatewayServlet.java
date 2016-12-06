package edu.elon.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import elon.edu.data.ConnectionPool;

public class SqlGatewayServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {

    String sqlStatement = request.getParameter("sqlStatement");
    String sqlResult = "";
    try {
      // load the driver
      Class.forName("com.mysql.jdbc.Driver");

      // get a connection to local MySQL database. The database is
      // called murach. The database can be created by running the
      // create_database.sql file provided in Moodle. My suggestion for
      // simplicity is to modify the create_database.sql file to 
      // create a database with the same name as your database on
      // openshift and to then modify the next line of code to change
      // murach to your app name.
      String dbURL = "jdbc:mysql://localhost:3306/firstapp";
      String username = "root";
      String password = "mysqluser";

            //Determine if running on OpenShift by getting value of
      //OpenShift environement variable. If it is set (non null) then
      //we are and need to reset some variables. If no then no need to
      //reset and we are running locally.
      String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
      if ((host != null) && (host.trim().length() > 1)) {
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String appname = System.getenv("OPENSHIFT_APP_NAME");
        username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        dbURL = "jdbc:mysql://" + host + ":" + port + "/" + appname;
      }

      //Connection connection = DriverManager.getConnection(dbURL, username, password);
      ConnectionPool pool = ConnectionPool.getInstance();
      Connection connection = pool.getConnection();

      // create a statement
      Statement statement = connection.createStatement();

      // parse the SQL string
      sqlStatement = sqlStatement.trim();
      if (sqlStatement.length() >= 6) {
        String sqlType = sqlStatement.substring(0, 6);
        if (sqlType.equalsIgnoreCase("select")) {
          // create the HTML for the result set
          ResultSet resultSet
            = statement.executeQuery(sqlStatement);
          sqlResult = SQLUtil.getHtmlTable(resultSet);
          resultSet.close();
        } else {
          int i = statement.executeUpdate(sqlStatement);
          if (i == 0) { // a DDL statement
            sqlResult
              = "<p>The statement executed successfully.</p>";
          } else { // an INSERT, UPDATE, or DELETE statement
            sqlResult
              = "<p>The statement executed successfully.<br>"
              + i + " row(s) affected.</p>";
          }
        }
      }
      statement.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      sqlResult = "<p>Error loading the databse driver: <br>"
        + e.getMessage() + "</p>";
    } catch (SQLException e) {
      sqlResult = "<p>Error executing the SQL statement: <br>"
        + e.getMessage() + "</p>";
    }

    HttpSession session = request.getSession();
    session.setAttribute("sqlResult", sqlResult);
    session.setAttribute("sqlStatement", sqlStatement);

    String url = "/index.jsp";
    getServletContext()
      .getRequestDispatcher(url)
      .forward(request, response);
  }
}

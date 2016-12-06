<%-- 
    Document   : index
    Created on : Dec 4, 2016, 5:11:37 PM
    Author     : Dylan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
<!DOCTYPE html>

<div id="redBorder">
  <div id="mainContent">
    <h1>Currently checked out books</h1>

    <table>

      <tr>
        <th>Patron Name</th>
        <th>Email Address</th>
        <th >Book Title</th>
        <th>Due Date</th>
        <th>Overdue</th>
      </tr>

      <c:forEach var="user" items="${users}">
        <tr>
          <td><c:out value="${user.firstName}"/> ${user.lastName}</td>
          <td>${user.email}</td>
          <td>${user.bookTitle}</td>
          <td>Due Date to fix</td>
          <td>Overdue to fix</td>
          <td><input type="submit" value="Check in" id="checkin" /></td>
        </tr>
      </c:forEach>

    </table>

    <p><a href="index.jsp" title="Return to front page"><strong>Return to front page</strong></a></p>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
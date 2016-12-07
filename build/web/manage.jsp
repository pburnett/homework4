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
        <th>Book Title</th>
        <th>Due Date</th>
        <th>Overdue</th>
        <th></th>
      </tr>
      
      <c:forEach var="user" items="${users}">
        <tr>
          <td>${user.firstName} ${user.lastName}</td>
          <td>${user.email}</td>
          <td>${user.bookTitle}</td>
          <td>${user.dueDate}</td>
          <td><c:if test="${user.isDue == true}">Overdue</c:if></td>      
          <td><form action="library?action=checkin&amp;email=${user.email}&amp;bookTitle=${user.bookTitle}" method="post">
          <input type="submit" name="action" value="Check in" id="checkin" />
          </form></td>

        </tr>
      </c:forEach>

    </table>

    <p><a href="library?action=null" title="Return to front page"><strong>Return to front page</strong></a></p>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
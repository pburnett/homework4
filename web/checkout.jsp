<%-- 
    Document   : checkout
    Created on : Dec 4, 2016, 5:11:37 PM
    Author     : © Dylan Burnett and Tanner McIntyre 2016. All rights reserved.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
<!DOCTYPE html>

<div id="redBorder">
  <div id="mainContent">
    <h1>Checkout A Book</h1>
  <form action="library" method="post">
    <input type="hidden" name="action" value="checkout" />
    <p>
      <label class="left">First Name:</label>
      <input id="firstNameID" name="firstName" type="text" title="First Name" value="${user.firstName}" required/>
    </p>
    <p>
      <label class="left">Last Name:</label>
      <input id="lastNameId" name="lastName" type="text" title="Last Name" value="${user.lastName}" required/>
    </p>
    <p>
      <label class="left">Email Address:</label>
      <input id="emailAddressID" name="email" type="email" title="Email Address" value="${user.email}" required/>
    </p>
    <p>
      <label class="left">Book Title</label>
      <input id="bookTitleID" name="bookTitle" type="text" title="Book Title" value="${user.bookTitle}" required/>
    </p>
    <p><input type="submit" value="Checkout" id="checkout" /></p>
  </form>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
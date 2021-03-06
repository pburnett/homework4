<%-- 
    Document   : checkoutConfirm
    Created on : Dec 4, 2016, 5:11:37 PM
    Author     : © Dylan Burnett and Tanner McIntyre 2016. All rights reserved.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/includes/header.html" %>
<!DOCTYPE html>

<div id="redBorder">
  <div id="mainContent">
    <p>Thank you for your patronage of the Belk Library. You've successfully checked out the book, ${user.bookTitle}. Please note that this book is due back on ${user.dueDate}. A friendly email reminder will be sent to you if your book becomes overdue.</p>
    <p><a href="library?action=null" title="Return"><strong>Return to front page</strong></a></p>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
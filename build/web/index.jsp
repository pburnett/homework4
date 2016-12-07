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
    <input type="hidden" name="action" value="checkout" />
    <p><a href="checkout.jsp" title="Checkout"><strong>Check out a book</strong></a></p>
    <p><a href="library?action=manage" title="Manage" ><strong>Manage checked out books</strong></a></p>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
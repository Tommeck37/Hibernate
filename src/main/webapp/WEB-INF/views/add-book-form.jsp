<%--
  Created by IntelliJ IDEA.
  User: bigbook
  Date: 03/04/2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Book Form</title>
</head>
<body>
<%--@elvariable id="book" type="pl.coderslab.entity.Book"--%>
<form:form modelAttribute="book" >
    Title: <form:input path="title"/>
    Rating: <form:input path="rating" />
    Description <form:textarea path="description"/>
    <form:select path="publisher" items="${publishers}" itemLabel="name" itemValue="id"/>
    <input type="submit">
</form:form>
</body>
</html>
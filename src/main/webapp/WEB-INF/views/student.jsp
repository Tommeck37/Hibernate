<%--
  Created by IntelliJ IDEA.
  User: tomek
  Date: 03.04.2022
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Person Form</title>
</head>
<body>
<%--@elvariable id="student" type="pl.coderslab.app.Student"--%>
<form:form modelAttribute="student" method="post">
    Email <form:input path="firstName"/><br>
    Login <form:input path="lastName"/><br>
    Male<form:radiobutton path="gender" value="M"/><br>
    Female <form:radiobutton path="gender" value="K"/><br>
    Countries<form:select path="country" items="${countries}"/><br>
    Notes <form:textarea path="notes"/><br>
    MailingList <form:checkbox path="mailingList"/><br>
    Programming Skills<form:select path="programmingSkills" items="${skills}" multiple="true"/><br>
    Hobbies<form:checkboxes items="${hobbies}" path="hobbies" />
    <input type="submit">
</form:form>
</body>
</html>

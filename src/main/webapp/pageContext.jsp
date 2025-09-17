<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> forward vs include </title>
</head>
<body>
<h1>THIS IS pageContext.jsp.</h1>

<%
    String id = "academy";
%>

<jsp:include page="sub.jsp">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:include>

<jsp:forward page="sub.jsp">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>

<jsp:useBean id="numberList" scope="request"
             type="java.util.List<java.lang.Integer>"
             class="java.util.ArrayList"/>

<jsp:useBean id="user1" scope="request" class="com.nhnacademy.hello.bean.User"/>
<jsp:setProperty name="user1" property="age" value="35"/>
</body>
</html>
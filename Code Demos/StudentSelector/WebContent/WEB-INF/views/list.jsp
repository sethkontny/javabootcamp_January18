<%--
  Created by IntelliJ IDEA.
  User: maurice
  Date: 6/28/17
  Time: 1:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Student List</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/"><h2>Home</h2></a>
<a href="admin"><h2>Admin Page</h2></a>

<p>
    <h3>Legend</h3>
    A = Available <br>
    T = Temporary state (pre-unavailable) <br>
    X = Unavailable <br>
</p>
<table border="1">
    <th>First Name</th>
    <th>Last Name</th>
    <th>Status</th>
    <c:forEach items="${results}" var ="list">
        <tr>
            <td>${list.firstName}</td>
            <td>${list.lastName}</td>
            <td>${list.status}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>

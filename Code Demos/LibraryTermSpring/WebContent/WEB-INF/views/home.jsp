<%--
  Created by IntelliJ IDEA.
  User: maurice
  Date: 5/15/17
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Library Terminal Spring</title>
    <link href="${pageContext.request.contextPath}/resources/css/validation.css" rel="stylesheet" >
</head>
<body>

<a href="${pageContext.request.contextPath}/">Index</a><br>
<h1>Add a new book</h1>
<form:form action="${pageContext.request.contextPath}/addbook" modelAttribute="command" method="get">

<fieldset>
    <legend>Add A Book:</legend>
    <label>Title</label>
    <form:input type="text" name="title" path="title"/><form:errors path="title" cssClass="error"/><br>

    <label>Author</label>
    <form:input type="text" name="author" path="author"/><form:errors path="author" cssClass="error"/><br>

    <label>Image URL</label>
    <form:input type="text" name="thumbURL" path="thumbURL"/><br>

    <select id="library" name="libraryadd">
        <c:forEach items="${librarylist}" var ="libList">
            <option value="${libList.id}">${libList.name}</option>
        </c:forEach>
    </select><br>

    <button type="submit">Add Book</button>
</fieldset>
</form:form>
<h2>Search for Books</h2>
<form action="${pageContext.request.contextPath}/search" method="get">

    <fieldset>
        <legend>Search for books:</legend>
    <label>Search Text</label>
    <input type="text" name="searchtxt">
    <button type="submit" id="authsearch" name="mode" value="authorsearch">Search by Author</button>
    <button type="submit" name="mode" value="titlesearch">Search by Title KeyWord</button><br>
    <button type="submit" name="mode" value="listbooks">List Books</button>

    <select id="library" name="library">
        <c:forEach items="${librarylist}" var ="libList">
            <option value="${libList.id}">${libList.name}</option>
        </c:forEach>
    </select><br>

    <label>Verify Age (must be 18 or over)</label>
    <input type="number" name="age" min="18" required><%--Demo of HTML5 front-end validation--%>
    </fieldset>
</form>

<table border="1">
    <th>BookId</th>
    <th>Cover Image</th>
    <th>Title</th>
    <th>Author</th>
    <th>Location</th>
    <th>Status</th>
    <th>Due Date</th>
    <c:forEach items="${results}" var ="books">
        <tr>
            <td>${books.bookid}</td>
                    <td>
                        <a href="${books.thumbURL}" target="_blank">
                            <img src="${books.thumbURL}" style="width: 100px; height: 100px" alt="${books.title}">
                        </a>
                    </td>
            <td>${books.title}</td>
            <td>${books.author}</td>
            <td>${books.libraryInfoDto.name}</td>
            <c:choose>
                <c:when test="${books.status == true}">
                    <td><input type="checkbox" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')" checked><div id="${books.bookid}">Checked Out</div></td>
                </c:when>
                <c:otherwise>
                    <td><input type="checkbox" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')"><div id="${books.bookid}">Checked In</div></td>
                </c:otherwise>
            </c:choose>

            <td><div id="due${books.bookid}">${books.dueDate}</div></td>
        </tr>

    </c:forEach>

</table>

<script src="//code.jquery.com/jquery-2.2.1.js"></script><script src="${pageContext.request.contextPath}/resources/js/home.js"></script>

</body>
</html>

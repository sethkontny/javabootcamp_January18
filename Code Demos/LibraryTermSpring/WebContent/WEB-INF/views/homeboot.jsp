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
    <link href="${pageContext.request.contextPath}/resources/css/home.css" rel="stylesheet" >
    <!-- Latest compiled and minified CSS -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="${pageContext.request.contextPath}/resources/css/validation.css" rel="stylesheet" >

</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="${pageContext.request.contextPath}/">Index</a>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Add a new book</h3>
                </div>
                <div class="panel-body">
                    <%--Ref:https://bootsnipp.com/snippets/featured/compact-login-form-bs-3--%>
                    <form:form accept-charset="UTF-8" role="form" action="${pageContext.request.contextPath}/addbookboot" modelAttribute="command" method="get">
                        <fieldset>
                            <div class="form-group">
                                <label>Title</label>
                                <input class="form-control" placeholder="title" name="title" type="text"><form:errors path="title" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label>Author</label>
                                <input class="form-control" placeholder="author" name="author" type="author" value=""><form:errors path="author" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label>
                                    <label>Image URL</label>
                                    <input class="form-control" placeholder="thumbURL" name="thumbURL" type="text">
                                </label>
                            </div>

                            <div class="form-group">
                                <label>Select Target Library</label>
                                <select id="library" name="libraryadd" class="custom-select">
                                    <c:forEach items="${librarylist}" var ="libList">
                                        <option value="${libList.id}">${libList.name}</option>
                                    </c:forEach>
                                </select><br>
                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Add Book">
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <%--Ref: https://bootsnipp.com/snippets/featured/easy-table-filter--%>
    <div class="row">
        <section class="content">
            <h1>Library Terminal Spring</h1>
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                            <form action="${pageContext.request.contextPath}/searchboot" method="get">
                                <div class="form-group">
                                    <label for="library" class="col-sm-2 control-label">Select Library</label>
                                </div>
                                <div class="form-group">
                                    <label for="searchtxt" class="col-sm-2 control-label">Search Text</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" id="searchtxt" name="searchtxt" class="form-control" placeholder="Author or Title keyword text" autofocus>
                                            <span class="input-group-btn">
                                                <button type="submit" name="mode" value="authorsearch" class="btn btn-success" data-target="pagado">Search by Author</button>
                                                <button type="submit" name="mode" value="titlesearch" class="btn btn-warning" data-target="pendiente">Search by Title KeyWord</button>
                                                <button type="submit" name="mode" value="listbooks" class="btn btn-danger" data-target="cancelado">List Books</button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group form-inline">
                                    <label>
                                        Verify Age (must be 18 or over)
                                        <input class="form-control" type="number" name="age" min="18" required><%--Demo of HTML5 front-end validation--%>
                                    </label>
                                    <select id="library" name="library">
                                        <c:forEach items="${librarylist}" var ="libList">
                                            <option value="${libList.id}">${libList.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        <br>
                        <div class="table-container">
                            <table class="table table-filter">
                                <tbody>
                            <c:forEach items="${results}" var ="books">
                                <tr data-status="pagado">
                                    <td>
                                        <div class="ckbox">
                                            <c:choose>
                                                <c:when test="${books.status == true}">
                                                    <input type="checkbox" id="checkbox${books.bookid}" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')" checked>
                                                    <label for="checkbox${books.bookid}"></label>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="checkbox${books.bookid}" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')">
                                                    <label for="checkbox${books.bookid}"></label>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </td>
                                    <td>
                                        <div>${books.bookid}</div>
                                    </td>
                                    <td>
                                        <div class="media">
                                            <a href="${books.thumbURL}" class="pull-left" target="_blank">
                                                <img src="${books.thumbURL}" class="media-photo" alt="${books.title}">
                                            </a>
                                            <div class="media-body">
                                                <span id="due${books.bookid}" class="media-meta pull-right">${books.dueDate}</span>

                                                <h4 class="title">
                                                ${books.title}
                                                    <c:choose>
                                                        <c:when test="${books.status == true}">
                                                            <span id="${books.bookid}" class="pull-right pagado">Date Due: </span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span id="${books.bookid}" class="pull-right pagado">Checked In</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </h4>
                                                <p class="summary">${books.author}<br>${books.libraryInfoDto.name}</p>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="content-footer">
                    <p>
                        Page © - 2016 <br>
                        Powered By <a href="https://www.facebook.com/tavo.qiqe.lucero" target="_blank">TavoQiqe</a>
                    </p>
                </div>
            </div>
        </section>

    </div>
</div>

<script src="//code.jquery.com/jquery-2.2.1.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/home.js"></script>

</body>
</html>

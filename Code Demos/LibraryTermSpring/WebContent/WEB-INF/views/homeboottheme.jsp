<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Terminal Spring</title>

    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="static/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="static/css/local.css" />

    <script type="text/javascript" src="static/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
    

    <!-- you need to include the shieldui css and js assets in order for the charts to work -->
    <link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light-bootstrap/all.min.css" />
    <link id="gridcss" rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/dark-bootstrap/all.min.css" />

    <script type="text/javascript" src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
    <script type="text/javascript" src="http://www.prepbootstrap.com/Content/js/gridData.js"></script>
</head>
<body>
    <div id="wrapper">
          <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Index</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul id="active" class="nav navbar-nav side-nav">
                    <li class="selected"><a href="index.html"><i class="fa fa-bullseye"></i> Library Catalog</a></li>
                    <li><a href="static/portfolio.html"><i class="fa fa-tasks"></i> Portfolio</a></li>
                    <li><a href="static/blog.html"><i class="fa fa-globe"></i> Blog</a></li>
                    <li><a href="static/signup.html"><i class="fa fa-list-ol"></i> SignUp</a></li>
                    <li><a href="static/register.html"><i class="fa fa-font"></i> Register</a></li>
                    <li><a href="static/timeline.html"><i class="fa fa-font"></i> Timeline</a></li>
                    <li><a href="static/forms.html"><i class="fa fa-list-ol"></i> Forms</a></li>
                    <li><a href="static/typography.html"><i class="fa fa-font"></i> Typography</a></li>
                    <li><a href="static/bootstrap-elements.html"><i class="fa fa-list-ul"></i> Bootstrap Elements</a></li>
                    <li><a href="static/bootstrap-grid.html"><i class="fa fa-table"></i> Bootstrap Grid</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right navbar-user">
                    <li class="dropdown messages-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> Messages <span class="badge">2</span> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header">2 New Messages</li>
                            <li class="message-preview">
                                <a href="#">
                                    <span class="avatar"><i class="fa fa-bell"></i></span>
                                    <span class="message">Security alert</span>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li class="message-preview">
                                <a href="#">
                                    <span class="avatar"><i class="fa fa-bell"></i></span>
                                    <span class="message">Security alert</span>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#">Go to Inbox <span class="badge">2</span></a></li>
                        </ul>
                    </li>
                    <li class="dropdown user-dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Steve Miller<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                            <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>

                        </ul>
                    </li>
                    <li class="divider-vertical"></li>
                    <li>
                        <form class="navbar-search">
                            <input type="text" placeholder="Search" class="form-control">
                        </form>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Library Terminal <small>Bootstrap Template Demo</small></h1>
                    <div class="alert alert-dismissable alert-success">
                        <form action="${pageContext.request.contextPath}/searchboottheme" method="get">
                         <div class="form-group input-group">
                            <input type="text" class="form-control" id="searchtxt" name="searchtxt" placeholder="Book Author or Title Keyword">
                            <span class="input-group-btn">
                                <button type="submit" name="mode" value="authorsearch" class="btn btn-info">Search By Author</button>
				                <button type="submit" name="mode" value="titlesearch" class="btn btn-primary">Search By Title</button>
				                <button type="submit" name="mode" value="listbooks" class="btn btn-default" ><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                            <div class="form-group form-inline">
                                <label>
                                    Verify Age (must be 18 or over)
                                    <input class="form-control" type="number" name="age" min="18" required><%--Demo of HTML5 front-end validation--%>
                                </label>

                                <select id="library" name="library" class="form-control">
                                    <c:forEach items="${librarylist}" var ="libList">
                                        <option value="${libList.id}">${libList.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-list"></i> Search Results</h3>
                        </div>
                       <!-- <div class="panel-body">
                            <div id="shieldui-grid1"></div>
                        </div> -->
<div class="panel-body">
    <div id="shieldui-grid1" role="grid" aria-readonly="true" class="sui-grid sui-grid-core">
        <div class="sui-gridheader">
            <table class="sui-table">
                <colgroup>
                    <col style="width:60px">
                    <col style="width:60px">
                    <col>
                    <col>
                    <col>
                </colgroup>
                <thead>
                <tr role="row" class="sui-columnheader">
                    <th data-field="Status" role="columnheader" tabindex="-1" class="sui-headercell"><a href="#"
                                                                                                        class="sui-link sui-unselectable"
                                                                                                        unselectable="on"
                                                                                                        tabindex="-1">Status</a>
                    </th>
                    <th data-field="Id" role="columnheader" tabindex="-1" class="sui-headercell"><a href="#"
                                                                                                    class="sui-link sui-unselectable"
                                                                                                    unselectable="on"
                                                                                                    tabindex="-1">Id</a>
                    </th>
                    <th data-field="Cover Art" role="columnheader" tabindex="-1" class="sui-headercell"><a href="#"
                                                                                                           class="sui-link sui-unselectable"
                                                                                                           unselectable="on"
                                                                                                           tabindex="-1">Cover
                        Art</a></th>
                    <th data-field="Description" role="columnheader" tabindex="-1" class="sui-headercell"><a href="#"
                                                                                                             class="sui-link sui-unselectable"
                                                                                                             unselectable="on"
                                                                                                             tabindex="-1">Description</a>
                    </th>

                    <th data-field="Due Date" role="columnheader" tabindex="-1" class="sui-headercell"><a href="#"
                                                                                                          class="sui-link sui-unselectable"
                                                                                                          unselectable="on"
                                                                                                          tabindex="-1">Due Date</a></th>
                </tr>
                </thead>
                <tbody class="sui-hide">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="sui-gridcontent">
            <table class="sui-table">
                <colgroup>
                    <col style="width:60px">
                    <col style="width:60px">
                    <col>
                    <col>
                    <col>
                </colgroup>
                <tbody>
                    <c:forEach items="${results}" var ="books">
                                    <tr class="sui-row" role="row">
                                        <td role="gridcell" tabindex="-1" class="sui-cell">
                                            <c:choose>
                                                <c:when test="${books.status == true}">
                                                    <div class="checkbox"><input type="checkbox" id="checkbox${books.bookid}" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')" checked></div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="checkbox"><input type="checkbox" id="checkbox${books.bookid}" onchange="checkOut(this,${books.bookid}, '${pageContext.request.contextPath}')"></div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td role="gridcell" tabindex="-1" class="sui-cell">${books.bookid}</td>
                                        <td role="gridcell" tabindex="-1" class="sui-cell"><a href="${books.thumbURL}" class="pull-left" target="_blank"><img class="thumbnail img-responsive"
                                                                                                alt="Bootstrap template" src="${books.thumbURL}"></a>
                                        </td>
                                        <td role="gridcell" tabindex="-1" class="sui-cell"><h2 class="text-primary">${books.title}</h2>
                                            <p>${books.author}<br>${books.libraryInfoDto.name}</p>
                                        </td>
                                        <td role="gridcell" tabindex="-1" class="sui-cell">
                                            <c:choose>
                                                <c:when test="${books.status == true}">
                                                    <span id="${books.bookid}" class="pull-right pagado">Date Due: </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span id="${books.bookid}" class="pull-right pagado">Checked In</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <span id="due${books.bookid}" class="text-muted media-meta pull-right">${books.dueDate}</span>
                                        </td>
                                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    Demo Version<a href="http://www.shieldui.com" target="_blank" style="color: grey; text-decoration: none;"> by
    ShieldUI</a>
</div>

                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-book"></i>Add A Book</h3>
                        </div>
                        <div class="panel-body feed">
		              <%--<form role="form">--%>
                    <%--Ref:https://bootsnipp.com/snippets/featured/compact-login-form-bs-3--%>
                    <form:form accept-charset="UTF-8" role="form" action="${pageContext.request.contextPath}/addbookboottheme" modelAttribute="command" method="get">
		                <div class="form-group has-success">
		                    <label class="control-label">Title</label>
		                    <input type="text" class="form-control" placeholder="title" name="title" type="title" value=""><form:errors path="title" cssClass="text-danger"/>
		                </div>

		                <div class="form-group has-success">
		                    <label class="control-label">Author</label>
		                    <input type="text" class="form-control" placeholder="author" name="author" type="author" value=""><form:errors path="author" cssClass="text-danger"/>
		                </div>

		                <div class="form-group has-success">
		                    <label class="control-label">Image URL</label>
		                    <input type="text" class="form-control" placeholder="thumbURL" name="thumbURL" >
		                </div>

                        <div class="form-group has-success">
                            <label class="control-label">Select Target Library</label>
                            <select id="library" name="libraryadd" class="form-control">
                                <c:forEach items="${librarylist}" var ="libList">
                                    <option value="${libList.id}">${libList.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                          <button type="submit" class="btn btn-info">Add Book</button>
                    </form:form>
		            <%--</form>--%>
                        </div>
                    </div>
                </div>
            </div>                              
            </div>
        </div>
    </div>
    <!-- /#wrapper -->

    <script src="resources/js/home.js"></script>
</body>
</html>                      

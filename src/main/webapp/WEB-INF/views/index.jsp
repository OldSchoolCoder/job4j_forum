<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="https://getbootstrap.com/docs/5.1/examples/offcanvas-navbar/offcanvas.css" rel="stylesheet">
    <title>Main page</title>
</head>

<body class="bg-white" style="padding-top: 3.5rem; min-height: 75rem;">
<nav class="navbar  bg-white  fixed-top">
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom"></header>
        <a href="/"
           class="d-flex align-items-center mx-3 mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <span style="font-size: 2rem; padding-right: 10px;">
                    <span style="color: rgb(166, 236, 1);">
                        <i class="fab fa-envira"></i>
                    </span>
                </span>
            <span class="fs-4 mx-3">Vegans forum</span>
        </a>
        <div class="text-end mx-1">
            <a href="/create" class="mx-1  btn btn-outline-success rounded-4">Add
                post</a>
        </div>
        <div class="dropdown">
            <a href="/login"
               class="d-flex mx-3 align-items-center link-dark text-decoration-none dropdown-toggle"
               id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <span style="font-size: 2rem; padding-right: 10px;">
                        <span style="color: rgb(166, 236, 1);">
                            <i class="fa fa-user-circle" aria-hidden="true"></i>
                        </span>
                    </span>
                <div class="text-dark">username</div>
            </a>
            <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
                <li><a class="dropdown-item" href="/login">Sign out</a></li>
            </ul>
        </div>
        </header>
    </div>
</nav>
<div class="container pt-3">
    <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h6 class="border-bottom pb-2 mb-0 text-center">All updates</h6>
        <c:forEach items="${posts}" var="post">
            <div class="d-flex text-muted pt-3">
                <a href="<c:url value='/post?id=${post.id}'/>">
                    <span style="font-size: 1.5rem; padding-right: 10px;">
                        <span style="color: rgb(166, 236, 1);">
                            <i class="fas fa-arrow-alt-circle-right"></i>
                        </span>
                    </span>
                </a>
                <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
                    <div class="d-flex justify-content-between">
                        <strong class="text-gray-dark">
                            <c:out value="${post.name}"/>
                        </strong>
                    </div>
                    <span class="d-block text-success">@username</span>
                </div>
                <a href="<c:url value='/update?id=${post.id}'/>">
                    <span style="font-size: 1.2rem;">
                        <span style="color: rgb(166, 236, 1);">
                            <i class="fas fa-edit"></i>
                        </span>
                    </span>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>

</html>
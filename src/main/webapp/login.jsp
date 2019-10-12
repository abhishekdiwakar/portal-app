<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body style="text-align: left; margin: 15px; background-color: #ffffff">
<h1>Portal Application</h1>
<span>${message}</span>
<div style="margin: 0 auto;">
    Patient Login
    <a href="${facebookUrl}">
        <img style="margin-top: 18px; width: 235px;" src="./resources/img/facebookloginbutton.png"/>
    </a>
</div>
<div style="margin: 0 auto;">
    Researcher
    <a href="${spotifyUrl}">
        <img style="margin-top: 18px; width: 250px;" src="./resources/img/spotifyloginbutton.png"/>
    </a>
</div>
<div style="margin: 0 auto;">
    Physician
    <a href="${githubUrl}">
        <img style="margin-top: 18px; width: 250px;" src="./resources/img/githubloginbutton.png"/>
    </a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>


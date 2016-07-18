<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>WFAnalist</title>

    <link href="/wfanalist/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/wfanalist/resources/css/style.css" rel="stylesheet">

    <link rel="shortcut icon" href="/wfanalist/resources/img/favicon.png" />

    <link href='https://fonts.googleapis.com/css?family=Roboto:500' rel='stylesheet' type='text/css'>

</head>
<body>

<%@include file='imports/nav-part.jsp' %>

<p><br>

<div class="container">
    <div class="col-lg-1"></div>
    <div class="col-lg-10 textBlocks">
        <spring:message code="notready.text" />
    </div>
    <div class="col-lg-1"></div>
</div>



<p><br>

<p><br>

<p><br>

<%@include file='imports/footer-part.jsp' %>

</body>
</html>

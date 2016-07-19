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

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/wfanalist/resources/js/bootstrap.js"></script>

    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $('#tabs').tab();
        });
    </script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

    <%@include file='imports/nav-part.jsp' %>

    <br>

    <div class="container">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 textBlocks">
            <spring:message code="statistic.welcome" />
        </div>
        <div class="col-lg-1"></div>
    </div>

    <br>

    <div class="container">
        <div class="col-lg-3"></div>
        <div class="col-lg-9"><h1><spring:message code="statistic.header1" /></h1></div>
    </div>

    <div class="container blueText">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-11">Выбор города (пока жестко Киев)</div>
        </div>
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg-11">Сегодня: ${curDate}</div>
        </div>
    </div>

    <br>

    <div class="container"> <!-------->

        <div id="content">
            <ul id="tabs" class="nav nav-tabs tabsMenu" data-tabs="tabs">
                <c:forEach items="${tableList.gettData()}" var="rowT">
                    <li${rowT.getStrActive1()}><a href="#${rowT.getTabName()}" data-toggle="tab">${rowT.getSource().getName()}</a></li>
                </c:forEach>
            </ul>

            <div id="my-tab-content" class="tab-content">

                <c:forEach items="${tableList.gettData()}" var="rowT">
                    <div class="tab-pane${rowT.getStrActive2()}" id="${rowT.getTabName()}">
                        <table class="table tableStatistic">
                            <thead  class="theadStatistic">
                            <tr>
                                <th rowspan="2" class="thStatistic"><spring:message code="statistictab.date" /></th>
                                <th rowspan="2" class="thStatistic"><spring:message code="statistictab.realtemperature" /></th>
                                <th colspan="4" class="thStatistic"><spring:message code="statistictab.waspredicted" /></th>
                            </tr>
                            <tr>
                                <th class="thStatistic">1 <spring:message code="statistictab.day" /></th>
                                <th class="thStatistic">2 <spring:message code="statistictab.days" /></th>
                                <th class="thStatistic">3 <spring:message code="statistictab.days" /></th>
                                <th class="thStatistic">4 <spring:message code="statistictab.days" /></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${rowT.getRowList()}" var="rowData">
                            <tr>
                                <td class="tdStatistic">${rowData.getSDate()}</td>
                                <td class="tdStatistic">${rowData.getRealTemp()}&deg;</td>
                                <td class="tdStatistic">${rowData.getTemp1day()}&deg;</td>
                                <td class="tdStatistic">${rowData.getTemp2days()}&deg;</td>
                                <td class="tdStatistic">${rowData.getTemp3days()}&deg;</td>
                                <td class="tdStatistic">${rowData.getTemp4days()}&deg;</td>
                            </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </c:forEach>

            </div>
        </div>

    </div> <!-- container -->

    <br>

    <%@include file='imports/footer-part.jsp' %>

</body>
</html>

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

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

	<script type="text/javascript">

		google.charts.load('current', {packages: ['corechart', 'line']});
		google.charts.setOnLoadCallback(drawCurveTypes);

		function drawCurveTypes() {

			<c:forEach items="${chartsList.getlData()}" var="curCh">
			var ${curCh.getDataName()} = new google.visualization.DataTable();
			${curCh.getDataName()}.addColumn('date', 'X');
			${curCh.getDataName()}.addColumn('number', '<spring:message code="charts.lineRealTemp" />');
			${curCh.getDataName()}.addColumn('number', '<spring:message code="charts.line1day" />');
			${curCh.getDataName()}.addColumn('number', '<spring:message code="charts.line2day" />');
			${curCh.getDataName()}.addColumn('number', '<spring:message code="charts.line3day" />');
			${curCh.getDataName()}.addColumn('number', '<spring:message code="charts.line4day" />');

			${curCh.getDataName()}.addRows([ ${curCh.getRows()} ]);
			</c:forEach>

			var options = {
				hAxis: {
					title: '<spring:message code="charts.hAxisTitle" />',
					format: 'd.MM'
				},
				vAxis: {
					title: '<spring:message code="charts.vAxisTitle" />'
				},
				series: {
					1: {curveType: 'function'}
				},
				legend: {
					position: 'out'
				},
				chartArea:{left:50,top:20,width:'75%',height:'65%'}
			};

			<c:forEach items="${chartsList.getlData()}" var="curCh">
			var ${curCh.getChartName()} = new google.visualization.LineChart(document.getElementById('${curCh.getChartDivName()}'));
			${curCh.getChartName()}.draw(${curCh.getDataName()}, options);
			</c:forEach>

		}
	</script>

</head>
<body>

	<%@include file='imports/nav-part.jsp' %>

	<br>

	<div class="container">
		<div class="col-lg-1"></div>
		<div class="col-lg-10 textBlocks">
			<spring:message code="index.welcome" />
		</div>
		<div class="col-lg-1"></div>
	</div>

	<br>

	<div class="container">
		<div class="col-lg-3"></div>
		<div class="col-lg-9"><h1><spring:message code="index.header1" /></h1></div>
	</div>

	<div class="container blueText">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-11">Выбор города</div>
		</div>
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-11">Сегодня: ${curDate}</div>
		</div>
	</div>

	<!-- Current forecasts -->
	<div class="container">
		<div class="row">

			<c:forEach items="${curInd}" var="curI">
			<div class="col-sm-6 col-sm-3">
				<div class="thumbnail">
					<div class="divH3"><div class="divH3Inner"><a href="${curI.ref}" target="_blank"><h3>${curI.source.name}</h3></a></div></div>
					<img src="/wfanalist/resources/img/icons/${curI.img_name}.PNG" alt="Sinoptick">
					<div class="caption">

						<p class="pClass"><spring:message code="index.curTemperature" /> &emsp; ${curI.temperature_l}&deg; ${curI.temperature_h}&deg; C</p>
						<p class="pClass"><spring:message code="index.curHumidity" /> &emsp; ${curI.humidity} %</p>
						<p class="pClass"><spring:message code="index.curFeelsLike" /> &emsp; ${curI.feelsLike}&deg; C</p>
						<p class="pClass"><spring:message code="index.curWind" /> &emsp; ${curI.wind}</p>
						<hr>
						<p> &emsp; Прогноз:</p>
						<p class="pClass">${curI.one_day_str} &emsp; ${curI.one_day_l}&deg; ${curI.one_day_h}&deg; C</p>
						<p class="pClass">${curI.two_days_str} &emsp; ${curI.two_days_l}&deg; ${curI.two_days_h}&deg; C</p>
						<p class="pClass">${curI.three_days_str} &emsp; ${curI.three_days_l}&deg; ${curI.three_days_h}&deg; C</p>
						<p class="pClass">${curI.four_days_str} &emsp; ${curI.four_days_l}&deg; ${curI.four_days_h}&deg; C</p>
					</div>
				</div>
			</div>
			</c:forEach>

		</div>
	</div>

	<br>

	<div class="container">
		<h2><spring:message code="index.header2" /></h2>
	</div>

	<div class="container">
		<div class="col-lg-1"></div>
		<div class="col-lg-10 textBlocks">
			<spring:message code="index.message" />
		</div>
		<div class="col-lg-1"></div>
	</div>


	<!-- Begin of diagrams -->
	<c:forEach items="${chartsList.getlData()}" var="curCh">
	<div class="container">
		<br>
		<div class="rov">
			<div class="col-lg-3"></div>
			<div class="col-lg-9"><h4>${curCh.getSource().getName()}</h4></div>
		</div>
		<div class="rov">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">

				<div id="${curCh.getChartDivName()}"></div>

			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
	<br>
	</c:forEach>

	<!-- End of the diagrams -->

	<div class="container">
		<div class="row rFooter rowFooter1">
			<div class="col-lg-12"> </div>
		</div>
		<div>
			<div class="row rFooter rowFooter2">
				<div class="col-lg-3"> &copy;, все права защищены. </div>
				<div class="col-lg-4"> </div>
				<div class="col-lg-5"> Разработка: Васильева Дарья, Васильев Сергей </div>
			</div>
			<div class="row rFooter rowFooter3">
				<div class="col-lg-3"> Оставить отзыв </div>
				<div class="col-lg-4"> </div>
				<div class="col-lg-5"> Написать нам </div>
			</div>
		</div>
	</div>

</body>
</html>
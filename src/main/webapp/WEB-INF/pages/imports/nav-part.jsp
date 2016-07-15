<%@page pageEncoding="UTF-8"%>
    <div class="container">
        <div class="row rowMenu rowMenu01">
            <div class="col-lg-9"></div>
            <div class="col-lg-3">
                <br>
                <spring:message code="nav.uk" /> &nbsp;
                <spring:message code="nav.ru" /> &nbsp;
                <spring:message code="nav.en" />
            </div>
        </div>
        <div class="row rowMenu rowMenu02left">
            <div class="col-lg-3 text-right textLogo"> <img src="/wfanalist/resources/img/logoWF.png" alt="WFAnalist"> </div>
            <div class="col-lg-9">
                <div class="navbar navbar-default font-sizeXlarge">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/wfanalist"><spring:message code="nav.home" /></a></li>
                            <li><a href="/wfanalist/statistic"><spring:message code="nav.statistics" /></a></li>
                            <li><a href="#"><spring:message code="nav.reviews" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
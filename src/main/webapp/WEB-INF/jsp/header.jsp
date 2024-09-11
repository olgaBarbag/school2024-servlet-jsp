
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<header>
    <%--FlexBox in order to be horizontal alignment--%>
    <div class="outer">
        <%--logo--%>
        <div class="items">
            <%--Relative context path ${}--%>
            <%--svg mathematical functions format--%>
            <a href=""><img class="gov-gr-logo" src="${pageContext.request.contextPath}/img/gov_header_logo.svg" alt="" width="40" height="auto"></a>
            <!-- <a href=""><img src="./art2.png" alt="" width="40" height="auto"></a> -->
            <span class="title">Coding Factory - Education Reinvented </span>
        </div>

        <%--We can take from session scope the username, assuming user has been logged in--%>
        <%--${} : Expression Language--%>
        <%--sessionScope/pageScope/requestScope : implicits--%>
        <%--if user did not login username is null and nothing is showing up--%>
        <div class="login-name">
<%--            <span>${sessionScope.firstname}</span>--%>
<%--            <span>${sessionScope.lastname</span>--%>
            <span>${sessionScope.username}</span>

            <%--Conditional: a checking event --%>
            <%--Syntax: c:if test ="${checking expression}"--%>
            <%--If username is NOT NULL, so user is logged in, exit button shows up--%>
            <%--Conditionals are used to divide users according to their roles and set the corresponding access/restrictions--%>
            <c:if test="${sessionScope.username != null}">
                <a class="log-out" href="${pageContext.request.contextPath}/logout">Έξοδος</a>
            </c:if>

        </div>
    </div>
    <div class="line">

    </div>
</header>
</body>
</html>
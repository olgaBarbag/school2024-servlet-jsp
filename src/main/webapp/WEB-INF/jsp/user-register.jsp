<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Εγγραφή Χρήστη</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-register.css">
</head>
<body>
<%@ include file="header.jsp"%>

<div class="main-content">

    <%--Registration form--%>

    <div class="form m-bottom">
<%--        <form method="POST" action="${pageContext.request.contextPath}/users/register">--%>
            <form method="POST" action="">
            <div class="row m-bottom">
                <%--this value is defined by the saved data in DTO.
                This method is useful for forms with a great number of fields that should be autofilled
                after refresh of the page
                refresh of the page could happen after an unsuccessful VALIDATION
                DTO saves data after POST--%>

                <%--requestScope/sessionScope are the data canal from controllers to jsp pages--%>
                <input class="m-bottom" type="email" name="username" value="${requestScope.userRegisterDTO.username}" required placeholder="Username">
                <%--Here is the error message that will show up if validation failed due to this input
                Validator which will check this input, will return a HashMap key = usernameMessage and value = "message"
                It works like conditional, if passes the validation is null if not an error message will show up--%>
                <p class="validation-error">${requestScope.usernameMessage}</p>
            </div>
            <div class="row m-bottom">
                <input class="m-bottom" type="password" name="password" value="${requestScope.userRegisterDTO.password}" required placeholder="Password">
                <%--Here error message will show up--%>
                <p class="validation-error">${requestScope.passwordMessage}</p>
            </div>
            <div class="row m-bottom">
                <input class="m-bottom" type="password" name="confirmPassword" value="${requestScope.userRegisterDTO.confirmPassword}" required placeholder="Confirm Password">
                <p class="validation-error">${requestScope.confirmPasswordMessage}</p>
            </div>
            <div class="row">
                <button type="submit">Εγγραφή</button>
            </div>
        </form>
    </div>

    <div class="m-bottom">
        <a href="${pageContext.request.contextPath}/login">Επιστροφή</a>
    </div>

    <div>
        ${requestScope.errorMessage}
    </div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>

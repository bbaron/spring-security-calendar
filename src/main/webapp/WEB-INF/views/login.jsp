<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Please Login" scope="request"/>
<jsp:include page="./includes/header.jsp"/>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <div class="alert alert-error">
            Failed to login.
            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
              Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
            </c:if>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            You have been logged out.
        </div>
    </c:if>
    <label for="username">Username</label>
    <input type="text" id="username" name="username"/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/>
    <div class="form-actions">
        <input id="submit" class="btn" name="submit" type="submit" value="Login"/>
        <span>
          <input id="admin1" class="btn test-user" name="admin1" type="button" value="Login as Admin1"/>
          <input id="user1" class="btn test-user" name="user1" type="button" value="Login as User1"/>
          <input id="user2" class="btn test-user" name="user2" type="button" value="Login as User2"/>
          <input id="user3" class="btn test-user" name="user3" type="button" value="Login as User3"/>
          <input id="nobody" class="btn test-user" name="nobody" type="button" value="Login as Nobody"/>
        </span>
    </div>
</form>
<jsp:include page="./includes/footer.jsp"/>
        <script type="text/javascript">
        $(document).ready(function() {
            $('input.test-user').click(function() {
                $('#username').val(this.id + '@example.com');
                $('#password').val(this.id);
            });
       });
        </script>

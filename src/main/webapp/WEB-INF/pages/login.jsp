<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container pt-3"
  style="margin-top: 80px; margin-bottom: 180px;">
  <h5 class="h5 mb-3">Login Form</h5>
  <form:form method="post" action="login" modelAttribute="loginForm">
    <div class="row mb-3">
      <div class="col-md-7">
        <c:if test="${errorMsg != null }">
          <div class="alert alert-danger alert-dismissible fade show"
            role="alert">
            <i class="far fa-times-circle close" data-dismiss="alert"
              aria-label="Close"></i> <strong>${errorMsg }</strong>
          </div>
        </c:if>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-2">
        <label><span class="align-middle">Email</span></label>
      </div>
      <div class="col-md-5">
        <form:input type="text" path="email" class="form-control" />
      </div>
      <div class="col-md-5">
        <small><form:errors class="text-danger" path="email" /></small>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-2">
        <label><span class="align-middle">Password</span></label>
      </div>
      <div class="col-md-5">
        <form:input type="password" path="password" class="form-control" />
      </div>
      <div class="col-md-5">
        <small><form:errors class="text-danger" path="password" /></small>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-md-2"></div>
      <div class="col-md-10">
        <input type="checkbox">&nbsp;Remember Me
      </div>
    </div>
    <div class="row">
      <div class="col-md-2"></div>
      <div class="col-md-5 text-center">
        <input type="submit" class="btn btn-primary" value="Login">
      </div>
      <div class="col-md-5"></div>
    </div>
  </form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
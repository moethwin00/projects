<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
	<h5 class="h5 mb-3">Change Password</h5>
	<form:form method="post" action="checkpassword" modelAttribute="password">
		<form:hidden path="id" value="${password.id}" />
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Old Password</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="password" path="oldPassword" value="${password.oldPassword}" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="oldPassword" /></small>
				<small class="text-danger">${invalidPassword}</small>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>New Password</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="password" value="${password.newPassword}" path="newPassword" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="newPassword" /></small>
				<small class="text-danger">${passwordMismatchError}</small>
			</div>
		</div>
		
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Confirm New Password</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="password" value="${confirmNewPassword}" path="confirmNewPassword"
					class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12"></div>
		</div>

		<div class="row">
			<div class="col-md-2 col-sm-3 col-6 text-right">
				<input type="submit" class="btn btn-primary" value="Confirm">
			</div>
			<div class="col-md-4 col-sm-5 text-left">
				<input type="reset" class="btn btn-outline-success" value="Cancel">
			</div>
		</div>
	</form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
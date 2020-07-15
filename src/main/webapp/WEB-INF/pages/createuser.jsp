<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
	<h5 class="h5 mb-3">${pageTitle}</h5>
	<form:form mehtod="post" action="confirmuser" modelAttribute="userForm"
		enctype="multipart/form-data">
		<form:hidden path="id" />
		<div class="row mb-3">
			<div class="col-md-6">
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
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Name</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="text" path="name" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="name" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Email Address</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="text" path="email" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="email" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Password</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="password" path="password" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small class="text-danger"> <form:errors path="password" />
					<c:if test="${passwordMismatchError != null}">${passwordMismatchError}</c:if>
				</small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Confirm Password</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="password" path="confirmPassword"
					class="form-control" />
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Type</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:select path="type" class="form-control">
					<form:option value="0">Admin</form:option>
					<form:option value="1">User</form:option>
				</form:select>
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="type" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Phone</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="text" path="phone" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="phone" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Date Of Birth</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:input type="date" path="dob" class="form-control" />
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="dob" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Address</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<form:textarea path="address" class="form-control"></form:textarea>
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="address" /></small>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-md-2 col-sm-3 col-6">
				<label><span>Profile</span></label>
			</div>
			<div class="col-md-4 col-sm-5 col-6">
				<div class="input-group">
					<input type="file" name="fileUpload" id="fileUpload"
						accept="image/*" value="${imageData}"
						onchange="showImage.call(this)" /> <input name="imageData"
						type="hidden" id="imageData" value="" />
					<form:input path="profile" type="hidden" value="${imageData }" />
				</div>
			</div>
			<div class="col-md-6 col-sm-4 col-12">
				<small><form:errors class="text-danger" path="profile" /></small>
			</div>
		</div>
		<div class="row">
			<div class="col-6 text-right">
				<input type="submit" class="btn btn-primary" value="Confirm">
			</div>
			<div class="col-6 text-left">
				<input type="reset" class="btn btn-outline-success" value="Cancel">
			</div>
		</div>
	</form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
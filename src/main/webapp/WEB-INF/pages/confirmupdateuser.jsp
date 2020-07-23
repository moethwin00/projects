<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
	<h5 class="h5 mb-3">
		Create User Confirmation
	</h5>
	<form:form method="post" action="updateUser" modelAttribute="user">
		<div class="row">
			<div class="col-md-6">
				<form:hidden path="id" value="${user.id}" />
				<div class="row mb-1 pt-2">
					<div class="col-md-4">
						<label><span class="align-middle">Name</span></label>
					</div>
					<div class="col-md-8">
						<p>${user.name}</p>
						<form:hidden path="name" value="${user.name}" />
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-md-4">
						<label><span class="align-middle">Email</span></label>
					</div>
					<div class="col-md-8">
						<a href="mailto:${user.email}">${user.email}</a>
						<form:hidden path="email" value="${user.email}"/>
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-md-4">
						<label><span class="align-middle">Type</span></label>
					</div>
					<div class="col-md-8">
						<p>
							<c:if test="${user.type == 0}">Admin</c:if>
							<c:if test="${user.type == 1}">User</c:if>
						</p>
						<form:hidden path="type" value="${user.type}" />
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-md-4">
						<label><span class="align-middle">Phone</span></label>
					</div>
					<div class="col-md-8">
						<p>${user.phone}</p>
						<form:hidden path="phone" value="${user.phone}" />
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-md-4">
						<label><span class="align-middle">Date of Birth</span></label>
					</div>
					<div class="col-md-8">
						<p>
							${user.dob}
						<%-- 	<fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" /> --%>
						</p>
						<form:hidden path="dob" value="${user.dob}"/>
					</div>
				</div>
				<div class="row mb-1">
					<div class="col-md-4">
						<label><span class="align-middle">Address</span></label>
					</div>
					<div class="col-md-8">
						<p>${user.address}</p>
						<form:hidden path="address" value="${user.address}"/>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="container-profile">
					<c:if test="${profile == ''}">
						<img alt="Image Preview ..." src="../${user.profile}" class="img-profile img img-fluid img-thumbnail">
					</c:if>
					<c:if test="${profile != ''}">
						<img alt="Image Preview ..." src="${profile}" class="img-profile img img-fluid img-thumbnail">
						<form:hidden path="profile" value="${profile}" />
					</c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 text-right">
				<input type="submit"
					class="btn btn-primary" value="Update"> 
			</div>
			<div class="col-md-8 text-left">
				<a href="editUser?id=${user.id}" class="btn btn-outline-success">Cancel</a>
			</div>
		</div>
	</form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
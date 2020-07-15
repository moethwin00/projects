<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
	<h5 class="h5 mb-3">
		<c:if test="${post.id == 0}">Create User Confirmation</c:if>
		<c:if test="${post.id != 0}">Update User Confirmation</c:if>
	</h5>
	<div class="row">
		<div class="col-md-6">
			<div class="row mb-1 pt-2">
				<div class="col-md-4">
					<label><span class="align-middle">Name</span></label>
				</div>
				<div class="col-md-8">
					<p>${user.name}</p>
				</div>
			</div>
			<div class="row mb-1">
				<div class="col-md-4">
					<label><span class="align-middle">Email</span></label>
				</div>
				<div class="col-md-8">
					<p>${user.email}</p>
				</div>
			</div>
			<div class="row mb-1">
				<div class="col-md-4">
					<label><span class="align-middle">Password</span></label>
				</div>
				<div class="col-md-8">
					<p>
						<input
							style="background-color: transparent; border: 1px solid transparent"
							type="password" value="${user.password}" disabled>
					</p>
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
				</div>
			</div>
			<div class="row mb-1">
				<div class="col-md-4">
					<label><span class="align-middle">Phone</span></label>
				</div>
				<div class="col-md-8">
					<p>${user.phone}</p>
				</div>
			</div>
			<div class="row mb-1">
				<div class="col-md-4">
					<label><span class="align-middle">Date of Birth</span></label>
				</div>
				<div class="col-md-8">
					<p>
						<fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" />
					</p>
				</div>
			</div>
			<div class="row mb-1">
				<div class="col-md-4">
					<label><span class="align-middle">Address</span></label>
				</div>
				<div class="col-md-8">
					<p>${user.address}</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 text-right">
					<a
						href="<c:if test='${errorMsg == null }'>saveUser/${user.id}/${user.name}/${user.email}/${user.password}/${user.profile}/${user.type}/${user.phone}/${user.dob}/${user.address}</c:if>
				<c:if test='${errorMsg != null }'>editPost?id=${user.id}</c:if>"
						class="btn btn-primary"> <c:if test="${user.id == 0}">Create</c:if>
						<c:if test="${user.id != 0}">Update</c:if>
					</a>
				</div>
				<div class="col-md-8 text-left">
					<a href="createpost" class="btn btn-outline-success">Cancel</a>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="container-profile">
				<img src="${user.profile}" class="img-profile img img-fluid img-thumbnail" alt="Image preview...">
			</div>
		</div>
	</div>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
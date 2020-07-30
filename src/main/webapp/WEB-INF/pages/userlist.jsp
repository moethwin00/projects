<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<form:form method="post" action="userlist/searchUsers"
	modelAttribute="userSearch">
	<div class="container">
		<h5 class="h5 mb-3">User List</h5>
		<div class="container">

			<div class="row">
				<div class="col-md-2 mb-1 col-sm-6">
					<form:input type="text" class="form-control" path="name"
						value="${userSearch.name}" />
				</div>
				<div class="col-md-2 mb-1 col-sm-6">
					<form:input type="text" class="form-control" path="email"
						value="${userSearch.email}" />
				</div>
				<div class="col-md-2 mb-1 col-sm-6">
					<form:input type="date" class="form-control" path="createdFrom"
						value="${userSearch.createdFrom}" />
				</div>
				<div class="col-md-2 mb-1 col-sm-6">
					<form:input type="date" class="form-control" path="createdTo"
						value="${userSearch.createdTo}" />
				</div>
				<div class="col-md-2 mb-1 col-sm-6">
					<input type="submit" value="Search"
						class="btn btn-primary btn-post-list">
				</div>
				<div class="col-md-2 mb-1 col-sm-6">
					<a href="userlist/createuser" class="btn btn-primary btn-post-list">Add</a>
				</div>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Created User</th>
						<th>Phone</th>
						<th>Birth Date</th>
						<th>Address</th>
						<th>Created Date</th>
						<th>Updated Date</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userLists}" varStatus="loop">
						<tr>
							<td><a href="#" data-toggle="modal"
								data-target="#detailUser${user.id}">${user.name}</a></td>
							<td>${user.email}</td>
							<td>${user.createUserId}</td>
							<td>${user.phone}</td>
							<td>${user.dob}</td>
							<td>${user.address}</td>
							<td>${user.createdAt}</td>
							<td>${user.updatedAt}</td>
							<td><a href="userlist/editUser?id=${user.id}">Edit</a></td>
							<td><a href="#" data-toggle="modal"
								data-target="#confirmUserModal${user.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:forEach var="user" items="${userLists}" varStatus="looop">
				<div class="modal fade" id="confirmUserModal${user.id}"
					tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-body bg-danger">
								<span class="text-white">Are you to delete this user?</span>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span class="text-white" aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-footer bg-danger">
								<button type="button" class="btn btn-primary"
									onclick="deleteUser(${user.id})">Yes</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">No</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

			<c:forEach var="user" items="${userLists}" varStatus="loop">
				<div class="modal fade" id="detailUser${user.id}" tabindex="-1"
					role="dialog" aria-labelledby="username" aria-hidden="true">
					<div
						class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
						role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="h5 modal-title" id="username">${user.name}</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p class="small font-weight-bold text-muted">
									Created by <span class="text-success">${user.name}</span> at <span
										class="text-primary">${user.createdAt}</span>
								</p>
								<p class="small font-weight-bold text-muted">
									Updated by <span class="text-success">${user.name}</span> at <span
										class="text-primary">${user.updatedAt}</span>
								</p>
								<p class="text-justified">${user.name}</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">OK</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

			<p id="pagination-count" style="display: none">${paginationCount+1}</p>
			<div
				class="container text-center <c:if test="${userCount <= 7}">close-pagination</c:if>">
				<div aria-label="Page navigation example"
					class="container-pagination">
					<ul class="pagination" id="post-pagination">
						<c:if test="${userCount > 7}">
							<li class="page-item disabled" id="prev"><a
								class="page-link" href="javascript:previousId();"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
							</a></li>
							<c:forEach var="i" begin="1" end="${paginationCount}">
								<li class="page-item" id="page-item${i}"><a
									class="page-link" href="userlist?page=${i}">${i}</a></li>
							</c:forEach>
						</c:if>
						<li class="page-item" id="next"><a class="page-link"
							href="javascript:nextId(${paginationCount});" aria-label="Next">
								<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
						</a></li>
					</ul>
				</div>
			</div>

		</div>
	</div>
</form:form>
<jsp:include page="layout/footer.jsp"></jsp:include>
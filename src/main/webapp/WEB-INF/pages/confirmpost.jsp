<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class = "container post-container">
	<h5 class="h5 mb-3">
		<c:if test="${post.id == 0}">Create Post Confirmation</c:if>
		<c:if test="${post.id != 0}">Update Post Confirmation</c:if>
	</h5>
	<%-- <form method="post" action="savePost"> --%>
				<div class="row mb-3">
					<div class="col-md-2"><label><span class="align-middle">Title</span></label></div>
					<div class="col-md-4"><p>${post.title}</p></div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2"><label><span class="align-middle">Description</span></label></div>
					<div class="col-md-4"><p>${post.description}</p></div>
				</div>
				<div class="row">
					<div class="col-md-2 text-right"><a href="<c:if test='${errorMsg == null }'>savePost?id=${post.id}&title=${post.title}&description=${post.description}&status=${post.status}</c:if><c:if test='${errorMsg != null }'>editPost?id=${post.id}</c:if>" class="btn btn-primary">
						<c:if test="${post.id == 0}">Create</c:if>
						<c:if test="${post.id != 0}">Update</c:if>
						</a>
					</div>
					<div class="col-md-4 text-left"><a href="createpost" class="btn btn-outline-success">Cancel</a></div>
				</div>
			<%-- </form> --%>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
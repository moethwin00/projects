<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class = "container post-container">
	<h5 class="h5 mb-3">${pageTitle}</h5>
	<form:form method="post" action="confirmpost" modelAttribute="postForm">
				<%-- <div class="row mb-3">
					<div class="col-md-7">
						<c:if test="${errorMsg != null }">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								<i class="far fa-times-circle close" data-dismiss="alert" aria-label="Close"></i> <strong>${errorMsg }</strong>
							</div>
						</c:if>
					</div>
				</div> --%>
				<form:hidden path="id" />
				<div class="row mb-3">
					<div class="col-md-6">
						<c:if test="${errorMsg != null }">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								<i class="far fa-times-circle close" data-dismiss="alert" aria-label="Close"></i> <strong>${errorMsg }</strong>
							</div>
						</c:if>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2"><label><span>Title</span></label></div>
					<div class="col-md-4"><form:input type="text" path="title" class="form-control" value="${postTitle}"/></div>
					<div class="col-md-6"><small><form:errors class="text-danger" path="title" /></small></div>
				</div>
				<div class="row mb-3">
					<div class="col-md-2"><label><span>Description</span></label></div>
					<div class="col-md-4"><form:textarea path="description" class="form-control"></form:textarea></div>
					<div class="col-md-6"><small><form:errors class="text-danger" path="description"/></small></div>
				</div>
				<c:if test="${pageTitle == 'Update Post'}">
				<div class="row mb-3">
					<div class="col-md-2"><label><span>Status</span></label></div>
					<div class="col-md-4"><form:checkbox data-toggle="toggle" data-size="small" data-onstyle="success" path="active" data-offstyle="danger" /></div>
					<div class="col-md-6"><small><form:errors class="text-danger" path="active"/></small></div>
				</div>
				</c:if>
				<div class="row">
					<div class="col-md-6 text-right"><input type="submit" class="btn btn-primary" value="Confirm"></div>
					<div class="col-md-6 text-left"><input type="reset" class="btn btn-outline-success" value="Cancel"></div>
				</div>
			</form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
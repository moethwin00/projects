<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
	<h5 class="h5 mb-3">Upload CSV File</h5>
	<form:form method="post" action="uploadCSVFile"
		modelAttribute="password" enctype="multipart/form-data">
		<c:forEach items="${uploadErrorMsg}" var="err" varStatus="loop">
			<c:if test="${err != ''}">
				<div class="row mb-3">
					<div class="col-md-6">
						<div class="alert alert-danger alert-dismissible fade show"
							role="alert">
							<i class="far fa-times-circle close" data-dismiss="alert"
								aria-label="Close"></i> <strong>${err}</strong>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
		<div class="row">
			<div class="col-md-6">
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Import File From:</legend>
					<div class="control-group">
						<input type="file" name="csvfile" id="csvfile"
							class="form-control mb-3" accept=".csv" /> <input type="submit"
							value="Import File" class="btn btn-primary" />
					</div>
				</fieldset>
			</div>
			<div class="col-md-6">
				<small class="text-danger">${csvError}</small>
			</div>
		</div>
	</form:form>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
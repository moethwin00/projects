<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<form:form method="post" action="searchPosts" commandName="postSearch" id="movePage">
	<div class = "container">
		<h5 class="h5 mb-3">Post List</h5>
		<div class="container">
		
			<div class="row">
				<div class="col-md-4 mb-1 col-sm-12 col-12"><form:input type="text" class="form-control" id="search-text" path="title"/></div>
				<div class="col-md-2 mb-1 col-sm-3 col-12"><input type="submit" value="Search" class="btn btn-primary btn-post-list"></div>
				<div class="col-md-2 mb-1 col-sm-3 col-4"><a href="createpost" class="btn btn-primary btn-post-list">Add</a></div>
				<div class="col-md-2 mb-1 col-sm-3 col-4"><a href="#" class="btn btn-primary btn-post-list">Upload</a></div>
				<div class="col-md-2 mb-1 col-sm-3 col-4"><a href="#" class="btn btn-primary btn-post-list">Download</a></div>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Post Title</th>
						<th>Post Description</th>
						<th>Posted User</th>
						<th>Posted Date</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${postLists}" varStatus="loop">
					<tr>
						<td><a href="#">${post.title}</a></td>
						<td>${post.description}</td>
						<td>${post.user.name}</td>
						<td>${post.createdAt}</td>
						<td><a href="editPost/${post.id}">Edit</a></td>
						<td><a href="deletePost/${post.id}">Delete</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		
			<div class="container text-center <c:if test="${postCount <= 7}">close-pagination</c:if>">
				<div aria-label="Page navigation example" class="container-pagination">
				  <ul class="pagination" id="post-pagination">
				    
				    <c:if test="${postCount > 7}">
				    	<li class="page-item">
					      <a class="page-link" href="javascript:previousId();" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					        <span class="sr-only">Previous</span>
					      </a>
					    </li>
					    <c:forEach var="i" begin="1" end="${paginationCount+1}">
					    <li class="page-item"><a class="page-link" href="${i}">${i}</a></li>
					    </c:forEach>
				    </c:if>
				    <li class="page-item">
				      <a class="page-link" href="javascript:nextId(${paginationCount+1});" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
				      </a>
				    </li>
				  </ul>
				</div>
			</div>
			
		</div>
	</div>
</form:form>
<jsp:include page="layout/footer.jsp"></jsp:include>
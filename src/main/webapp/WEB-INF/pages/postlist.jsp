<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<form:form method="post"
  action="${pageContext.request.contextPath}/postlist/searchPosts"
  commandName="postSearch" id="movePage">
  <div class="container">
    <h5 class="h5 mb-3">Post List</h5>
    <div class="container">
      <c:if test="${LOGIN_USER != null}">
        <div class="row">
          <div class="col-md-4 mb-1 col-sm-12 col-12">
            <form:input type="text" class="form-control"
              id="search-text" path="title" value="${title}" />
          </div>
          <div class="col-md-2 mb-1 col-sm-3 col-12">
            <input type="submit" value="Search"
              class="btn btn-primary btn-post-list">
          </div>
          <div class="col-md-2 mb-1 col-sm-3 col-4">
            <a
              href="${pageContext.request.contextPath}/postlist/createpost"
              class="btn btn-primary btn-post-list">Add</a>
          </div>
          <div class="col-md-2 mb-1 col-sm-3 col-4">
            <a
              href="${pageContext.request.contextPath}/postlist/uploadcsv"
              class="btn btn-primary btn-post-list">Upload</a>
          </div>
          <div class="col-md-2 mb-1 col-sm-3 col-4">
            <a
              href="${pageContext.request.contextPath}/postlist/download"
              class="btn btn-primary btn-post-list">Download</a>
          </div>
        </div>
      </c:if>
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Post Title</th>
            <th>Post Description</th>
            <th>Posted User</th>
            <th>Posted Date</th>
            <c:if test="${LOGIN_USER != null}">
              <th></th>
              <th></th>
            </c:if>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="post" items="${postLists}" varStatus="loop">
            <tr>
              <td><a href="#" data-toggle="modal"
                data-target="#detailPost${post.id}">${post.title}</a></td>
              <td>${post.description}</td>
              <td>${post.user.name}</td>
              <td>${post.createdAt}</td>
              <c:if test="${LOGIN_USER != null}">
                <td><a href="postlist/editPost?id=${post.id}">Edit</a></td>
                <td><a href="#" data-toggle="modal"
                  data-target="#confirmModal${post.id}">Delete</a></td>
              </c:if>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <c:forEach var="post" items="${postLists}" varStatus="loop">
        <div class="modal fade" id="confirmModal${post.id}"
          tabindex="-1" role="dialog"
          aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered"
            role="document">
            <div class="modal-content">
              <!-- <div class="modal-header modal-header-danger">
				        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				        
				      </div> -->
              <div class="modal-body bg-danger">
                <span class="text-white">Are you to delete this
                  post?</span>
                <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span class="text-white" aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-footer bg-danger">
                <button type="button" class="btn btn-primary"
                  onclick="deletePost(${post.id})">Yes</button>
                <button type="button" class="btn btn-secondary"
                  data-dismiss="modal">No</button>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>

      <c:forEach var="post" items="${postLists}" varStatus="looop">
        <div class="modal fade" id="detailPost${post.id}" tabindex="-1"
          role="dialog" aria-labelledby="postTitle" aria-hidden="true">
          <div
            class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
            role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="h5 modal-title" id="postTitle">${post.title}</h5>
                <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <p class="small font-weight-bold text-muted">
                  Created by <span class="text-success">${post.user.name}</span>
                  at <span class="text-primary">${post.createdAt}</span>
                </p>
                <p class="small font-weight-bold text-muted">
                  Updated by <span class="text-success">${post.user2.name}</span>
                  at <span class="text-primary">${post.updatedAt}</span>
                </p>
                <p class="text-justified">${post.description}</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                  data-dismiss="modal">OK</button>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      <p id="pagination-count" style="display: none">${paginationCount +1}</p>
      <div
        class="container text-center <c:if test="${postCount <= 7}">close-pagination</c:if>">
        <div aria-label="Page navigation example"
          class="container-pagination">
          <ul class="pagination" id="post-pagination">
            <c:if test="${postCount > 7}">
              <li class="page-item disabled" id="prev"><a
                class="page-link" href="javascript:previousId();"
                aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                  <span class="sr-only">Previous</span>
              </a></li>
              <c:forEach var="i" begin="1" end="${paginationCount}">
                <li class="page-item" id="page-item${i}"><a
                  class="page-link" href="postlist?page=${i}">${i}</a></li>
              </c:forEach>
            </c:if>
            <li class="page-item" id="next"><a class="page-link"
              href="javascript:nextId(${paginationCount});"
              aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a></li>
          </ul>
        </div>
      </div>

    </div>
  </div>
</form:form>
<jsp:include page="layout/footer.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/menu.jsp"></jsp:include>
<div class="container post-container">
  <div class="row">
    <div class="col-md-3">
      <span class="h5 mb-3">User Profile</span>
    </div>
    <div class="col-md-4">
      <a href="userlist/editUser?id=${currentUser.id}"
        class="text-right">Edit</a>
    </div>
  </div>
  <div class="row">
    <div class="col-md-6">
      <input type="hidden" value="${currentUser.id}" />
      <div class="row mb-1 pt-2">
        <div class="col-md-4">
          <label><span class="align-middle">Name</span></label>
        </div>
        <div class="col-md-8">
          <p>${currentUser.name}</p>
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Email Address</span></label>
        </div>
        <div class="col-md-8">
          <p>${currentUser.email}</p>
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Password</span></label>
        </div>
        <div class="col-md-8">
          <input type="password" disabled="disabled"
            style="background-color: transparent; border: 1px solid transparent"
            value="${currentUser.password}">
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Type</span></label>
        </div>
        <div class="col-md-8">
          <p>
            <c:if test="${currentUser.type == 0}">Admin</c:if>
            <c:if test="${currentUser.type == 1}">User</c:if>
          </p>
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Phone</span></label>
        </div>
        <div class="col-md-8">
          <p>${currentUser.phone}</p>
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Date of Birth</span></label>
        </div>
        <div class="col-md-8">
          <p>
            ${currentUser.dob}
            <%-- 	<fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" /> --%>
          </p>
        </div>
      </div>
      <div class="row mb-1">
        <div class="col-md-4">
          <label><span class="align-middle">Address</span></label>
        </div>
        <div class="col-md-8">
          <p>${currentUser.address}</p>
        </div>
      </div>
    </div>
  </div>

</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
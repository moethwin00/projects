<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
  <div class="container">
    <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbarTogglerDemo01"
      aria-controls="navbarTogglerDemo01" aria-expanded="false"
      aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
      <a class="navbar-brand" href="#">SCM Bulletin Board</a>
      <c:if test="${LOGIN_USER != null}">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <c:if test="${LOGIN_USER.type == '0'}">
            <li class="nav-item"><a class="nav-link"
              href="${pageContext.request.contextPath}/userlist">Users</a></li>
          </c:if>
          <li class="nav-item"><a class="nav-link"
            href="${pageContext.request.contextPath}/userprofile">User</a></li>
          <li class="nav-item"><a class="nav-link"
            href="${pageContext.request.contextPath}/postlist">Posts</a></li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <span>${LOGIN_USER.name}&nbsp;&nbsp;</span> <a
            class="btn btn-outline-success my-2 my-sm-0"
            href="javascript:noBack();">Logout</a>
        </form>
      </c:if>

      <c:if test="${LOGIN_USER == null}">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <form class="form-inline my-2 my-lg-0">
          <a class="btn btn-outline-success my-2 my-sm-0"
            href="${pageContext.request.contextPath}/login">Login</a>
        </form>
      </c:if>

    </div>
  </div>
</nav>
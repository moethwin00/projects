<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
	<div class="container">
 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
 		 </button>
	  	<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		    <a class="navbar-brand" href="#">SCM Bulletin Board</a>
		    <c:if test="${LOGIN_USER != null}">
		    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			    <li class="nav-item active">
			      <a class="nav-link" href="userlist">Users</a>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="../userprofile">User</a>
	   			</li>
			    <li class="nav-item">
			      <a class="nav-link" href="postlist">Posts</a>
			    </li>
	   		</ul>
		    <form class="form-inline my-2 my-lg-0">
		      <span>${LOGIN_USER.name}&nbsp;&nbsp;</span>
		      <a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/logout">Logout</a>
		    </form>
		    </c:if>
		    <c:if test="${LOGIN_USER == null}">
				<a class="btn btn-outline-success my-2 my-sm-0" href="${pageContext.request.contextPath}/login" onclick="noback()">Login</a>
		    </c:if>
	  	</div>
  	</div>
</nav>
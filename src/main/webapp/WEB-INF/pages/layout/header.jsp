<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SCM Bulletin Board</title>
<link rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<spring:url value="/resources/css/bootstrap-toggle.min.css"
  var="toggleButton"></spring:url>
<spring:url value="/resources/js/jquery.js" var="mainJQuery"></spring:url>
<spring:url value="/resources/css/style.css" var="mainStyle"></spring:url>
<spring:url value="/resources/js/main.js" var="mainJs"></spring:url>
<link rel="stylesheet" href="${toggleButton}">
<link rel="stylesheet" href="${mainStyle}">
<script src="${mainJQuery}"></script>
<script src="${mainJs}"></script>
<script type="text/javascript">
	var url = document.location;
	var param = url.toString().split("/");
	var route = param[param.length - 1];
	(function() {
		if(${LOGIN_USER.id}) {
			if(route == "login") {
				window.history.forward();
			}
		}
		if (window.localStorage) {
			if (!localStorage.getItem('firstLoad')) {
				localStorage['firstLoad'] = true;
				window.location.reload();
			} else
				localStorage.removeItem('firstLoad');
		}
	})();
	
	function noBack() {
		window.sessionStorage.clear();
		document.location = "${pageContext.request.contextPath}/logout";
	}
	function deletePost(id) {
		var url = document.location;
		var param = url.toString().split("/");
		var route = param[param.length - 1];
		if (route == "searchPosts") {
			document.location = "${pageContext.request.contextPath}/postlist/searchDeletePost?id="
					+ id;
		} else {
			document.location = "${pageContext.request.contextPath}/postlist/deletePost?id="
					+ id;
		}
	}
	function deleteUser(id) {
		var url = document.location;
		var param = url.toString().split("/");
		var route = param[param.length - 1];
		if (route == "searchUsers") {
			document.location = "${pageContext.request.contextPath}/userlist/searchDeleteUser?id="
					+ id;
		} else {
			document.location = "${pageContext.request.contextPath}/userlist/deleteUser?id="
					+ id;
		}
	}
	function showImage() {
		if (this.files && this.files[0]) {
			var obj = new FileReader();
			obj.onload = function(data) {
				document.getElementById("imageData").src = data.target.result;
				document.getElementById("imageData").value = data.target.result;
			}
			obj.readAsDataURL(this.files[0]);
		}
	}
</script>
<style>
.container-profile {
  width: 200px;
  height: 200px;
  overflow: hidden;
  margin: 10px;
  background-position: center center;
  position: relative;
}

.img-profile {
  position: absolute;
  margin: auto;
  min-height: 100%;
  min-width: 100%;
  left: -100%;
  right: -100%;
  top: -100%;
  bottom: -100%;
}

fieldset.scheduler-border {
  border: 1px groove #ddd !important;
  padding: 0 1.4em 1.4em 1.4em !important;
  margin: 0 0 1.5em 0 !important;
  -webkit-box-shadow: 0px 0px 0px 0px #000;
  box-shadow: 0px 0px 0px 0px #000;
}

legend.scheduler-border {
  font-size: 1.2em !important;
  font-weight: bold !important;
  text-align: left !important;
}
</style>
</head>
<body>
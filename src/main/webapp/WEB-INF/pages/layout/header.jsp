<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
<script src="${mainJs}"></script>
<script src="${mainJQuery}"></script>
<script type="text/javascript">
	function deletePost(id) {
		var url = document.location;
		var param = url.toString().split("/");
		var route = param[param.length - 1];
		alert(route);
		if (route == "searchPosts") {
			document.location = "searchDeletePost?id=" + id;
		} else {
			document.location = "deletePost?id=" + id;
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
</style>
</head>
<body>
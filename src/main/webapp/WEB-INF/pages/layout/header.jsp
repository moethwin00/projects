<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<spring:url value="/resources/css/bootstrap-toggle.min.css" var="toggleButton"></spring:url>
	<spring:url value="/resources/js/jquery.js" var="mainJQuery"></spring:url>
	<spring:url value="/resources/css/style.css" var="mainStyle"></spring:url>
	<spring:url value="/resources/js/main.js" var="mainJs"></spring:url>
	<link rel="stylesheet" href="${toggleButton}">
	<link rel="stylesheet" href="${mainStyle}">
	<script src="${mainJs}"></script>
	<script src="${mainJQuery}"></script>
	<!-- <script type="text/javascript">
		function search() {
			var searchKey = document.getElementById("search-text").value;
			document.location = searchKey;
		}
	</script> -->
</head>
<body>
var url = document.location;
var param = url.toString().split("/");
var route = param[param.length - 1];

var pageId = 0;
var urlSearchParam = route;
var routeParam = route.toString().split("?");

function noBack() {
	 window.history.forward();
     function noBack()
     {
         window.history.forward();
     }
}

if (routeParam[0] == "userlist" || routeParam[0] == "postlist") {
	pageTag = routeParam[1];
	var pageTag = pageTag.split("=");
	if (pageTag[0] == "page") {
		pageId = pageTag[1];
	}
}

window.onload = function() {
	if (route == "searchPosts" || route == "searchUsers") {
		$("#post-pagination").hide();
	} else {
		if (pageId > 1) {
			$("#prev").removeClass("page-item disabled").addClass("page-item");
		}
		var pagCount = document.getElementById("pagination-count").innerHTML;
		if (pageId == pagCount - 1) {
			$("#next").addClass("disabled");
		}
		$("#page-item" + pageId).removeClass("page-item").addClass(
				"page-item active");
	}
}

function previousId() {
	if (pageId > 1) {
		--pageId;
		if (routeParam[0] == "userlist") {
			document.location = "userlist?page=" + pageId;
		} else if (routeParam[0] == "postlist") {
			document.location = "postlist?page=" + pageId;
		}
	}
}
function nextId(paginationCount) {
	if (pageId < paginationCount) {
		++pageId;
		if (routeParam[0] == "userlist") {
			document.location = "userlist?page=" + pageId;
		} else if (routeParam[0] == "postlist") {
			document.location = "postlist?page=" + pageId;
		}
	}
}
var url = document.location;
var param = url.toString().split("/");
var route = param[param.length - 1];

var pageId = 0;
var urlSearchParam = route;
var routeParam;

if (route.includes("?")) {
	routeParam = route.toString().split("?");
	if (routeParam[0] == "userlist" || routeParam[0] == "postlist") {
		pageTag = routeParam[1];
		var pageTag = pageTag.split("=");
		if (pageTag[0] == "page") {
			pageId = pageTag[1];
		}
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
var url = document.location;
var param = url.toString().split("/");
var route = param[param.length-1];

window.onload = function() {
	if(route == "searchPosts") {
		$("#post-pagination").hide();
	}
}

function previousId() {
	if(route > 1){
		document.location = --route;
	}
}
function nextId(paginationCount) {
	if(route < paginationCount){
		document.location = ++route;
	}
}
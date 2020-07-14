var url = document.location;
var param = url.toString().split("/");
var route = param[param.length-1];

window.onload = function() {
	if(route == "searchPosts") {
		$("#post-pagination").hide();
	}
	if( route > 1) {
		$("#prev").removeClass( "page-item disabled" ).addClass("page-item");
	}
	var pagCount = document.getElementById("pagination-count").innerHTML;
	if(route == pagCount-1) {
		$( "#next" ).addClass( "disabled" );
	}
	$("#page-item"+route).removeClass( "page-item" ).addClass( "page-item active" );
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

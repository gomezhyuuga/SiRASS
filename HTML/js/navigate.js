$(document).ready(function() {
	var activate = $('#sidebarActive').val();
	activate = '#sidebar li#' + activate;
	$(activate).addClass('active');
});
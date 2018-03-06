$(function() {
	$('#logout').click(function() {
	var requestUrl = "http://"+window.location.host+"/Ratatoullie/api/user/logout";
		$.get(requestUrl)
				.done(function(data, status, headers) {
					location.reload();					
				})
				.fail(function(xhr, textStatus, errorThrown) {
				});
	});
});
$(function(){
	$("#searchName").click(function() {
		if($('#name').val()){
			$(location).attr('href', site+"#!/restaurant/searchName/"+$('#name').val()	);
		}else{
		}
	});
});

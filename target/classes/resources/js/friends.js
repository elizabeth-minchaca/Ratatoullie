$(function() {
	$('#tableFollowings').on('check-all.bs.table', function (rows) {
		$('#removeButton').prop('disabled', true);
	});
	$('#tableFollowings').on('uncheck-all.bs.table', function (rows) {
		$('#removeButton').prop('disabled', true);
	});
	$('#tableFollowings').on('check.bs.table', function (row, $element) {
		if($('#tableFollowings').bootstrapTable('getSelections').length==1){
			$('#removeButton').prop('disabled', false);
		}else{
			$('#removeButton').prop('disabled', true);
		}
	});
	$('#tableFollowings').on('uncheck.bs.table', function (row, $element) {
		if($('#tableFollowings').bootstrapTable('getSelections').length==1){
			$('#removeButton').prop('disabled', false);
		}else{
			$('#removeButton').prop('disabled', true);
		}		
	});	
});

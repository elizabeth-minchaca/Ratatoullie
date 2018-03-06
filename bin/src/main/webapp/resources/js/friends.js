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
app.controller("friends",function($scope){
	$scope.friend={};
	$scope.user= {};
	$scope.addFollow = function(){		
		if ($scope.user.mail === $scope.friend.mail) {
			$scope.friend.mail = "";
            swal("Error!", "No puede seguirse a s&iacute; mismo", "error");
		} else {
			var requestUrl = site+"api/user/addFollow";
			$.post(requestUrl, $scope.friend)
			.done(function(data, status, headers) {
				$('#tableFollowings').bootstrapTable('load',{
					data: data.followings
				});
				swal(
						  'Felicidades!',
						  'Estas siguiendo a '+$scope.friend.mail,
						  'success'
						)
			})				
			.fail(function(xhr, textStatus, errorThrown) {
				swal({
					title: 'Error!',
					text: 'No se puede seguir a '+ $scope.friend.mail,
					type: 'error',
				})
			});

		}
	};
	$scope.removeFollow = function(){		
		var requestUrl = site+"api/user/stopFollow";
		var item=$('#tableFollowings').bootstrapTable('getSelections')[0];
		swal({
			title: 'Eliminar',
			text: "Deseas eliminar a "+item.mail,
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Eliminar',
			cancelButtonText: 'Cancelar'
		}).then(function () {
			$.post(requestUrl, {mail:item.mail})
			.done(function(data, status, headers) {
				$('#tableFollowings').bootstrapTable('load',{
					data: data.followings
				});
				swal(
						'Eliminado!',
						'Has dejado de seguir a '+item.mail,
						'success'
				)
			})				
			.fail(function(xhr, textStatus, errorThrown) {
				swal({
					title: 'Error!',
					text: 'No se puede dejar de seguir a '+ item.mail,
					type: 'error',
				})
			});
		})
		
	};
	$.get( site+"api/user/getMy")
	.done(function( data ) {
		$scope.user= data;
		$('#tableFollowings').bootstrapTable({
			data: data.followings
		});
		$('#tableFollowers').bootstrapTable({
			data: data.followers
		})		
	});
});
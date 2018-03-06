app.controller('restaurantList', function($scope, $http, $location){
	$scope.restaurants=[];
	$scope.requestUrl=site+"api/restaurant/list";	
	$http.get($scope.requestUrl)
    .then(function(response) {
        $scope.restaurants = response.data;
        
    });
});
$(function(){
	$("#searchName").click(function() {
		if($('#name').val()){
			$(location).attr('href', site+"#!/restaurant/searchName/"+$('#name').val()	);
		}else{
		}
	});
});

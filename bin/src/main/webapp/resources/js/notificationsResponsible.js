app.controller('notificationsResponsible', function($scope){
	$scope.user={};
	$scope.notificationsNotSeen = [];
	$scope.notificationsSeen = [];

	$scope.requestUrl=site+"api/user/getMy";	
	$.get($scope.requestUrl)
    .done(function(response) {
        $scope.user = response;
        var j=0;
        var k=0;
        var notifications = $scope.user.notifications;
        var notificationsNotSeen = [];
        var notificationsSeen = [];

        for(var i = 0; i < notifications.length; i++){
            if(notifications[i].seen) {
            	notificationsSeen[j] = angular.copy(notifications[i]);
            	j++;
            }else{
            	notificationsNotSeen[k] = angular.copy(notifications[i]);
            	k++;
            }
        }
    	$scope.notificationsSeen = notificationsSeen;
    	$scope.notificationsNotSeen = notificationsNotSeen;
	
    	$('#tableNotSeen').bootstrapTable({
			data: notificationsNotSeen
		});
    	
    	$('#tableSeen').bootstrapTable({
			data: notificationsSeen
		});
        
    });
	
});
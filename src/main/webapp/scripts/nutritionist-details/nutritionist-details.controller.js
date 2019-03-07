'use strict';

angular.module('nutritionistDetails')
    .controller('NutritionistDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;
        
        $http.get('api/nutritionist/' + $stateParams.nutritionistId).then(function (resp) {
            self.nutritionist = resp.data
//            console.log(self.nutritionist)
            
            $http.get('/api/nutritionist/'+$stateParams.nutritionistId +'/users').then(function (resp) {
            		self.users = resp.data;
//            		console.log(self.users);
            })
        });
        
        self.removeUser = function(user){
        		console.log(user);
        		$http.post('api/nutritionist/'+$stateParams.nutritionistId +'/users', user).then(function(resp) {
        			alert('Patient Removed!');
        			location.reload();
        		})
        }
        
        
    }]);
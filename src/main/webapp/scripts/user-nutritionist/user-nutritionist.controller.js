'use strict';

angular.module('userNutritionist')
    .controller('UserNutritionistController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;
        
        $http.get('api/user/' + $stateParams.userId).then(function (resp) {
            self.user = resp.data
            $http.get('api/user/' + $stateParams.userId + '/doctor').then(function(resp) {
            		self.nutritionist = resp.data;
            		console.log(self.nutritionist)
            })
        });
        $http.get('api/nutritionist').then(function (resp) {
            self.nutritionists = resp.data
        });
        

        
        self.setNutritionist = function(nutId) {
//        	"/api/user/{id}/doctor/{docId}"
        		var userId = $stateParams.userId;
        		var docId = nutId;
        		console.log(userId);
        		console.log(docId);
        		$http.post('api/user/' + userId+'/doctor/' +docId).then(function(resp){
        			alert('New nutritionist set!')
        		})
        };
        
        
    }]);

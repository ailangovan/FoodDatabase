'use strict';

angular.module('adminDetails')
    .controller('AdminDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;
        var newUser;
        var newFood;
        
        $http.get('api/admin/' + $stateParams.adminId).then(function (resp) {
            self.admin = resp.data
        });
        
        $http.get('api/user').then(function (resp) {
            self.users = resp.data
        });
        
        $http.get('api/nutritionist').then(function (resp) {
            self.nutritionists = resp.data
        });
        
        $http.get('api/food').then(function (resp) {
            self.food = resp.data
        });
        
        
        self.addUser = function() {
        		console.log(self.newUser);
	        	$http.post('api/user', self.newUser).then(function (resp) {
	        		alert('new user posted')
	        		location.reload();
	            });
        };
        self.addNutritionist = function() {
    		console.log(self.newUser);
        	$http.post('api/nutritionist', self.newNutritionist).then(function (resp) {
        		alert('new nutritionist posted')
        		location.reload();
            });
        };
        
        self.removeNutritionist = function(nutritionist) {
//    			var id = nutritionist.id
    			console.log(nutritionist)
    			$http.delete('api/nutritionist/' + nutritionist).then(function (resp) {
    				alert('Nutritionist Deleted')
    				location.reload();
    			});
        };
        
        
        self.removeUser = function(user) {
        		var id = user.id
        		console.log(id)
        		$http.delete('api/user/' + id).then(function (resp) {
        			alert('User Deleted')
	        		location.reload();
        		});
        };
        
        self.addFood = function() {
        		$http.post('/api/food' , self.newFood).then(function(resp){
        			alert('Food Added')
        			location.reload();
        		})
        };
        
        self.removeFood = function(food) {
        		var post = food.id;
        		console.log(post)
        		$http.delete('/api/food/'+ post).then(function(resp) {
        			alert('Food Deleted')
        			location.reload();
        		})
        };
    }]);
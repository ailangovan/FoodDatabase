'use strict';

angular.module('localFoodSearch')
    .controller('LocalFoodSearchController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;
        
        var userId = $stateParams.userId;  
        var newFoodItem;
        
        $http.get("api/food").then(function (resp) {
            self.food = resp.data;
            console.log(self.food);
        });
        
 
        self.addFood = function(f) {
        		if(userId) {
        			$http.get('api/user/' + $stateParams.userId+ '/diet').then(function (resp) {
        	            self.diet = resp.data;
        	            $http.get('api/diet/' + self.diet.id + '/food').then(function (resp) {
        	        			self.userFood = resp.data;

                	            var postArray = [];
                	      		jQuery.each(self.userFood, function(i, val) {
                	      			delete val.diets;
                	      			postArray.push(val);
                	      		})
                	      		console.log(f);
                	      		delete f.diets;
                	      		postArray.push(f);
                	      		console.log(postArray);
                	      		var post = JSON.stringify(postArray);
                	      		$http.post('api/diet/' + self.diet.id + '/food', post ).then(function (resp) {
                	      			alert('Food Added to Diet')
                	      		})
        	            })
    				})
        		} else {
        			alert('Please Login To Add Food To Your Diet.')
        		}
        }
        self.newFood = function() {
    		if(userId) {
    			$http.post('/api/food', self.newFoodItem).then(function(resp) {
    				alert('Food Added To Database')
    				$http.get('api/user/' + $stateParams.userId+ '/diet').then(function (resp) {
        	            self.diet = resp.data;
        	            $http.get('api/diet/' + self.diet.id + '/food').then(function (resp) {
        	        			self.userFood = resp.data;
        	        			var postArray = [];
                	      		jQuery.each(self.userFood, function(i, val) {
                	      			delete val.diets;
                	      			postArray.push(val);
                	      		})
                	      		self.food.push(self.newFoodItem);
                	      		console.log(postArray);
                	      		var post = JSON.stringify(self.food);
                	      		$http.post('api/diet/' + self.diet.id + '/food', post ).then(function (resp) {
                	      			alert('Food Added to Diet')
                	      		})
        	            })
        	            
    				})
    			})
    			
    		} else {
    			alert('Please Login To Add Food.')
    		}
    }
    }]);

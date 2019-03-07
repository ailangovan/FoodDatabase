'use strict';

angular.module('userDetails')
    .controller('UserDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;
        
        $http.get('api/user/' + $stateParams.userId).then(function (resp) {
            self.user = resp.data
            $http.get('api/user/' + $stateParams.userId + '/doctor').then(function(resp) {
            		self.nutritionist = resp.data;
            		console.log(self.nutritionist)
            })
        });
        
        $http.get('api/user/' + $stateParams.userId+ '/diet').then(function (resp) {
            self.diet = resp.data;
            $http.get('api/diet/' + self.diet.id + '/food').then(function (resp) {
        			self.food = resp.data;
            });
        });

        
        self.deleteFood = function(food) {
        		var food = food;
        		
        		var index = self.food.indexOf(food);
        		
      		self.food.splice(index, 1);
      		var postArray = [];
      		jQuery.each(self.food, function(i, val) {
      			delete val.diets;
      			postArray.push(val);
      		})
      		console.log(postArray);
      		var post = JSON.stringify(self.food);
      		$http.post('api/diet/' + self.diet.id + '/food', post ).then(function (resp) {
      			
      		})
        };
        
        
    }]);

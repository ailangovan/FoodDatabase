'use strict';

angular.module('foodSearch')
    .controller('FoodSearchController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;

        var search = $stateParams.search;
        
        if (!search) {
            self.search = "";
        } else {
            $http.get(
            		"https://api.nal.usda.gov/ndb/search/?format=json&q=" +
            		search +
            		"&sort=n&max=25&offset=0&api_key=hkIfDqsoFT16WzPCcCfZwUl5Lm49IhsusGlSlkmx"
            		).then(function (resp) {
                self.api = resp.data;
                self.list = self.api.list;
                self.food = self.list.item;
                console.log(self.api);
                console.log(self.list);
                console.log(self.food);
            });
            
        }
        
        self.search = function() {
        		$state.go('foodSearch', {search: self.newSearch});
        }
        
        self.addFood = function(food) {
        		var ndb = food.ndbno;
        		$http.get("https://api.nal.usda.gov/ndb/nutrients/?format=json&" +
        				"api_key=hkIfDqsoFT16WzPCcCfZwUl5Lm49IhsusGlSlkmx&" +
        				"nutrients=205&nutrients=204&nutrients=208&nutrients=269&" +
        				"ndbno=" + ndb).then(function(resp) {
        					console.log(resp.data);
        					var item = resp.data.report.foods[0];
        					console.log(item);
        					var name = item.name;
        					var nutrients = item.nutrients;
        					
        					var foodToAdd = {};
        					foodToAdd.name = name;
        					if(nutrients[0].value){
        						foodToAdd.calories = nutrients[0].value;
        					}
        					if(nutrients[1].value){
        						foodToAdd.sugar = nutrients[1].value;
        					}
        					if(nutrients[3].value){
        						foodToAdd.carbohydrates = nutrients[3].value;
        					}
        					if(nutrients[2].value){
        						foodToAdd.fat = nutrients[2].value;
        					}
        					console.log(foodToAdd);
        					$http.post("/api/food", foodToAdd).then(function(resp) {
        						alert("Food Successfully Added to Local!");
        					})
        					
        					
        				})
        			
        }

    }]);

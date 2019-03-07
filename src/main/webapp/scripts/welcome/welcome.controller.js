'use strict';

angular
		.module('layoutWelcome')
		.controller(
				'LoginController',
				[
						"$http",
						'$state',
						'$stateParams',
						function($http, $state, $stateParams) {
							var self = this;

							var user;
							var person;
							var admin;
							var nutritionist;
							var id = {};

							self.submitLogin = function() {

								$http
										.get(
												'/user/' + self.person.userName
														+ '/'
														+ self.person.password)
										.then(
												function(response) {
													self.id = response.data;
													$http
															.get(
																	'/api/admin/'
																			+ self.id)
															.then(
																	function(
																			response) {
																		self.admin = response.data;
																		if (self.admin) {
																			$state.go('adminDetails', {adminId: self.id});
																		}
																	});
													$http
															.get(
																	'/api/user/'
																			+ self.id)
															.then(
																	function(
																			response) {
																		self.user = response.data;
																		if (self.user) {
																			$state.go('userDetails', {userId: self.id});
																		}
																	});
													$http
															.get(
																	'/api/nutritionist/'
																			+ self.id)
															.then(
																	function(
																			response) {
																		self.nutritionist = response.data;
																		if (self.nutritionist) {
																			$state.go('nutritionistDetails', {nutritionistId: self.id});
																		}
																	});
												});
							};

						}]);

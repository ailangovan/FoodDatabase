'use strict';

angular.module('userForm')
    .controller('UserFormController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;

        var userId = $stateParams.userId || 0;

        if (!userId) {
            self.user = {};
        } else {
            $http.get("api/user/" + userId).then(function (resp) {
                self.user = resp.data;
            });
        }

        self.submitUserForm = function () {
            var id = self.user.id;

            if (id) {
            		delete self.user.diet;
                $http.post('api/user/' + id, self.user).then(function () {
                    $state.go('userDetails', {userId: userId});
                });
            } else {
                $http.post('api/user/', self.user).then(function () {
                		alert('New User Created, Please Login!')
                    $state.go('welcome');
                });
            }
        };
    }]);

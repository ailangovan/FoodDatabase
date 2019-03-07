'use strict';

angular.module('userForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('userNew', {
                parent: 'app',
                url: '/user/new',
                template: '<user-form></user-form>'
            })
            .state('userEdit', {
                parent: 'app',
                url: '/user/:userId/edit',
                template: '<user-form></user-form>'
            })
    }]);

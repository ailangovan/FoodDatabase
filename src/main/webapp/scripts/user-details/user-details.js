'use strict';

angular.module('userDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('userDetails', {
                parent: 'app',
                url: '/user/:userId',
                template: '<user-details></user-details>'
            })
    }]);
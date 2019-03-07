'use strict';

angular.module('userNutritionist', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('userNutritionist', {
                parent: 'app',
                url: '/user/:userId/nutritionist',
                template: '<user-nutritionist></user-nutritionist>'
            })
    }]);
'use strict';

angular.module('nutritionistDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('nutritionistDetails', {
                parent: 'app',
                url: '/nutritionist/:nutritionistId',
                template: '<nutritionist-details></nutritionist-details>'
            })
    }]);
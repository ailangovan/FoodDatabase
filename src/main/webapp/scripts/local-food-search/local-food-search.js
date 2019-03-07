'use strict';

angular.module('localFoodSearch', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('localFood', {
                parent: 'app',
                url: '/local',
                template: '<local-food-search></local-food-search>'
            })
            .state('userFood', {
            		parent: 'app',
            		url: '/local/:userId',
            		template: '<local-food-search> </local-food-search>'
            })
    }]);
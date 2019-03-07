'use strict';

angular.module('foodSearch', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('food', {
                parent: 'app',
                url: '/food',
                template: '<food-search></food-search>'
            })
            .state('foodSearch', {
            		parent: 'app',
            		url: '/food/{search}',
            		template: '<food-search> </food-search>'
            })
    }]);
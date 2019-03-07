'use strict';

angular.module('adminDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('adminDetails', {
                parent: 'app',
                url: '/admin/:adminId',
                template: '<admin-details></admin-details>'
            })
    }]);
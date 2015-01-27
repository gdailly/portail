'use strict';

angular.module('portailApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('parametrage_admin', {
                parent: 'admin',
                url: '/parametrage_admin',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/parametrage/parametrage.html',
                        controller: 'ParametrageAdminController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('parametrage');
                        return $translate.refresh();
                    }]
                }
            })

            .state('parametrage', {
                 parent: 'account',
                 url: '/parametrage',
                 data: {
                     roles: ['ROLE_USER']
                 },
                 views: {
                     'content@': {
                         templateUrl: 'scripts/app/entities/parametrage/parametrage.html',
                         controller: 'ParametrageController'
                     }
                 },
                 resolve: {
                     translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                         $translatePartialLoader.addPart('parametrage');
                         return $translate.refresh();
                     }]
                 }
             });
    });

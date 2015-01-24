'use strict';

angular.module('portailApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/main/main.html',
                        controller: 'MainController'
                    }
                },
                resolve: {
                    resolvedParametrage: ['Parametrage', function (Parametrage) {
                         return null;

                    }],
                    resolvedParametrageDefaut: ['Parametrage', function (Parametrage) {
                        return Parametrage.defaut.query();
                    }]
                }
            });
    });

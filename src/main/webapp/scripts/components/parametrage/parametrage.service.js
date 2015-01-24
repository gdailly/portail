'use strict';

angular.module('portailApp')
    .factory('Parametrage', function ($resource) {
        return {
            defaut: $resource('app/rest/parametrage/defaut/:id', {}, {
                'query': { method: 'GET', isArray: true},
                'get': { method: 'GET'}
            }),
            perso: $resource('app/rest/parametrage/:id', {}, {
                'query': { method: 'GET', isArray: true},
                'get': { method: 'GET'}
            })
        }
});

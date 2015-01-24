'use strict';

angular.module('portailApp')
    .factory('Alias', function ($resource) {
        return {
            redirect: $resource('app/redirect/:url', {}, {
                'redirects': { method: 'GET'}
            }),
            get: $resource('app/rest/alias', {}, {
                'get': { method: 'POST'}
            }),
            crud: $resource('app/rest/alias/crud/:id', {}, {
                'get': { method: 'GET'},
                'query': { method: 'GET', isArray: true},
            })
        }
});

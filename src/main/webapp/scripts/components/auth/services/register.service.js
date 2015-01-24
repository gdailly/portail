'use strict';

angular.module('portailApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



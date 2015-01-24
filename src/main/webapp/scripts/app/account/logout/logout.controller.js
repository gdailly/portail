'use strict';

angular.module('portailApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });

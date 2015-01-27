'use strict';

angular.module('portailApp')
    .controller('AliasController', function ($scope, Alias, $filter, $translate) {

        $scope.listeAliasStruct = Alias.crud.query();

        $scope.create = function () {
            Alias.crud.save($scope.aliasStruct,
                function () {
                    $scope.listeAliasStruct = Alias.crud.query();
                    $('#saveAliasModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.aliasStruct = Alias.crud.get({id: id});
            $('#saveAliasModal').modal('show');
        };

        $scope.delete = function (id) {
            Alias.crud.delete({id: id},
                function () {
                    $scope.listeAliasStruct = Alias.crud.query();
                },
    			function (httpResponse) {
    				if(httpResponse.status == 500) {
    					$scope.error = $filter('translate')('alias.error.delete');
    				}
    			});
        };

        $scope.clear = function () {
            $scope.aliasStruct = null;
        };
    });

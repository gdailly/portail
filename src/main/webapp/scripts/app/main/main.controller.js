'use strict';

angular.module('portailApp')
    .controller('MainController', function ($scope,$rootScope, $window,resolvedParametrageDefaut,resolvedParametrage, Alias, Parametrage ,Principal) {
	$scope.listeParametrageDefaut = resolvedParametrageDefaut;
	$scope.listeParametrage = resolvedParametrage;

	$scope.$
	$scope.$on('event:auth-loginConfirmed', function(data) {
        $rootScope.authenticated = true;
        $scope.listeParametrage = Parametrage.perso.query();
    });

	$scope.redirect = function() {
		if($scope.aliasStruct.alias != "") {
			$scope.aliasStruct = Alias.get.get($scope.aliasStruct,
					function (value, responseHeaders) {
				$window.location.href = $scope.aliasStruct.target;
			},
			function (httpResponse) {
				if(httpResponse.status == 404) {
					$scope.showDescription = true;
				}
			});
		}
	}

	$scope.search = function() {
		if($scope.aliasStruct.alias != "") {
			$scope.aliasStruct = Alias.get.get($scope.aliasStruct,
					function (value, responseHeaders) {
				$scope.showDescription = true;
			},
			function (httpResponse) {
				if(httpResponse.status == 404) {
					$scope.showDescription = true;
				}
			});
		}
	}

	$scope.save = function() {
		$scope.aliasStruct = Alias.crud.save($scope.aliasStruct,
				function (value, responseHeaders) {
			$scope.showDescription = false;
		});
	}

	$scope.delete = function() {
		$scope.aliasStruct = Alias.crud.delete({id: $scope.aliasStruct.id},
				function (value, responseHeaders) {
			$scope.showDescription = false;
		});
	}

	$scope.close = function() {
		$scope.showDescription = false;
		$scope.aliasStruct.id = null;
		$scope.aliasStruct.url = null;
		$scope.aliasStruct.owner = null;
	}

	$scope.actionAuthorised = function() {
		return ($rootScope.account == null
				|| ($scope.aliasStruct == null && $rootScope.account != null)
				|| ($scope.aliasStruct.id == null && $rootScope.account != null)
				|| Principal.isInRole('ROLE_ADMIN')
				|| ($rootScope.account != null && $rootScope.account.login == $scope.aliasStruct.owner));
	}

});

'use strict';

angular.module('portailApp')
.controller('ParametrageAdminController', function ($scope, GLYPHICON, Alias, Parametrage) {
	$scope.listeIconeDispo = GLYPHICON;
	$scope.listeParametrage = Parametrage.defaut.query();

	$scope.completeUrl = function() {
		if($scope.alias != "") {
			$scope.parametrageStruct.aliasStruct.alias = $scope.alias;
			$scope.parametrageStruct.aliasStruct = Alias.get.get($scope.parametrageStruct.aliasStruct,
	                function () {
                $scope.parametrageStruct.url = $scope.parametrageStruct.aliasStruct.target;
                if($scope.alias.indexOf("/") > -1) {
                	$scope.parametrageStruct.parametrageAlias = $scope.alias.substr($scope.alias.indexOf("/") + 1 );
                } else {
                	$scope.parametrageStruct.parametrageAlias = null;
                }
            },
			function (httpResponse) {
				if(httpResponse.status == 404) {
					$scope.parametrageStruct.url = "";
				}
			});
		} else {
			$scope.parametrageStruct.url = "";
		}
	}

	$scope.setIcone = function(glyphicon) {
		$scope.parametrageStruct.glyphicon = glyphicon;
		$scope.glyphicon = "<span class=\"glyphicon "+ glyphicon +" btn-lg\"></span>";
	};

	$scope.saveParametrage = function() {
		Parametrage.defaut.save($scope.parametrageStruct,
                function () {
			$scope.listeParametrage = Parametrage.defaut.query();
			$('#saveParametrageModal').modal('hide');
        });
	};

	$scope.isFormumaireValid = function() {
		if($scope.parametrageStruct != null
				&& $scope.parametrageStruct.titre != null && $scope.parametrageStruct.titre != ""
				&& $scope.parametrageStruct.url != null && $scope.parametrageStruct.url != ""
				&& $scope.parametrageStruct.glyphicon != null && $scope.parametrageStruct.glyphicon != "") {
			if($scope.isAliasEnable
					&& $scope.parametrageStruct.aliasStruct.id != null) {
				return true;
			} else {
				return true;
			}
		}
	}

	$scope.setAliasEnable = function(isEnable) {
		$scope.isUrlAliasEnable = true;
		$scope.isAliasEnable = isEnable;

		$scope.parametrageStruct.aliasStruct = {};
		$scope.parametrageStruct.url = "";
	}

	$scope.clear = function() {
		$scope.parametrageStruct = {};
		$scope.parametrageStruct.aliasStruct = {};
		$scope.alias = "";
		$scope.setIcone("");
		$scope.isUrlAliasEnable = false;
	}

	$scope.delete = function(id) {
		Parametrage.defaut.delete({id: id},
                function () {
			$scope.listeParametrage = Parametrage.defaut.query();
        });
	}

	$scope.update = function(id) {
		$scope.parametrageStruct = Parametrage.defaut.get({id: id},function() {
			$scope.setIcone($scope.parametrageStruct.glyphicon);
			if($scope.parametrageStruct.aliasStruct.id != null) {
				$scope.isAliasEnable = true;
				$scope.alias = $scope.parametrageStruct.aliasStruct.alias;
				if($scope.parametrageStruct.parametrageAlias != null) {
					$scope.alias = $scope.alias + "/" + $scope.parametrageStruct.parametrageAlias;
				}
			}
		});
		$scope.isUrlAliasEnable = true;
		$('#saveParametrageModal').modal('show');
	}

	$scope.clear();

})

.controller('ParametrageController', function ($scope, GLYPHICON, Alias, Parametrage) {
	$scope.listeIconeDispo = GLYPHICON;
	$scope.listeParametrage = Parametrage.perso.query();

	$scope.completeUrl = function() {
		if($scope.alias != "") {
			$scope.parametrageStruct.aliasStruct.alias = $scope.alias;
			$scope.parametrageStruct.aliasStruct = Alias.get.get($scope.parametrageStruct.aliasStruct,
	                function () {
                $scope.parametrageStruct.url = $scope.parametrageStruct.aliasStruct.target;
                if($scope.alias.indexOf("/") > -1) {
                	$scope.parametrageStruct.parametrageAlias = $scope.alias.substr($scope.alias.indexOf("/") + 1 );
                } else {
                	$scope.parametrageStruct.parametrageAlias = null;
                }
            },
			function (httpResponse) {
				if(httpResponse.status == 404) {
					$scope.parametrageStruct.url = "";
				}
			});
		} else {
			$scope.parametrageStruct.url = "";
		}
	}

	$scope.setIcone = function(glyphicon) {
		$scope.parametrageStruct.glyphicon = glyphicon;
		$scope.glyphicon = "<span class=\"glyphicon "+ glyphicon +" btn-lg\"></span>";
	};

	$scope.saveParametrage = function() {
		Parametrage.perso.save($scope.parametrageStruct,
                function () {
			$scope.listeParametrage = Parametrage.perso.query();
			$('#saveParametrageModal').modal('hide');
        });
	};

	$scope.isFormumaireValid = function() {
		if($scope.parametrageStruct != null
				&& $scope.parametrageStruct.titre != null && $scope.parametrageStruct.titre != ""
				&& $scope.parametrageStruct.url != null && $scope.parametrageStruct.url != ""
				&& $scope.parametrageStruct.glyphicon != null && $scope.parametrageStruct.glyphicon != "") {
			if($scope.isAliasEnable
					&& $scope.parametrageStruct.aliasStruct.id != null) {
				return true;
			} else {
				return true;
			}
		}
	}

	$scope.setAliasEnable = function(isEnable) {
		$scope.isUrlAliasEnable = true;
		$scope.isAliasEnable = isEnable;

		$scope.parametrageStruct.aliasStruct = {};
		$scope.parametrageStruct.url = "";
	}

	$scope.clear = function() {
		$scope.parametrageStruct = {};
		$scope.parametrageStruct.aliasStruct = {};
		$scope.alias = "";
		$scope.setIcone("");
		$scope.isUrlAliasEnable = false;
	}

	$scope.delete = function(id) {
		Parametrage.perso.delete({id: id},
                function () {
			$scope.listeParametrage = Parametrage.perso.query();
        });
	}

	$scope.update = function(id) {
		$scope.parametrageStruct = Parametrage.perso.get({id: id},function() {
			$scope.setIcone($scope.parametrageStruct.glyphicon);
			if($scope.parametrageStruct.aliasStruct.id != null) {
				$scope.isAliasEnable = true;
				$scope.alias = $scope.parametrageStruct.aliasStruct.alias;
				if($scope.parametrageStruct.parametrageAlias != null) {
					$scope.alias = $scope.alias + "/" + $scope.parametrageStruct.parametrageAlias;
				}
			}
		});
		$scope.isUrlAliasEnable = true;
		$('#saveParametrageModal').modal('show');
	}

	$scope.clear();

});

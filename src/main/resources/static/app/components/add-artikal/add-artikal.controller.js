var magacinApp = angular.module("magacinApp");
magacinApp.controller("addArtikalCtrl", function($scope,$routeParams, $http, $location){
	
	var baseUrl = "/api/artikli";
	var magacinUrl = "/api/magacini";
	
	$scope.magacini = [];
	$scope.artikli = {};
	$scope.artikli.naziv = "";
	$scope.artikli.pakovanje = "";
	$scope.artikli.jedinicaMere = "";
	$scope.artikli.magacinId = "";
	
var getMagacine = function(){
		
		var promise = $http.get(magacinUrl);
		
		promise.then(
			function success(res){
				$scope.magacini = res.data;
			},
			function error(){
				alert("Unsuccessful fetch!")
			}
		);

	}

getMagacine();
	
	$scope.doAdd = function(){
		$http.post(baseUrl, $scope.artikli).then(
			function success(){
				$location.path("/artikli");
			},
			function error(){
				alert("Couldn't add activity!");
			}
		);
	}
	
});
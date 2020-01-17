var magacinApp = angular.module("magacinApp");
magacinApp.controller("primiCtrl", function($scope, $routeParams, $http,
		$location) {

	
	$scope.primi = {};
	$scope.primi.kolicina = "";
	$scope.primi.jedinicnaCena = "";

	var url = "/api/artikli/" + $routeParams.id;

	$scope.doPrimi = function() {
		$http.post(url, $scope.primi).then(function success() {
			$location.path("/artikli");
		}, function error() {
			alert("Something went wrong.");
		});
	}

});
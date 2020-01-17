var magacinApp = angular.module("magacinApp");
magacinApp.controller("editArtikalCtrl", function($scope, $routeParams, $http,
		$location) {

	$scope.magacini = [];
	$scope.artikli = {};
	$scope.artikli.naziv = "";
	$scope.artikli.pakovanje = "";
	$scope.artikli.jedinicaMere = "";
	$scope.artikli.kolicina = "";
	$scope.artikli.kalkulisanaCena = "";
	$scope.artikli.magacinId = "";

	
	var baseUrl = "/api/artikli/" + $routeParams.id;
	var magacinUrl = "/api/magacini";

	
	var getMagacine = function() {

		var promise = $http.get(magacinUrl);

		promise.then(function success(res) {
			$scope.magacini = res.data;
		}, function error() {
			alert("Unsuccessful fetch!")
		});

	}

	getMagacine();
	
var getArtikle = function(){
		
		var promise = $http.get(baseUrl);
		promise.then(
			function uspeh(odg){
				$scope.artikli = odg.data;
			},
			function neuspeh(){
				console.log("Something went wrong!");
			}
		);
		
	}
	
	getArtikle();
	

	$scope.doEdit = function() {
		$http.put(baseUrl, $scope.artikli).then(function success() {
			$location.path("/artikli");
		}, function error() {
			alert("Something went wrong.");
		});
	}

});

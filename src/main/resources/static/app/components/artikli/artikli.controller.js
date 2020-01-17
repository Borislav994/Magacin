var magacinApp = angular.module("magacinApp");

magacinApp.controller("artikliCtrl", function($scope, $http, $location){
	
	$scope.artikli = [];
	$scope.magacini = [];
	
	$scope.magacin = {};
	$scope.magacin.magacinId = "";
	
	$scope.searchParams = {};
	$scope.searchParams.artikalNaziv = $location.search().artikalNaziv != undefined ? $location.search().artikalNaziv  : "";
	$scope.searchParams.magacinNaziv = $location.search().magacinNaziv != undefined ? $location.search().magacinNaziv : "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1
	
	var url = "/api/artikli";
	var magacinUrl = "/api/magacini";
	
var getMagacine = function(){
		
		var promise = $http.get(magacinUrl);
		
		promise.then(
			function success(res){
				$scope.magacini = res.data;
				getArtikle();
			},
			function error(){
				alert("Unsuccessful fetch!")
			}
		);

	}
	
	var getArtikle = function(){
		
		var config = { params: {} };
		
		//Dakle, polja config.params objekta moraju da se zovu kako back-end ocekuje
		if($scope.searchParams.artikalNaziv != ""){
			$location.search("artikalNaziv", $scope.searchParams.artikalNaziv);
			config.params.artikalNaziv = $scope.searchParams.artikalNaziv;
		}
		
		if($scope.searchParams.magacinNaziv != ""){
			config.params.magacinNaziv = $scope.searchParams.magacinNaziv;
		}

		config.params.pageNum = $scope.pageNum;
		
		$http.get(url, config).then(
			function success(res){
				$scope.artikli = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	

	
	getMagacine();
	
	$scope.goToAdd = function(){
		$location.path("/artikli/add");
	}
	
	$scope.goToEdit = function(id){
		$location.path("/artikli/edit/" + id);
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getArtikle();
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
	
	$scope.doSearch = function(){

		var params =  {};
		
		if($scope.searchParams.artikalNaziv != ""){
			params.artikalNaziv = $scope.searchParams.artikalNaziv;
		}
		
		if($scope.searchParams.magacinNaziv != ""){
			params.magacinNaziv = $scope.searchParams.magacinNaziv;
		}
		
		$location.search(params);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getArtikle();
	}
	
	$scope.goToPrimi = function(id){
		$location.path("/artikli/primi/" + id);
	}
	
	
	
}); 
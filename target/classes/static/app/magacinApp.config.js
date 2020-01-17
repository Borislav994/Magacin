var magacinApp = angular.module("magacinApp");
magacinApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/artikli', {
			templateUrl : '/app/components/artikli/artikli.html'
		})
		.when('/artikli/add', {
			templateUrl : '/app/components/add-artikal/add-artikal.html'
		})
		.when('/artikli/edit/:id', {
			templateUrl : '/app/components/edit-artikal/edit-artikal.html'
		})
		.when('/artikli/primi/:id', {
			templateUrl : '/app/components/primi/primi.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
'use strict';

/* Controllers */



var phonecatApp = angular.module('phonecatApp', ['spring-security-csrf-token-interceptor']);
phonecatApp.config(function(csrfProvider) {
    // optional configurations
    csrfProvider.config({
        url: 'poll',
        maxRetries: 3,
        csrfHttpType: 'get',
        csrfTokenHeader: 'X-CSRF-XXX-TOKEN',
        httpTypes: ['PUT', 'POST', 'DELETE'] //CSRF token will be added only to these method types 
    })});

phonecatApp.controller('pollCtrl', ['$scope', '$http', function($scope, $http) {
  $http.get('poll').success(function(data) {
    $scope.phones = data;
  });

  $scope.list = [];
  $scope.text = '';
  $scope.submit = function() {
    if ($scope.text) {
      $scope.list.push(this.text);
      $scope.text = '';
      
      var data = $.param({
          json: JSON.stringify({
              name: $scope.newName
          })
      });
      
      $http.post('poll', data).success(function(data, status) {
          $scope.hello = data;
      })
    }
  };
  
 
  
  $scope.orderProp = 'age';
}]);

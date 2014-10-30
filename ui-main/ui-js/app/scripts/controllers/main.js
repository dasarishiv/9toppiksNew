'use strict';

/**
 * @ngdoc function
 * @name uiJsApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the uiJsApp
 */
angular.module('uiJsApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });

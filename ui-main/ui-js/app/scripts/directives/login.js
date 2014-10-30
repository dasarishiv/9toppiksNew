'use strict';

/**
 * @ngdoc directive
 * @name 9toppiksApp.directive:login
 * @description
 * # login
 */
angular.module('9toppiksApp')
  .directive('login', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the login directive');
      }
    };
  });

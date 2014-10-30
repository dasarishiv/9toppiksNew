'use strict';

/**
 * @ngdoc filter
 * @name 9toppiksApp.filter:login
 * @function
 * @description
 * # login
 * Filter in the 9toppiksApp.
 */
angular.module('9toppiksApp')
  .filter('login', function () {
    return function (input) {
      return 'login filter: ' + input;
    };
  });
